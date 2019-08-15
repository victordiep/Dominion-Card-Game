package Client.GUI.Element;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Logo extends ImageView {

    public Logo(double width, double height, double x, double y) {
        Image logoImage = new Image("/Main/Logo.png");
        setImage(logoImage);

        setFitWidth(width);
        setFitHeight(height);
        setLayoutX(x - getFitWidth()/2);
        setLayoutY(y);
    }

}
