package Server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import protobuf.PacketProtos.Packet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Server implements Runnable {
    private static final Logger logger = LogManager.getLogger(Server.class);

    private final int port; //the port that the clients will connect to
    private final ServerSocket server;

    private final Lobby lobby;
    private final int numPlayersToStart;

    private boolean isRunning; // Keeps track of whether the server is running
    private final Object isRunningLock;
    private boolean inLobby; // Keeps track of whether the server is in lobby waiting for connections
    private final Object inLobbyLock;
    private boolean inGame; // Keeps track of whether the game has started
    private final Object inGameLock;

    private final List<Packet> packetQueue;

    public Server(ConnectionConfig config) throws IOException {
        int port = config.getHostPort();

        // Checking if the host port provided is valid
        if(port <= 1024 || port > 65535)
            throw new IllegalArgumentException(String.format("Port must be between 1024 exclusive and 65535 inclusive; got %d", port));

        //setting up fields
        this.port = port;
        this.numPlayersToStart = 4; // Hard-code for now
        this.lobby = new Lobby(this, numPlayersToStart);
        this.server = new ServerSocket(this.port);
        this.server.setSoTimeout(5000);

        this.isRunning = false;
        this.isRunningLock = new Object();
        this.inLobby = false;
        this.inLobbyLock = new Object();
        this.inGame = false;
        this.inGameLock = new Object();

        this.packetQueue = new ArrayList<>();
    }

    public String getLocalIP(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "Could not retrieve local address.";
        }
    }

    @Override
    public void run() {
        // Wait for connections until the lobby is full
        accept();

        // Lobby is now full, start the game
        try {
            if (checkIfRunning()) {
                // Broadcast to all players in lobby that the game is starting
                broadcast(Packet.newBuilder()
                        .setUUID("SERVER")
                        .setType(Packet.Type.START_GAME)
                        .build());

                // Stop any new connections to be made to the server socket
                server.close();

                setIfInGame(true);
                /*
                 * TODO: Start game logic
                 */
            }
        } catch (IOException e) {

        }
    }

    // Handles incoming connections from clients
    private void accept(){
        while((lobby.getNumPlayersConnected() < numPlayersToStart) && checkIfRunning()){
            try {

                if(lobby.getNumPlayersConnected() < numPlayersToStart){
                    //
                    Socket client = server.accept();

                    // Set up a listener so that the server listens to incoming client messages
                    new ServerListener(lobby, client).run();

                    broadcastPlayerList();
                }
            } catch (IOException e) {

            }
        }

        setIfInLobby(false);
    }

    private void broadcastPlayerList() throws IllegalArgumentException, IOException{
        StringBuilder waiting = new StringBuilder(""); //all of the players that are currently connected

        Packet.Builder buildPlayerList = Packet.newBuilder()
                                        .setUUID("SERVER")
                                        .setType(Packet.Type.LOBBY);

        // Retrieve player UUID and names and add them to the Packet
        for(ConnectionDetails connectionDetails : lobby.getConnectionDetails()) {
            buildPlayerList.addMessage(connectionDetails.getUsername());
            buildPlayerList.addAddon(connectionDetails.getPlayerId().toString());
        }

        Packet playerList = buildPlayerList.build();

        //sending update
        broadcast(playerList);
    }

    public void broadcast(Packet message) throws IOException{
        synchronized(lobby){
            // Iterate through each client in the lobby and send them the message
            for(ConnectionDetails clientDetails : Collections.synchronizedCollection(lobby.getClients().values())){
                ServerListener listener = clientDetails.getListener();

                try{
                    clientDetails.getListener().send(message);
                } catch(IOException e){

                }
            }
        }
    }

    public void queuePacket(Packet message){
        packetQueue.add(message);
        packetQueue.notifyAll();
    }

    public boolean checkIfRunning(){
        synchronized(isRunningLock){
            return isRunning;
        }
    }

    public void setIfServerRunning(boolean isRunning){
        synchronized(isRunningLock){
            this.isRunning = isRunning;
        }
    }

    public boolean checkIfInLobby(){
        synchronized(inLobbyLock){
            return inLobby;
        }
    }

    public void setIfInLobby(boolean inLobby){
        synchronized(inLobbyLock){
            this.inLobby = inLobby;
        }
    }

    public boolean checkIfInGame(){
        synchronized(inGameLock){
            return inGame;
        }
    }

    public void setIfInGame(boolean inGame){
        synchronized(inGameLock){
            this.inGame = inGame;
        }
    }

    public void kill(){
        if(checkIfRunning()){

            setIfServerRunning(false);
            setIfInLobby(false);
            setIfInGame(false);

            lobby.killAll();

            try{
                server.close();
            } catch(IOException e){
                // The server is already closed
            }
        }
    }
}
