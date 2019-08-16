package Client.EventHandler.Connection;

import Client.Client;
import Server.ConnectionConfig;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.net.UnknownHostException;

public class JoinGameEventHandler implements EventHandler<ActionEvent> {

    private Client client;
    private ConnectionConfig connectionConfig;

    public JoinGameEventHandler(Client client, ConnectionConfig connectionConfig){
        this.client = client;
        this.connectionConfig = connectionConfig;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            this.client.initialize(connectionConfig);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Thread clientThread = new Thread(client);
        clientThread.start();
    }

}