package Client.GUI.Element.Game;

import Client.GUI.Element.Card.CardArt;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import static Constant.GuiSettings.CardSelectScreen.CARDS_PER_ROW;
import static Constant.GuiSettings.GameScreen.*;

public class HandDisplay extends ScrollPane {

    private List<CardArt> cardArts;

    public HandDisplay() {
        setPadding(new Insets(10,10,10,10));
        setStyle("-fx-background:transparent; -fx-background-color: rgba(0, 0, 0, 0.5)");
        setPrefHeight(300);

        cardArts = new ArrayList<>();
    }

    public final List<CardArt> getCardArts() { return cardArts; }

    public void addCards(List<String> cardNames) {
        int cardIndex  = 0;
        int numOfCards = cardNames.size();
        int numOfRows  = (int) Math.ceil((double)numOfCards / CARDS_PER_ROW);

        VBox display = new VBox();
        display.setSpacing(10);
        display.setPadding(new Insets(10,10,10,10));

        for (int row = 0; row < numOfRows; row++) {
            HBox displayRow = new HBox();
            displayRow.setSpacing(10);

            for (int col = 0; col < CARDS_PER_ROW && cardIndex < numOfCards; col++) {
                CardArt card = new CardArt(cardNames.get(cardIndex++), GAME_CARD_WIDTH, GAME_CARD_HEIGHT);

                displayRow.getChildren().add(card);
                cardArts.add(card);
            }
            display.getChildren().add(displayRow);
        }

        setContent(display);
    }
}
