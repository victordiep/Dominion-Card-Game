package Server;

import protobuf.PacketProtos.Packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

/*
 * Every time the server accepts a new client, a ServerListener is created that will listen to messages from the client.
 * The server then handles the message accordingly.
 */

public class ServerListener implements Runnable {

    private ConnectionDetails clientDetails;

    private final Socket client; // The client's socket
    private final ObjectInputStream in; // Used to read messages coming from the client to the server
    private final ObjectOutputStream out; // Used to send messages from the server to the client

    private final Lobby lobby; //contains the other clients

    private boolean isRunning;

    public ServerListener(Lobby lobby, Socket client) throws IOException {
        this.client = client;
        this.lobby = lobby;
        this.isRunning = false;

        //setting up readers and writers
        this.in = new ObjectInputStream(client.getInputStream());
        this.out = new ObjectOutputStream(client.getOutputStream());

        if(lobby.getNumPlayersConnected() >= lobby.getMaxNumOfPlayers()){
            in.close();
            out.close();
            client.close();
            throw new IllegalArgumentException("Server is full");
        }
    }

    @Override
    public void run() {
        try {
            readPlayerDetails();
            lobby.addClient(clientDetails);
            isRunning = true;

            while (isRunning) {
                try {
                    Packet message = (Packet) in.readObject();
                    lobby.queuePacketToProcess(message);
                }
                catch (ClassNotFoundException e) {
                    // Something that was not a packet was sent
                    kill();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readPlayerDetails() {
        try {
            Packet message = (Packet) in.readObject();

            if (message.getType() == Packet.Type.CONNECTION) {
                this.clientDetails = new ConnectionDetails(UUID.fromString(message.getUUID()) , message.getMessage(0), this);
            }
        }
        catch (Exception e) {

        }
    }

    public void send(Packet packet) throws IOException{
        out.reset();
        out.writeObject(packet);
        out.flush();
    }

    public void kill(){
        if(isRunning){
            try{
                isRunning = false;

                lobby.removeClient(clientDetails.getPlayerId());

                in.close();
                out.close();
                client.close();
            } catch(IOException ex){
                System.err.println(String.format("ERROR: %s", ex.getMessage()));
            }
        }
    }

}
