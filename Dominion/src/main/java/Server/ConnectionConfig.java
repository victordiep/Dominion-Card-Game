package Server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static Constant.LaunchDefaults.ServerSettings.*;

/*
 * Contains the details of the configurations used to establish a connection between the client and server
 */

public class ConnectionConfig {

    private int localPort; // Client's port
    private int hostPort; // Server's port

    private String hostname; // Server's hostname (IP address)
    private String username; // Username used for the game

    private int lobbySize; // Username used for the game

    public ConnectionConfig() {
        this.localPort = DEFAULT_PORT;
        this.hostPort = DEFAULT_PORT;
        this.hostname = DEFAULT_HOSTNAME;
        this.lobbySize = DEFAULT_LOBBY_SIZE;
        setUsername(DEFAULT_USERNAME);
    }

    // Username
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    // Client's port
    public int getLocalPort() { return localPort; }
    public void setLocalPort(int localPort) { this.localPort = localPort; }

    // Server's port
    public int getHostPort() { return hostPort; }
    public void setHostPort(int hostPort) { this.hostPort = hostPort; }

    // Server's hostname
    public InetAddress getHostName() throws UnknownHostException { return InetAddress.getByName(this.hostname); }
    public void setHostName(String hostName) { this.hostname = hostName; }

    // Lobby Size
    public int getLobbySize() { return lobbySize; }
    public void setLobbySize(int size) { this.lobbySize = size; }
}
