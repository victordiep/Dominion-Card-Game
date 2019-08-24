package Client;
;
import Client.GUI.Screen.Menus.CardSelectPane;
import Client.GUI.Screen.Menus.StartPane;
import Server.ConnectionConfig;
import Server.Server;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.UnknownHostException;
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

    private ConnectionConfig connectionConfig = null;

    private Client client = null;
    private Server server = null;

    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;

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
        return instance;
    }

    public ConnectionConfig getConnectionConfig() {
        return connectionConfig;
    }

    public void switchToScreen(Parent content) {
        this.scene.setRoot(content);
    }

    public void hostGame() {
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

            joinGame();

            switchToScreen(new CardSelectPane());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void joinGame() {
        try {
            this.client.initialize(connectionConfig);
            Thread clientThread = new Thread(client);
            clientThread.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void processEvent() {
        //client.process()
    }

    public static void main(String[] args) {
        launch(args);
    }
}