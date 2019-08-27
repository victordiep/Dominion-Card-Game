package Client.GUI.Element.Game;

import Client.DominionManager;
import Client.GUI.Element.Card.CardArt;
import Game.Card.Card;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

import static Constant.GuiSettings.GameScreen.GAME_CARD_HEIGHT;
import static Constant.GuiSettings.GameScreen.GAME_CARD_WIDTH;

public class TrashPile extends Tab {

    private VBox trash;

    public TrashPile() {
        setText("Trash Pile");

        ScrollPane scrollingTrash = new ScrollPane();
        scrollingTrash.setStyle("-fx-background: transparent; -fx-background-color: rgba(0, 0, 0, 0.8)");
        scrollingTrash.setVvalue(1.0);
        addTrash(scrollingTrash);

        setContent(scrollingTrash);
    }

    private void addTrash(ScrollPane scrollingTrash) {
        trash = new VBox();
        trash.setPadding(new Insets(10,10,10,10));

        updateTrash();

        scrollingTrash.setContent(trash);
    }

    public void updateTrash() {
        trash.getChildren().clear();

        for (Card card : DominionManager.getInstance().getGame().getTrash()) {
            trash.getChildren().add(new CardArt(card.getName(), GAME_CARD_WIDTH, GAME_CARD_HEIGHT));
        }
    }
}