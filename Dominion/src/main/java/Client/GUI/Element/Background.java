package Client.GUI.Element;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static Constant.GuiSettings.WindowDimensions.*;

public class Background extends ImageView {

    public Background() {
        Image image = new Image("/Main/Border_VillageArt.png");
        setImage(image);
        setFitWidth(WINDOW_WIDTH);
        setFitHeight(WINDOW_HEIGHT);
    }

}