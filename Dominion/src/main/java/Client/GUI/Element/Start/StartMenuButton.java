package Client.GUI.Element.Start;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class StartMenuButton extends StackPane {
    private Text text;

    public StartMenuButton(String name, double width, double height) {
        text = new Text(name);
        text.setFont(text.getFont().font(height * 0.66));
        text.setFill(Color.WHITE);
        text.setTranslateX(10);

        Rectangle bg = new Rectangle(width, height);
        bg.setOpacity(0.6);
        bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(3.5));

        setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(bg, text);

        setOnMouseEntered(e -> {
            bg.setFill(Color.WHITE);
            text.setFill(Color.BLACK);
        });

        setOnMouseExited(e -> {
            bg.setFill(Color.BLACK);
            text.setFill(Color.WHITE);
        });

        DropShadow shadow = new DropShadow(50, Color.WHITE);
        shadow.setInput(new Glow());

        setOnMousePressed(e -> setEffect(shadow));
        setOnMouseReleased(e-> setEffect(null));
    }
}
