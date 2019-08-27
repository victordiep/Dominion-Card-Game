package Client.GUI.Element.Game;

import Client.DominionManager;
import Client.GUI.Element.Card.CardArt;
import Client.GUI.Screen.Game.GamePane;
import Constant.ActionInProgress;
import Constant.TurnPhase;
import Game.Game;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import protobuf.PacketProtos;

import java.io.IOException;

import static Constant.CardSettings.DominionCards.*;

public class PurchaseableCard extends StackPane {
    private ImageView cardArt;
    private StackPane costOverlay;

    private int numberInStock;

    private String cardName;
    private int cardCost;

    private Text stockText;

    public PurchaseableCard(String cardName, int stock, double width, double height) {
        this.cardName = cardName;
        cardArt = new CardArt(cardName, width, height);
        cardCost = CARD_COSTS.getOrDefault(cardName, -1);

        numberInStock = stock;

        getChildren().add(cardArt);
        getChildren().add(createStockOverlay());

        setOnMousePressed(e -> {
            try {
                PurchaseCard();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public String getCardName(){
        return cardName;
    }
    public ImageView getCardArt(){
        return cardArt;
    }

    private StackPane createStockOverlay() {
        costOverlay = new StackPane();

        stockText = new Text(Integer.toString(numberInStock));
        stockText.setFill(Color.WHITE);
        stockText.setTranslateX(3);

        Rectangle bg = new Rectangle(20, 20);
        bg.setArcHeight(7.5);
        bg.setArcWidth(7.5);
        bg.setFill(Color.RED);

        costOverlay.getChildren().addAll(bg, stockText);
        costOverlay.setAlignment(Pos.TOP_LEFT);

        return costOverlay;
    }

    public void updateStock() {
        stockText.setText(Integer.toString(DominionManager.getInstance().getGame().getStock(cardName)));
    }

    public void PurchaseCard() throws IOException {
        if (Game.getTurnPhase() == TurnPhase.BUY) {
            DominionManager.getInstance().getGame().purchaseCard(cardName);
            updateStock();
            DiscardDisplay.updateCardCount();
            DiscardDisplay.updateArt();
            GameDetails.updateBuys();
            GameDetails.updateCoins();
        }

        if (Game.getActionInProgress() == ActionInProgress.GAIN) {
            try {
                if (DominionManager.getInstance().getGame().putCardInDiscard(getCardName())) {
                    updateStock();
                    DiscardDisplay.updateCardCount();
                    DiscardDisplay.updateArt();

                    Game.setActionInProgress(ActionInProgress.NO_ACTION);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}