package Client.GUI.Element.Game;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Log extends Tab {
    private VBox log;

    public Log() {
        setText("Game Log");

        ScrollPane scrollingLog = new ScrollPane();
        scrollingLog.setStyle("-fx-background: transparent; -fx-background-color: rgba(0, 0, 0, 0.8)");
        scrollingLog.setVvalue(1.0);
        addLog(scrollingLog);

        setContent(scrollingLog);
    }

    private void addLog(ScrollPane scrollingLog) {
        log = new VBox();
        log.setSpacing(1);
        scrollingLog.setContent(log);

        addEvent("Game has started!");
    }

    public void addTurn(String player) {
        Text empty = new Text("");
        Text text = new Text("It's player " + player + "'s turn!");
        text.setTranslateX(5);
        text.setFill(Color.INDIANRED);
        log.getChildren().addAll(empty, text);
    }

    public void addEntry(String player, String entry) {
        Text text = new Text(entry);
        text.setTranslateX(5);
        text.setFill(Color.WHITE);
        log.getChildren().add(text);
    }

    public void addAction(String player, String actionDesc) {
        Text text = new Text(actionDesc);
        text.setTranslateX(15);
        text.setFill(Color.WHITE);
        log.getChildren().add(text);
    }

    public void addEvent(String event) {
        Text text = new Text(event);
        text.setTranslateX(5);
        text.setFill(Color.PALEGREEN);
        log.getChildren().add(text);
    }
}