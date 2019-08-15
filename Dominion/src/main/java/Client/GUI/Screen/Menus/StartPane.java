package Client.GUI.Screen.Menus;

import Client.Client;
import Client.GUI.Element.Background;
import Client.GUI.Element.Logo;
import Client.GUI.Element.Misc.TranslucentRectangle;
import Client.GUI.Element.Start.PlayerListMenu;
import Client.GUI.Element.Start.StartMenu;
import Client.GUI.Screen.SceneState;
import static Constant.GuiSettings.StartScreen.*;
import Server.ConnectionDetails;
import Server.Server;

import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * Contains forms to create a game or join a game
 *  -> Instantiates the client and/or server for GuiManager based on user input
 */

public class StartPane extends Pane implements SceneState {

    private ConnectionDetails connectionDetails;

    public StartPane(Client client, Server server) {
        setup();
    }

    @Override
    public void setup() {
        getChildren().addAll(new Background(), new Logo(LOGO_WIDTH, LOGO_HEIGHT, LOGO_X, LOGO_Y));

        addPlayerList();
        addStartForm();
    }

    private void addPlayerList() {
        getChildren().add(new PlayerListMenu(LOBBY_RECT_X, LOBBY_RECT_Y));
    }

    private void addStartForm() {
        getChildren().add(new StartMenu(START_RECT_X, START_RECT_Y));
    }

}
