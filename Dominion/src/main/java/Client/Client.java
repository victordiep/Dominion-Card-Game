package Client;

import Client.GUI.Element.Start.PlayerListMenu;
import Client.GUI.Screen.Game.GamePane;
import Server.ConnectionConfig;
import javafx.application.Platform;
import protobuf.PacketProtos.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;

/*
 * - The server then sends the interaction made to all other clients who will then perform the action on their local
 *   representation of the game
 */

public class Client implements Runnable {
    private UUID playerId;
    private String username;
    private InetAddress hostName;
    private int localPort;
    private int hostPort;

    private Socket socket; // The client's socket
    private ObjectInputStream in; // Used to read messages coming from the client to the server
    private ObjectOutputStream out; // Used to send messages from the server to the client

    private boolean isRunning = false; // Keeps track of whether the server is running
    private final Object isRunningLock;
    private boolean inLobby = false; // Keeps track of whether the server is in lobby waiting for connections
    private final Object inLobbyLock;
    private boolean inGame = false; // Keeps track of whether the game has started
    private final Object inGameLock;

    private boolean isProcessing = false; // Keeps track of whether the client is already doing work
    private final Object isProcessingLock;

    private Map<UUID, String> playerList;

    public Client() {
        isRunningLock = new Object();
        inLobbyLock = new Object();
        inGameLock = new Object();
        isProcessingLock = new Object();

        playerList = Collections.synchronizedMap(new LinkedHashMap<>());
    }

    public void initialize(ConnectionConfig config) throws UnknownHostException {
        playerId = UUID.randomUUID();
        username = config.getUsername();
        hostName = config.getHostName();
        localPort = config.getLocalPort();
        hostPort = config.getHostPort();
    }

    public UUID getPlayerId() { return playerId; }
    public String getUsername() { return username; }
    public int getPlayerListSize() { return playerList.size(); }
    public List<String> getPlayers() { return new LinkedList<>(playerList.values()); }
    public List<UUID> getPlayerIds() { return new LinkedList<>(playerList.keySet()); }

    @Override
    public void run() {
        try {
            connect();

            while((checkIfInLobby() || checkIfInGame()) && checkIfRunning()) {
                try {
                    Packet message = read();

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

                    process(message);
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {

        }
    }

    public void connect() throws IOException{
        try {
            // Setting up the socket
            this.socket = new Socket(hostName.getHostName(), hostPort);
            setIfRunning(true);

            // Setting up input and output
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Sending Packet back to that contains this client's UUID and username
            Packet message = Packet.newBuilder()
                    .setUUID(this.playerId.toString())
                    .setType(Packet.Type.CONNECTION)
                    .addMessage(this.username)
                    .build();
            out.reset();
            out.writeObject(message);
            out.flush();

            // Once the server accepts the client, the server should send a LOBBY packet containing the list of players
            message = read();

            if(message.getType() != Packet.Type.LOBBY){
                // Something went wrong
                throw new IOException();
            } else {
                process(message);
            }

        } catch (ClassNotFoundException e) {

        } catch (SocketException e) {
            throw new IOException("Failed to connect to server.");
        }
    }

    public synchronized Packet read() throws ClassNotFoundException, IOException{
        try {
            Object o = in.readObject();

            if(o instanceof Packet)
                return (Packet) o;
            else
                throw new IOException("Invalid protocol: Received something other than a packet");

        } catch(IOException e){
            throw new IOException(e.getMessage());
        }
    }

    public void process(Packet message) throws ClassNotFoundException, IOException {
        synchronized(isProcessingLock){
            isProcessing = true;
        }

        Packet.Type messageType = message.getType();

        if (messageType == Packet.Type.LOBBY) {
            setIfInLobby(true);

            // Received update to lobby so clear and then rebuild player list
            playerList.clear();
            for (int i = 0; i < message.getMessageCount(); i++) {
                playerList.put(UUID.fromString(message.getAddon(i)), message.getMessage(i));
            }

            Platform.runLater(PlayerListMenu::updatePlayerLobby);
        }
        else if (messageType == Packet.Type.CARD_LIST) {
            if (DominionManager.getInstance().getGame() == null)
                DominionManager.getInstance().createGame(message.getMessageList());
        }
        else if (messageType == Packet.Type.START_GAME) {
            setIfInLobby(false);
            setIfInGame(true);
            DominionManager.getInstance().switchToScreen(new GamePane(DominionManager.getInstance().getGame(), getPlayers()));
        }

        finish();
    }

    public void finish(){
        synchronized(isProcessingLock){
            isProcessing = false;
            isProcessingLock.notifyAll();
        }
    }

    public synchronized void send(Packet message) throws IllegalArgumentException, IOException{
        out.reset();
        out.writeObject(message);
        out.flush();
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
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
                if (socket != null)
                    socket.close();

            } catch(IOException e){
                // The server is already closed
                e.printStackTrace();
            }
        }
    }
}
