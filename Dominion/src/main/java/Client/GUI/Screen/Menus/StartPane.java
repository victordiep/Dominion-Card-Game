package Client.GUI.Screen.Menus;

import Client.EventHandler.EventHandlers;
import Client.GUI.Element.Background;
import Client.GUI.Element.Logo;
import Client.GUI.Element.Start.PlayerListMenu;
import Client.GUI.Element.Start.StartMenu;
import Client.GUI.Screen.SceneState;
import static Constant.GuiSettings.StartScreen.*;

import javafx.scene.layout.Pane;

/*
 * Contains forms to create a game or join a game
 *  -> Instantiates the client and/or server for DominionManager based on user input
 */

public class StartPane extends Pane implements SceneState {

    private EventHandlers startMenuEventHandlers;

    public StartPane(EventHandlers startMenuEventHandlers) {
        this.startMenuEventHandlers = startMenuEventHandlers;

        setup();
    }

    @Override
    public void setup() {
        getChildren().addAll(new Background(), new Logo(LOGO_WIDTH, LOGO_HEIGHT, LOGO_X, LOGO_Y));

        addPlayerList();
        addStartMenu();
    }

    private void addPlayerList() {
        getChildren().add(new PlayerListMenu(LOBBY_RECT_X, LOBBY_RECT_Y));
    }

    private void addStartMenu() {
        getChildren().add(new StartMenu(START_RECT_X, START_RECT_Y, this.startMenuEventHandlers));
    }

}
