package Client.GUI.Element.Game;

import Client.GUI.Element.Card.CardArt;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static Constant.CardSettings.DominionCards.KINGDOM_CARD_COSTS;
import static Constant.GuiSettings.GameSettings.KINGDOM_CARD_PILE_SIZE;

public class PurchaseableCard extends StackPane {
    private ImageView cardArt;
    private StackPane costOverlay;

    private int numberInStock;
    private int cardCost;

    public PurchaseableCard(String cardName) {
        cardArt = new CardArt(cardName);

        cardCost = KINGDOM_CARD_COSTS.get(cardName);
        numberInStock = KINGDOM_CARD_PILE_SIZE;

        getChildren().add(cardArt);
        getChildren().add(createStockOverlay());

        setOnMousePressed(e -> {
            PurchaseCard();
        });
    }

    public ImageView getCardArt(){
        return cardArt;
    }

    private StackPane createStockOverlay() {
        costOverlay = new StackPane();

        Text costText = new Text(Integer.toString(numberInStock));
        costText.setFill(Color.WHITE);
        costText.setTranslateX(3);

        Rectangle bg = new Rectangle(20, 20);
        bg.setArcHeight(7.5);
        bg.setArcWidth(7.5);
        bg.setFill(Color.RED);

        costOverlay.getChildren().addAll(bg, costText);
        costOverlay.setAlignment(Pos.TOP_LEFT);

        return costOverlay;
    }

    public void PurchaseCard() {
        if (numberInStock > 0) {
            numberInStock--;
            getChildren().set(1, createStockOverlay());
        }
    }
}