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

import java.io.IOException;
import java.net.UnknownHostException;
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
            if (server.checkIfRunning())
                server.kill();
            if (client.checkIfRunning())
                client.kill();
        });

        primaryStage.show();
    }

    public static DominionManager getInstance() {
        if (instance == null)
            instance = new DominionManager();

        return instance;
    }

    public ConnectionConfig getConnectionConfig() {
        return connectionConfig;
    }

    public void switchToScreen(Parent content) {
        previousScreen = new Group(this.scene.getRoot());
        this.scene.setRoot(content);
    }

    public void switchToPreviousScreen() {
        Group tempScreen = new Group(this.scene.getRoot());
        this.scene.setRoot(previousScreen);
        previousScreen = tempScreen;
    }

    public void hostLobby() {
        try {
            this.server.initialize(connectionConfig);
            Thread serverThread = new Thread(this.server);
            serverThread.start();

            while (!this.server.checkIfRunning()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            joinLobby();

            switchToScreen(new CardSelectPane());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void joinLobby() {
        try {
            this.client.initialize(connectionConfig);
            Thread clientThread = new Thread(client);
            clientThread.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void createGame(List<String> kingdomCards) {
        for (String name : kingdomCards) {
            System.out.println(name);
        }
        System.out.println(kingdomCards.size());
    }


    public void processEvent() {
        //client.process()
    }

    public static void main(String[] args) {
        launch(args);
    }
}