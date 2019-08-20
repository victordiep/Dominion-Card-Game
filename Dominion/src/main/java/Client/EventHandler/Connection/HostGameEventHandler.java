package Client.EventHandler.Connection;

import Client.Client;
import Client.DominionManager;
import Client.GUI.Screen.Menus.CardSelectPane;
import Server.Server;
import Server.ConnectionConfig;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
 * EventHandler that handles hosting of a game upon pressing the "Host Game" button
 */

public class HostGameEventHandler implements EventHandler<ActionEvent> {

    private DominionManager dominionManager;

    private Client client;
    private Server server;

    private ConnectionConfig connectionConfig;

    public HostGameEventHandler(DominionManager dominionManager, Client client, Server server, ConnectionConfig connectionConfig) {
        this.dominionManager = dominionManager;
        this.client = client;
        this.server = server;
        this.connectionConfig = connectionConfig;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            this.server.initialize(connectionConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread serverThread = new Thread(this.server);
        serverThread.start();

        while (!this.server.checkIfRunning()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            this.client.initialize(connectionConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread clientThread = new Thread(this.client);
        clientThread.start();

        dominionManager.switchToScreen(new CardSelectPane());
    }

}
