package Server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import Client.DominionManager;
import Client.GUI.Element.Start.PlayerListMenu;
import Client.GUI.Screen.SceneState;
import javafx.application.Platform;
import protobuf.PacketProtos.Packet;

public class Server implements Runnable {

    private int port; //the port that the clients will connect to
    private ServerSocket server;

    private Lobby lobby;
    private int numPlayersToStart;

    private boolean isRunning = false; // Keeps track of whether the server is running
    private final Object isRunningLock;
    private boolean inLobby = false; // Keeps track of whether the server is in lobby waiting for connections
    private final Object inLobbyLock;
    private boolean inGame = false; // Keeps track of whether the game has started
    private final Object inGameLock;
    private boolean isProcessing = false; // Keeps track of whether the game has started
    private final Object isProcessingLock;

    private List<Packet> packetQueue;

    public Server() {
        this.isRunningLock = new Object();
        this.inLobbyLock = new Object();
        this.inGameLock = new Object();
        this.isProcessingLock = new Object();

        this.packetQueue = new ArrayList<>();
    }

    public void initialize(ConnectionConfig config) throws IOException {
        int port = config.getHostPort();

        // Checking if the host port provided is valid
        if(port <= 1024 || port > 65535)
            throw new IllegalArgumentException(String.format("Port must be between 1024 exclusive and 65535 inclusive; got %d", port));

        //setting up fields
        this.port = port;
        this.numPlayersToStart = config.getLobbySize();
        this.lobby = new Lobby(this, this.numPlayersToStart);
        this.server = new ServerSocket(this.port);
        this.server.setSoTimeout(5000);
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
        try {
            accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

                setIfInLobby(false);
                setIfInGame(true);

                lobby.establishPlayerOrder();

                broadcast(Packet.newBuilder()
                        .setUUID("SERVER")
                        .setType(Packet.Type.SELECT_TURN)
                        .addMessage(lobby.getCurrentPlayerTurn().toString())
                        .build());

                while (checkIfInGame()) {
                    if (!packetQueue.isEmpty()) {
                        synchronized(isProcessingLock){
                            while(isProcessing){
                                try {
                                    isProcessingLock.wait();
                                } catch (InterruptedException e) {
                                    isProcessing = false;
                                }
                            }
                            isProcessing = true;
                        }

                        process(readPacket());
                    }
                }
            }
        } catch (IOException e) {

        }
    }

    public void process(Packet message) throws IOException {
        synchronized (isProcessingLock) {
            isProcessing = true;
        }

        Packet.Type messageType = message.getType();

        if (messageType == Packet.Type.END_TURN) {
            broadcast(Packet.newBuilder()
                    .setUUID("SERVER")
                    .setType(Packet.Type.SELECT_TURN)
                    .addMessage(lobby.getCurrentPlayerTurn().toString())
                    .build());
        }

        finish();
    }

    public void finish () {
        synchronized (isProcessingLock) {
            isProcessing = false;
            isProcessingLock.notifyAll();
        }
    }

        // Handles incoming connections from clients
    private void accept() throws IOException {
        setIfRunning(true);

        while(lobby.getNumPlayersConnected() < numPlayersToStart && checkIfRunning()){
            try {
                if(lobby.getNumPlayersConnected() < numPlayersToStart){
                    setIfInLobby(true);
                    Socket client = server.accept();

                    // Set up a listener so that the server listens to incoming client messages
                    Thread thread = new Thread(new ServerListener(lobby, client));
                    thread.start();

                    TimeUnit.SECONDS.sleep(1);

                    broadcastPlayerList();
                }
            } catch (IOException e) {

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (!DominionManager.getInstance().getIfCardsSelected()) {}

        // Broadcast the card list
        Packet.Builder cardListMsg = Packet.newBuilder()
                .setUUID("SERVER")
                .setType(Packet.Type.CARD_LIST);
        for (String name : DominionManager.getInstance().getKingdomCards()) {
            cardListMsg.addMessage(name);
        }
        System.out.println();
        broadcast(cardListMsg.build());

        setIfInLobby(false);
    }

    private void broadcastPlayerList() throws IllegalArgumentException, IOException{
        Packet.Builder buildPlayerList = Packet.newBuilder()
                                        .setUUID("SERVER")
                                        .setType(Packet.Type.LOBBY);

        // Retrieve player UUID and names and add them to the Packet
        for(ConnectionDetails connectionDetails : lobby.getConnectionDetails()) {
            buildPlayerList.addMessage(connectionDetails.getUsername());
            buildPlayerList.addAddon(connectionDetails.getPlayerId().toString());;
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
                    listener.send(message);
                } catch(IOException e){

                }
            }
        }
    }

    public synchronized void queuePacket(Packet message){
        packetQueue.add(message);
    }

    public synchronized Packet readPacket(){
        return packetQueue.remove(0);
    }

    public boolean checkIfRunning(){
        synchronized(isRunningLock){
            return isRunning;
        }
    }

    public void setIfRunning(boolean isRunning){
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

            setIfRunning(false);
            setIfInLobby(false);
            setIfInGame(false);

            try{
                lobby.killAll();
                server.close();
            } catch(IOException e){
                // The server is already closed
            }
        }
    }
}
