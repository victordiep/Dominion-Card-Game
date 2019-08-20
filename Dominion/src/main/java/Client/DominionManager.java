package Client;

import Client.EventHandler.EventHandlers;
import Client.GUI.Screen.Menus.StartPane;
import Server.ConnectionConfig;
import Server.Server;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static Constant.GuiSettings.WindowDimensions.WINDOW_HEIGHT;
import static Constant.GuiSettings.WindowDimensions.WINDOW_WIDTH;

/*
 * The Client.GUI for the game is implemented using the State design pattern
 *  -> This DominionManager class is considered the "Context"
 *      -> switchToScreen() is used to switch between different states
 *  -> Classes under the Screen package are considered States (i.e. SceneState)
 */

public class DominionManager extends Application {

    private ConnectionConfig connectionConfig;

    private Client client = null;
    private Server server = null;

    // Stores nodes to be displayed on the scene
    private Scene scene;

    // Stores nodes to be displayed on the scene
    private Parent content;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dominion");
        primaryStage.setResizable(false);
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);

        this.connectionConfig = new ConnectionConfig();
        this.client = new Client();
        this.server = new Server();

        this.scene = new Scene(new StartPane(new EventHandlers(this, this.connectionConfig, this.client, this.server)));
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void switchToScreen(Parent content) {
        this.scene.setRoot(content);
    }

    public static void main(String[] args) {
        launch(args);
    }
}