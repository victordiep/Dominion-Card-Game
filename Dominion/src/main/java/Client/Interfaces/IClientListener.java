package Client.Interfaces;

public interface IClientListener {
    void unknownHost();
    void couldNotConnect();
    void receivedInput(String msg);
    void serverClosed();
    void disconnected();
    void connectedToServer();
}
