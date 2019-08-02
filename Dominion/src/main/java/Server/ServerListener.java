package Server;

import Client.ClientInstance;
import Server.Interfaces.IServerListener;

import java.io.PrintWriter;

public class ServerListener implements IServerListener {
    @Override
    public void clientConnected(ClientInstance client, PrintWriter out) {

    }

    @Override
    public void clientDisconnected(ClientInstance client) {

    }

    @Override
    public void receivedInput(ClientInstance client, String msg) {

    }

    @Override
    public void serverClosed() {

    }
}
