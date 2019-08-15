package Client;

import Server.ConnectionConfig;
import protobuf.PacketProtos.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

/*
 * - Client holds the GuiManager and has a thread that listens to it
 * - GuiManager keeps track of interactions made with the GUI and stores it in a data collection for the client
 *   to send details of the interaction to the server
 * - The server then sends the interaction made to all other clients who will then perform the action on their local
 *   representation of the game
 */

public class Client implements Runnable {

    private final UUID playerId;
    private final String username;
    private final InetAddress hostName;
    private final int localPort;
    private final int hostPort;

    private Socket socket; // The client's socket
    private ObjectInputStream in; // Used to read messages coming from the client to the server
    private ObjectOutputStream out; // Used to send messages from the server to the client

    private boolean isRunning; // Keeps track of whether the server is running
    private final Object isRunningLock;
    private boolean inLobby; // Keeps track of whether the server is in lobby waiting for connections
    private final Object inLobbyLock;
    private boolean inGame; // Keeps track of whether the game has started
    private final Object inGameLock;

    private Map<UUID, String> playerList;

    public Client(ConnectionConfig config) throws IOException {
        this.playerId = UUID.randomUUID();
        this.username = config.getUsername();
        this.hostName = config.getHostName();
        this.localPort = config.getLocalPort();
        this.hostPort = config.getHostPort();

        this.isRunning = false;
        this.isRunningLock = new Object();
        this.inLobby = false;
        this.inLobbyLock = new Object();
        this.inGame = false;
        this.inGameLock = new Object();

        this.playerList = Collections.synchronizedMap(new HashMap<UUID, String>());
    }

    public UUID getPlayerId() { return playerId; }
    public String getUsername() { return username; }
    public synchronized int getPlayerListSize() { return playerList.size(); }
    public synchronized List<String> getPlayers() { return new LinkedList<>(playerList.values()); }

    @Override
    public void run() {
        try {
            connect();
        } catch (IOException e) {

        }
    }

    public void connect() throws IOException{
        try {
            // Setting up the socket
            this.socket = new Socket(hostName.getHostName(), hostPort);

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
                for (int i = 0; i < message.getMessageCount(); i++) {
                    playerList.put(UUID.fromString(message.getAddon(i)), message.getMessage(i));
                }
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
            }
        }
    }
}
