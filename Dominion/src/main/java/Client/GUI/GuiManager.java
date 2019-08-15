package Client.GUI;

import Client.Client;
import Client.GUI.Screen.Menus.StartPane;
import Server.Server;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static Constant.GuiSettings.WindowDimensions.WINDOW_HEIGHT;
import static Constant.GuiSettings.WindowDimensions.WINDOW_WIDTH;

/*
 * The Client.GUI for the game is implemented using the State design pattern
 *  -> This GuiManager class is considered the "Context"
 *      -> switchToScreen() is used to switch between different states
 *  -> Classes under the Screen package are considered States (i.e. SceneState)
 */

public class GuiManager extends Application {

    private Client client;
    private Server server;

    // Stores nodes to be displayed on the scene
    private Parent content;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Dominion");
        primaryStage.setResizable(false);
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);

        switchToScreen(new StartPane(client, server));
        Scene scene = new Scene(content);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void switchToScreen(Parent content) {
        this.content = content;
    }

    public static void main(String[] args) {
        launch(args);
    }
}