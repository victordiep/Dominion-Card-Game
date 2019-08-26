package Client;
;
import Client.GUI.Screen.Menus.CardSelectPane;
import Client.GUI.Screen.Menus.StartPane;
import Game.Game;
import Server.ConnectionConfig;
import Server.Server;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import protobuf.PacketProtos.Packet;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static Constant.GuiSettings.WindowDimensions.WINDOW_HEIGHT;
import static Constant.GuiSettings.WindowDimensions.WINDOW_WIDTH;

/*
 * This class is a Singleton
 *
 * The Client.GUI for the game is implemented using the State design pattern
 *  -> This DominionManager class is considered the "Context"
 *      -> switchToScreen() is used to switch between different states
 *  -> Classes under the Screen package are considered States (i.e. SceneState)
 */

public class DominionManager extends Application {

    private static DominionManager instance = null;
    private static Game game = null;
    private List<String> kingdomCards;
    private boolean cardsHaveBeenSelected = false;

    private ConnectionConfig connectionConfig = null;

    private Client client = null;
    private Server server = null;

    private Scene scene;
    // Keeps track of the previous screen
    private Group previousScreen;

    public DominionManager(){
        super();
        synchronized(DominionManager.class){
            if(instance != null) throw new UnsupportedOperationException(
                    getClass() + " is singleton but constructor called more than once");
            instance = this;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dominion");
        primaryStage.setResizable(false);
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);

        this.connectionConfig = new ConnectionConfig();
        this.client = new Client();
        this.server = new Server();

        this.scene = new Scene(new StartPane());
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(event -> {
            if (client.checkIfRunning())
                client.kill();
            if (server.checkIfRunning())
                server.kill();
        });

        primaryStage.show();
    }

    public synchronized static DominionManager getInstance() {
        if (instance == null)
            instance = new DominionManager();

        return instance;
    }

    public ConnectionConfig getConnectionConfig() {
        return connectionConfig;
    }

    public void switchToScreen(Parent content) {
        previousScreen = new Group(scene.getRoot());
        this.scene.setRoot(content);
    }

    public void switchToPreviousScreen() {
        Group tempScreen = new Group(scene.getRoot());
        this.scene.setRoot(previousScreen);
        previousScreen = tempScreen;
    }

    public void hostLobby() {
        // A lobby is already being hosted
        if (server.checkIfRunning())
            return;

        try {
            server.initialize(connectionConfig);
            Thread serverThread = new Thread(this.server);
            serverThread.start();

            while (!server.checkIfRunning()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            joinLobby();

            switchToScreen(new CardSelectPane());
        } catch (IOException e) {

        }
    }

    public void joinLobby() {
        // We have already joined a lobby
        if (client.checkIfRunning())
            return;

        try {
            client.initialize(connectionConfig);
            Thread clientThread = new Thread(client);
            clientThread.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public synchronized List<String> getPlayers() {
        return client.getPlayers();
    }
    public synchronized int getNumOfPlayers() {
        return client.getPlayerListSize();
    }
    public synchronized boolean getIfCardsSelected() {
        return cardsHaveBeenSelected;
    }
    public synchronized List<String> getKingdomCards() {
        return kingdomCards;
    }
    public synchronized void setKingdomCards(List<String> kingdomCards) {
        this.kingdomCards = new ArrayList<>(kingdomCards);
        cardsHaveBeenSelected = true;
    }

    public synchronized void createGame(List<String> kingdomCards) {
        game = new Game(kingdomCards, client.getPlayerId(), client.getUsername(), client.getPlayerIds(), client.getPlayers());
    }

    public synchronized Game getGame() {
        return game;
    }

    public void processEvent() {
        //client.process()
    }

    public static void main(String[] args) {
        launch(args);
    }
}