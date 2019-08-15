package Client.GUI.Element.Misc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class PlayerTag extends StackPane {
    private Text name;

    public PlayerTag(String name) {
        this.name = new Text(name);
        this.name.setFill(Color.PALEGREEN);

        Rectangle bg = new Rectangle(100, 30);
        bg.setArcHeight(7.5);
        bg.setArcWidth(7.5);
        bg.setOpacity(0.8);
        bg.setFill(Color.BLACK);

        setPadding(new Insets(5, 0, 5, 0));
        setAlignment(Pos.CENTER);
        getChildren().addAll(bg, this.name);
    }
}