package Client.GUI.Element.Game;

import Client.DominionManager;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class DiscardDisplay extends StackPane {
    private StackPane numberOfCardsOverlay;

    private int numberOfCards;

    private Text cardCountText;

    public DiscardDisplay() {
        Rectangle bg = new Rectangle();
        bg.setFill(Color.WHITE);
        bg.setOpacity(0.3);
        bg.setArcWidth(7.5);
        bg.setArcHeight(7.5);
        bg.setWidth(75);
        bg.setHeight(119.25);

        numberOfCards = 0;

        getChildren().add(bg);
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
        cardCountText.setText(Integer.toString(DominionManager.getInstance().getGame().getDiscardSize()));
    }
}