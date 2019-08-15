package Client.GUI.Element.Misc;

import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Constant.GuiSettings.StartScreen.*;
import static Constant.GuiSettings.StartScreen.LOBBY_RECT_Y;

/*
 * A commonly used background used in the game for UI elements
 */

public class TranslucentRectangle extends Rectangle {

    public TranslucentRectangle(double width, double height, double x, double y, Color color, double opacity, double blur) {
        setWidth(width);
        setHeight(height);
        setLayoutX(x);
        setLayoutY(y);
        setOpacity(opacity);
        setFill(color);
        setEffect(new GaussianBlur(blur));
    }

}
