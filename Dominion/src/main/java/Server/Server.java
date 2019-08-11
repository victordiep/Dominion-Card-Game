package Server;

import Server.Interfaces.IServerListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class);

    private int port;
    private boolean open = true;
    private ServerSocket ss;
    private IServerListener serverListener;
    private ArrayList<Socket> clients = new ArrayList<>();

    public Server(int port, IServerListener listener) {
        serverListener = listener;

        try {
            ss = new ServerSocket(port);
            if (this.port == 0) {
                this.port = ss.getLocalPort();
            }
            else this.port = port;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void dispose(){
        open=false;
        try{ ss.close();
        }catch(IOException e){ e.printStackTrace(); }
        for(Socket s : clients){
            try{ s.close();
            }catch(Exception exception){ exception.printStackTrace(); }
        }
        clients.clear();
        clients = null;
        ss = null;
        serverListener.serverClosed();
        serverListener=null;
    }
}
