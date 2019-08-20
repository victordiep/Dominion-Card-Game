package Client.EventHandler;

import Client.Client;
import Client.DominionManager;
import Client.EventHandler.Connection.HostGameEventHandler;
import Client.EventHandler.Connection.JoinGameEventHandler;
import Server.ConnectionConfig;
import Server.Server;

import java.io.IOException;

public class EventHandlers {

    private ConnectionConfig connectionConfig;

    private HostGameEventHandler hostGame;
    private JoinGameEventHandler joinGame;

    public EventHandlers(DominionManager dominionManager, ConnectionConfig connectionConfig, Client client, Server server) throws IOException {
        this.connectionConfig = connectionConfig;

        this.hostGame = new HostGameEventHandler(dominionManager, client, server, connectionConfig);
        this.joinGame = new JoinGameEventHandler(client, connectionConfig);
    }

    public ConnectionConfig getConnectionConfig() { return connectionConfig; }

    public HostGameEventHandler getHostGameEvent() { return hostGame; }
    public JoinGameEventHandler getJoinGameEvent() { return joinGame; }
}
