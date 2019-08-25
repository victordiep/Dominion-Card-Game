package Client.GUI.Element.Game;

import Client.GUI.Element.Card.CardArt;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class HandDisplay extends HBox {
    List<String> cardNames;

    public HandDisplay() {
        setSpacing(5);
        setPadding(new Insets(20,10,10,10));
    }

    public void addCards(List<String> cardNames) {
        for (String cardName : cardNames) {
            CardArt cardArt = new CardArt(cardName);
            getChildren().add(cardArt);
        }

        this.cardNames = new ArrayList<>(cardNames);
    }
}
