package Client.EventHandler.Connection;

import Client.Client;
import Client.GUI.GuiManager;
import Server.ConnectionConfig;
import Server.Server;

import java.io.IOException;

public class StartMenuEventHandlers {

    private ConnectionConfig connectionConfig;

    private HostGameEventHandler hostGame;
    private JoinGameEventHandler joinGame;

    public StartMenuEventHandlers(GuiManager guiManager, ConnectionConfig connectionConfig, Client client, Server server) throws IOException {
        this.connectionConfig = connectionConfig;

        this.hostGame = new HostGameEventHandler(guiManager, client, server, connectionConfig);
        this.joinGame = new JoinGameEventHandler(client, connectionConfig);
    }

    public ConnectionConfig getConnectionConfig() { return connectionConfig; }

    public HostGameEventHandler getHostGameEvent() { return hostGame; }
    public JoinGameEventHandler getJoinGameEvent() { return joinGame; }
}
