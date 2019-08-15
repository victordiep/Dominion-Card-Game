package Networking;

import Client.*;
import Server.*;
import protobuf.PacketProtos.Packet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class SocketConnectionTest {
    private Client client;
    private Server server;

    private ConnectionConfig clientConfig;
    private ConnectionConfig serverConfig;

    @Before
    public void setUp() throws Exception {
        clientConfig = new ConnectionConfig();
        clientConfig.setHostName("localhost");
        clientConfig.setHostPort(4444);
        clientConfig.setLocalPort(4445);
        clientConfig.setUsername("CLIENT");

        serverConfig = new ConnectionConfig();
        serverConfig.setHostName("localhost");
        serverConfig.setHostPort(4444);
        serverConfig.setLocalPort(4444);
        serverConfig.setUsername("SERVER");

        client = new Client(clientConfig);
        server = new Server(serverConfig);

        Thread thread = new Thread(server);
        thread.start();

        while (!server.checkIfRunning()) {
            TimeUnit.SECONDS.sleep(1);
        }

        client.connect();
    }

    @After
    public void tearDown() throws Exception {
        client.kill();
        server.kill();
    }

    @Test(timeout=10000)
    public void checkIfClientPlayerListIsCorrect() {
        assertEquals(1, client.getPlayerListSize());
        assertEquals("CLIENT", client.getPlayers().get(0));
    }

    @Test(timeout=10000)
    public void checkIfServerBroadcastWorks() throws IOException, ClassNotFoundException {
        Packet message = Packet.newBuilder()
                            .setUUID("SERVER")
                            .setType(Packet.Type.TEST)
                            .addMessage("potato")
                            .addAddon("tomato")
                            .build();

        server.broadcast(message);
        Packet recvMessage = client.read();

        assertEquals("SERVER", recvMessage.getUUID());
        assertEquals(Packet.Type.TEST, recvMessage.getType());
        assertEquals("potato", recvMessage.getMessage(0));
        assertEquals("tomato", recvMessage.getAddon(0));
    }

    @Test(timeout=10000)
    public void checkIfServerBroadcastWorksForTwoClients() throws IOException, ClassNotFoundException {
        ConnectionConfig clientConfig2 = new ConnectionConfig();
        clientConfig.setHostName("localhost");
        clientConfig.setHostPort(4444);
        clientConfig.setLocalPort(4446);
        clientConfig.setUsername("CLIENT2");

        Client client2 = new Client(clientConfig2);
        client2.connect();

        assertEquals(2, client.getPlayerListSize());
        assertEquals("CLIENT", client.getPlayers().get(0));
        assertEquals("CLIENT2", client.getPlayers().get(1));

        assertEquals(2, client2.getPlayerListSize());
        assertEquals("CLIENT", client2.getPlayers().get(0));
        assertEquals("CLIENT2", client2.getPlayers().get(1));

        Packet message = Packet.newBuilder()
                .setUUID("SERVER")
                .setType(Packet.Type.TEST)
                .addMessage("potato")
                .addAddon("tomato")
                .build();

        server.broadcast(message);
        Packet recvMessage = client.read();
        Packet recvMessage2 = client2.read();

        assertEquals("SERVER", recvMessage.getUUID());
        assertEquals(Packet.Type.TEST, recvMessage.getType());
        assertEquals("potato", recvMessage.getMessage(0));
        assertEquals("tomato", recvMessage.getAddon(0));

        assertEquals("SERVER", recvMessage2.getUUID());
        assertEquals(Packet.Type.TEST, recvMessage2.getType());
        assertEquals("potato", recvMessage2.getMessage(0));
        assertEquals("tomato", recvMessage2.getAddon(0));
    }

    @Test(timeout=10000)
    public void checkIfClientSendWorks() throws IOException, InterruptedException {
        Packet message = Packet.newBuilder()
                .setUUID(client.getPlayerId().toString())
                .setType(Packet.Type.CONNECTION)
                .addMessage("CLIENT")
                .build();

        // Client sends message
        client.send(message);

        TimeUnit.SECONDS.sleep(1);

        // Server receives message
        Packet recvMessage = server.readPacket();

        assertEquals(client.getPlayerId().toString(), recvMessage.getUUID());
        assertEquals(Packet.Type.CONNECTION, recvMessage.getType());
        assertEquals("CLIENT", recvMessage.getMessage(0));
    }
}
