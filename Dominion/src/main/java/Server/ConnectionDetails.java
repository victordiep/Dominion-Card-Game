package Server;

import java.util.UUID;

/*
 * Used to store information of a connection from a client to a server
 */

public class ConnectionDetails {

    private final UUID playerId;
    private final String username;
    private final ServerListener listener;

    public ConnectionDetails(UUID playerId, String username, ServerListener listener) {
        this.playerId = playerId;
        this.username = username;
        this.listener = listener;
    }

    public UUID getPlayerId() { return playerId; }
    public String getUsername() { return username; }
    public ServerListener getListener() { return listener; }

    public void kill() {
        listener.kill();
    }
}
