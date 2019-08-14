package Server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static Constants.LaunchDefaults.ServerSettings.*;

/*
 * Contains the details of the configurations used to establish a connection between the client and server
 */

public class ConnectionConfig {

    private int localPort; // Client's port
    private int hostPort; // Server's port

    private InetAddress hostname; // Server's hostname (IP address)
    private String username; // Username used for the game

    public ConnectionConfig() throws UnknownHostException {
        this.localPort = DEFAULT_PORT;
        this.hostPort = DEFAULT_PORT;
        this.hostname = InetAddress.getByName(DEFAULT_HOSTNAME);
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
    public InetAddress getHostName() { return hostname; }
    public void setHostName(String hostName) throws UnknownHostException { this.hostname = InetAddress.getByName(hostName); }

}
