package Server.Interfaces;

import Client.ClientInstance;

import java.io.PrintWriter;

public interface IServerListener {
    void clientConnected(ClientInstance client, PrintWriter out);
    void clientDisconnected(ClientInstance client);
    void receivedInput(ClientInstance client, String msg);
    void serverClosed();
}
