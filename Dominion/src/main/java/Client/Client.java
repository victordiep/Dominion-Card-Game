package Client;

import Server.ConnectionConfig;
import protobuf.PacketProtos.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

        this.isRunning = false;
        this.isRunningLock = new Object();
        this.inLobby = false;
        this.inLobbyLock = new Object();
        this.inGame = false;
        this.inGameLock = new Object();

        this.playerList = Collections.synchronizedMap(new HashMap<UUID, String>());
    }

    @Override
    public void run() {
        try {
            connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connect() throws IOException{
        try {

            // Setting up the socket
            this.socket = new Socket(hostName.getHostName(), localPort);

            // Setting up input and output
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();

            // Once the server accepts the client, the server should send a LOBBY packet containing the list of players
            Packet message = (Packet) read();

            if(message.getType() != Packet.Type.LOBBY){
                // Something went wrong
                throw new IOException();
            } else {
                for (int i = 0; i < message.getMessageCount(); i++) {
                    playerList.put(UUID.fromString(message.getAddon(i)), message.getMessage(i));
                }
            }

            // Sending Packet back to that contains this client's UUID and username
            message = Packet.newBuilder()
                        .setUUID(this.playerId.toString())
                        .setType(Packet.Type.CONNECTION)
                        .setMessage(0, this.username)
                        .build();
            out.reset();
            out.writeObject(message);
            out.flush();

        } catch (ClassNotFoundException e) {

        } catch (SocketException e) {
            throw new IOException("Failed to connect to server.");
        }
    }

    private synchronized Packet read() throws ClassNotFoundException, IOException{
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
}
