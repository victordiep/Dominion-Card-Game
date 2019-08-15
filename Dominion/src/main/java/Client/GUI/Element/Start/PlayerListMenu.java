package Client.GUI.Element.Start;

import Client.GUI.Element.Misc.PlayerTag;
import Client.GUI.Element.Misc.TranslucentRectangle;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static Constant.GuiSettings.StartScreen.*;

public class PlayerListMenu extends BorderPane {
    private VBox players;

    public PlayerListMenu(double x, double y) {
        // Background
        Rectangle lobby_bg = new TranslucentRectangle(MENU_RECT_WIDTH, MENU_RECT_HEIGHT, x, y,
                                                        Color.BLACK, 0.5, 1.5);

        // Player Lobby
        VBox lobby = new VBox();
        lobby.setPadding(new Insets(y + 10, x, y, x + 20));
        lobby.setSpacing(10);

        Text lobbyText = new Text("Player Lobby:");
        lobbyText.setFont(Font.font("Verdana", FontWeight.LIGHT, 20));
        lobbyText.setFill(Color.WHITE);

        // Player Name Tags
        players = new VBox();
        players.setSpacing(5);

        lobby.getChildren().add(lobbyText);
        lobby.getChildren().add(players);

        // Putting it together
        getChildren().addAll(lobby_bg, lobby);
    }

    public void addPlayer(final String username) {
        PlayerTag playerName = new PlayerTag(username);

        players.getChildren().add(playerName);
    }
}