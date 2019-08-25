package Client.GUI.Element.Game;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameDetails extends StackPane {
    private HBox turnDetails;
    private HBox currentAction;

    public GameDetails() {
        setPadding(new Insets(0, 10, 0, 10));

        Rectangle bg = new Rectangle(565, 60);
        bg.setArcHeight(7.5);
        bg.setArcWidth(7.5);
        bg.setOpacity(0.8);
        bg.setFill(Color.BLACK);

        BorderPane details = createInfo();

        getChildren().addAll(bg, details);
    }

    private BorderPane createInfo() {
        BorderPane gameDetails = new BorderPane();
        gameDetails.setPadding(new Insets(5,5,5,5));

        gameDetails.setCenter(createDetails());
        gameDetails.setRight(createEndTurn());
        return gameDetails;
    }

    private VBox createDetails() {
        VBox details = new VBox();

        details.getChildren().addAll(createTurnDetails(), createActionDetails());

        return details;
    }

    private HBox createTurnDetails() {
        turnDetails = new HBox();
        turnDetails.setSpacing(20);

        Text action = new Text("1 Action");
        action.setFill(Color.WHITE);
        Text buy = new Text("1 Buy");
        buy.setFill(Color.WHITE);
        Text coins = new Text("0 Coin");
        coins.setFill(Color.WHITE);

        turnDetails.getChildren().addAll(action, buy, coins);

        return turnDetails;
    }

    private HBox createActionDetails() {
        currentAction = new HBox();

        Text action = new Text("Waiting for your turn...");
        action.setFill(Color.PALETURQUOISE);

        currentAction.getChildren().add(action);

        return currentAction;
    }

    private VBox createEndTurn() {
        VBox buttons = new VBox();
        buttons.setSpacing(5);

        Button endTurn = new Button("End Turn");
        endTurn.setStyle("-fx-text-fill: white; -fx-background: black; -fx-background-color: indianred");
        endTurn.setDisable(true);

        Button endAction = new Button("End Action");
        endAction.setStyle("-fx-text-fill: black; -fx-background: black; -fx-background-color: turquoise");
        endAction.setDisable(true);

        endTurn.setOnMousePressed(e -> {

        });

        endAction.setOnMousePressed(e -> {

        });

        buttons.getChildren().addAll(endTurn, endAction);

        return buttons;
    }

    public void setActions(int numOfActions) {
        Text action = new Text(numOfActions + " Action");
        action.setFill(Color.WHITE);

        currentAction.getChildren().set(0, action);
    }

    public void setBuys(int numOfBuys) {
        Text buy = new Text(numOfBuys + " Buy");
        buy.setFill(Color.WHITE);

        currentAction.getChildren().set(0, buy);
    }

    public void setCoins(int numOfCoins) {
        Text coins = new Text(numOfCoins + " Coin");
        coins.setFill(Color.WHITE);

        currentAction.getChildren().set(0, coins);
    }

    public void setActionDetails(String actionDetails) {
        Text action = new Text(actionDetails);
        action.setFill(Color.PALETURQUOISE);

        currentAction.getChildren().set(0, action);
    }
}