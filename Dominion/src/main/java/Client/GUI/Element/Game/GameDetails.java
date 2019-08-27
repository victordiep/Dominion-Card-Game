package Client.GUI.Element.Game;

import Client.DominionManager;
import Client.GUI.Screen.Game.GamePane;
import Constant.ActionInProgress;
import Game.Game;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

public class GameDetails extends StackPane {
    private GamePane gamePane;

    private HBox turnDetails;
    private HBox currentStatus;

    private static Text action;
    private static Text buy;
    private static Text coins;

    private Text status;

    private Button endAction;
    private Button endTurn;

    public GameDetails(GamePane pane) {
        this.gamePane = pane;

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

        details.getChildren().addAll(createTurnDetails(), createStatusDetails());

        return details;
    }

    private HBox createTurnDetails() {
        turnDetails = new HBox();
        turnDetails.setSpacing(20);

        action = new Text();
        updateActions();
        action.setFill(Color.WHITE);
        buy = new Text();
        updateBuys();
        buy.setFill(Color.WHITE);
        coins = new Text();
        updateCoins();
        coins.setFill(Color.WHITE);

        turnDetails.getChildren().addAll(action, buy, coins);

        return turnDetails;
    }

    private HBox createStatusDetails() {
        currentStatus = new HBox();

        status = new Text("Waiting for your turn...");
        status.setFill(Color.PALETURQUOISE);

        currentStatus.getChildren().add(status);

        return currentStatus;
    }

    private VBox createEndTurn() {
        VBox buttons = new VBox();
        buttons.setSpacing(5);

        endTurn = new Button("End Turn");
        endTurn.setStyle("-fx-text-fill: white; -fx-background: black; -fx-background-color: indianred");
        endTurn.setDisable(true);

        endAction = new Button("End Action");
        endAction.setStyle("-fx-text-fill: black; -fx-background: black; -fx-background-color: turquoise");
        endAction.setDisable(true);

        endTurn.setOnMousePressed(e -> {
            setStatusDetails("Waiting for your turn...");
            try {
                gamePane.setEndTurn();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            endTurn.setDisable(true);
        });

        endAction();

        buttons.getChildren().addAll(endTurn, endAction);

        return buttons;
    }

    public static void updateActions() {
        action.setText(DominionManager.getInstance().getGame().getPlayerActions() + " Action");
    }

    public static void updateBuys() {
        buy.setText(DominionManager.getInstance().getGame().getPlayerBuys() + " Buy");
    }

    public static void updateCoins() { coins.setText(DominionManager.getInstance().getGame().getPlayerCoins() + " Coin"); }

    public void setStatusDetails(String statusDetails) {
        status.setText(statusDetails);
    }

    public void enableAction() {
        endAction.setDisable(false);
    }

    public Button specialAction() {
        endAction.setText("FINISH");
        endAction.setStyle("-fx-text-fill: white; -fx-background: black; -fx-background-color: green");
        return endAction;
    }

    public void endAction() {
        endAction.setOnMousePressed(e -> {
            if (Game.getActionInProgress() == ActionInProgress.NO_ACTION) {
                setStatusDetails("Buy Phase");
                Game.switchToBuyPhase();
                endAction.setDisable(true);
                endTurn.setDisable(false);
            }
        });
    }
}