package Client.GUI.Element.Game;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class DeckDisplay extends StackPane {
    private StackPane numberOfCardsOverlay;
    private int numberOfCards;

    public DeckDisplay(final int numberOfCards) {
        Image cardBack = new Image("/CardArts/CardBack/Card_Back.png");
        ImageView deckArt = new ImageView(cardBack);
        deckArt.setFitWidth(75);
        deckArt.setFitHeight(119.25);

        this.numberOfCards = numberOfCards;

        getChildren().add(deckArt);
        getChildren().add(createCardCountOverlay());
    }

    private StackPane createCardCountOverlay() {
        numberOfCardsOverlay = new StackPane();

        Text costText = new Text(Integer.toString(numberOfCards));
        costText.setFill(Color.WHITE);
        costText.setTranslateX(3);

        Rectangle bg = new Rectangle(20, 20);
        bg.setArcHeight(7.5);
        bg.setArcWidth(7.5);
        bg.setFill(Color.RED);

        numberOfCardsOverlay.getChildren().addAll(bg, costText);
        numberOfCardsOverlay.setAlignment(Pos.TOP_LEFT);

        return numberOfCardsOverlay;
    }
}