package Client.GUI.Element.Game;

import Client.DominionManager;
import Constant.CardSettings;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static Constant.GuiSettings.GameScreen.GAME_CARD_HEIGHT;
import static Constant.GuiSettings.GameScreen.GAME_CARD_WIDTH;

public class DiscardDisplay extends StackPane {
    private StackPane numberOfCardsOverlay;

    private int numberOfCards;

    private static Text cardCountText;
    private static ImageView discardArt;

    public DiscardDisplay() {
        discardArt = new ImageView();
        discardArt.setFitWidth(GAME_CARD_WIDTH);
        discardArt.setFitHeight(GAME_CARD_HEIGHT);

        Rectangle bg = new Rectangle();
        bg.setFill(Color.WHITE);
        bg.setOpacity(0.3);
        bg.setArcWidth(7.5);
        bg.setArcHeight(7.5);
        bg.setWidth(GAME_CARD_WIDTH);
        bg.setHeight(GAME_CARD_HEIGHT);

        numberOfCards = 0;

        getChildren().addAll(bg,discardArt);
        getChildren().add(createCardCountOverlay());
    }

    private StackPane createCardCountOverlay() {
        numberOfCardsOverlay = new StackPane();

        cardCountText = new Text();
        updateCardCount();
        cardCountText.setFill(Color.WHITE);
        cardCountText.setTranslateX(3);

        Rectangle bg = new Rectangle(20, 20);
        bg.setArcHeight(7.5);
        bg.setArcWidth(7.5);
        bg.setFill(Color.RED);

        numberOfCardsOverlay.getChildren().addAll(bg, cardCountText);
        numberOfCardsOverlay.setAlignment(Pos.TOP_LEFT);

        return numberOfCardsOverlay;
    }

    public static void updateArt() {
        String topCardOnDiscard = DominionManager.getInstance().getGame().peekDiscard();

        if (topCardOnDiscard == null) {
            discardArt.setImage(null);
        }
        else {
            String name;

            if (CardSettings.DominionCards.CURSE_CARDS.contains(topCardOnDiscard))
                name = "/CardArts/Curse/" + topCardOnDiscard + ".jpg";
            else if (CardSettings.DominionCards.KINGDOM_CARDS.contains(topCardOnDiscard))
                name = "/CardArts/Kingdom/" + topCardOnDiscard + ".jpg";
            else if (CardSettings.DominionCards.TREASURE_CARDS.contains(topCardOnDiscard))
                name = "/CardArts/Treasure/" + topCardOnDiscard + ".jpg";
            else if (CardSettings.DominionCards.VICTORY_CARDS.contains(topCardOnDiscard))
                name = "/CardArts/Victory/" + topCardOnDiscard + ".jpg";
            else
                return; // Could not find card

            Image cardArt = new Image(name);
            discardArt.setImage(cardArt);
        }
    }

    public static void updateCardCount() {
        cardCountText.setText(Integer.toString(DominionManager.getInstance().getGame().getDiscardSize()));
        updateArt();
    }
}