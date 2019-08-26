package Client.GUI.Element.Card;

import Constant.CardSettings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardArt extends ImageView {
    private final String name;

    public CardArt(String name, double width, double height) {
        this.name = name.trim();

        Image image = new Image(getCorrectImagePath(name));
        setImage(image);
        setSmooth(true);
        setCache(true);
        setFitWidth(width);
        setFitHeight(height);
    }

    private String getCorrectImagePath(String name) {
        if (CardSettings.DominionCards.CURSE_CARDS.contains(name))
            return "/CardArts/Curse/" + name + ".jpg";
        else if (CardSettings.DominionCards.KINGDOM_CARDS.contains(name))
            return "/CardArts/Kingdom/" + name + ".jpg";
        else if (CardSettings.DominionCards.TREASURE_CARDS.contains(name))
            return "/CardArts/Treasure/" + name + ".jpg";
        else if (CardSettings.DominionCards.VICTORY_CARDS.contains(name))
            return "/CardArts/Victory/" + name + ".jpg";
        else
            return "/CardArts/CardBack/Card_Back.png";
    }

    public String getName() {
        return name;
    }

    public void disable() {
        setOpacity(0.3);
        setDisable(true);
    }
}