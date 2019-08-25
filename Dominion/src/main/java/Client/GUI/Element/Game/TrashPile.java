package Client.GUI.Element.Game;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class TrashPile extends Tab {

    private VBox trash;

    public TrashPile() {
        setText("Trash Pile");

        ScrollPane scrollingTrash = new ScrollPane();
        scrollingTrash.setStyle("-fx-background: transparent; -fx-background-color: rgba(0, 0, 0, 0.8)");
        scrollingTrash.setVvalue(1.0);
        addTrash(scrollingTrash);

        setContent(scrollingTrash);
    }

    private void addTrash(ScrollPane scrollingTrash) {
        trash = new VBox();

    }
}