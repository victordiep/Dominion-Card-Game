package Client.GUI.Element.Card;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardArt extends ImageView {
    private final String name;

    public CardArt(String name) {
        this.name = name.trim();

        Image image = new Image("/CardArts/Kingdom/" + this.name + ".jpg");
        setImage(image);
        setSmooth(true);
        setCache(true);
        setFitWidth(100);
        setFitHeight(159);
    }

    public String getName() {
        return name;
    }
}