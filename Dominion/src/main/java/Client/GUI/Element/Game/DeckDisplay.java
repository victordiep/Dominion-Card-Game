package Client.GUI.Element.Game;

import Client.DominionManager;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static Constant.GuiSettings.GameScreen.GAME_CARD_HEIGHT;
import static Constant.GuiSettings.GameScreen.GAME_CARD_WIDTH;

public class DeckDisplay extends StackPane {
    private StackPane numberOfCardsOverlay;
    private int numberOfCards;

    private Text cardCountText;

    public DeckDisplay(final int numberOfCards) {
        Image cardBack = new Image("/CardArts/CardBack/Card_Back.png");
        ImageView deckArt = new ImageView(cardBack);
        deckArt.setFitWidth(GAME_CARD_WIDTH);
        deckArt.setFitHeight(GAME_CARD_HEIGHT);

        this.numberOfCards = numberOfCards;

        getChildren().add(deckArt);
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

    public void updateCardCount() {
        cardCountText.setText(Integer.toString(DominionManager.getInstance().getGame().getDeckSize()));
    }
}