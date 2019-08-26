package Client.GUI.Screen.Game;

import Client.GUI.Element.Background;
import Client.GUI.Element.Game.*;
import Client.GUI.Element.Misc.PlayerTag;
import static Constant.CardSettings.DominionCards.*;
import static Constant.GuiSettings.GameScreen.*;

import Client.GUI.Screen.SceneState;
import Game.Game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class GamePane extends BorderPane implements SceneState {

    private Game game;

    private List<String> kingdomCards;
    private List<String> playerNames;

    private HBox kingdomCardDisplay;
    private VBox basicCardDisplay;
    private HBox deckDisplay;

    private GameDetails turnDetails;
    private HandDisplay handDisplay;

    public GamePane(Game game, List<String> playerNames) {
        this.game = game;
        this.playerNames = new ArrayList<>(playerNames);

        setup();
    }

    /*
     * Contains options to purchase treasure and victory cards and the player's deck and discard pile
     *
     */
    private void setupLeft(VBox leftPane) {
        leftPane.setPrefWidth(245);
        leftPane.setPrefHeight(562);

        StackPane leftMenu = new StackPane();

        BorderPane leftMenuContainer = new BorderPane();

        // Background
        Rectangle bg = new Rectangle(leftPane.getPrefWidth(), leftPane.getPrefHeight());
        bg.setOpacity(0.6);
        bg.setFill(Color.BLACK);

        // Treasure, Victory and Curse cards
        basicCardDisplay = createBasicCards();
        basicCardDisplay.setSpacing(5);
        basicCardDisplay.setPadding(new Insets(10, 10, 10, 10));

        deckDisplay = createDeckAndDiscard();
        deckDisplay.setSpacing(5);
        deckDisplay.setPadding(new Insets(0, 10, 10, 10));

        leftMenuContainer.setTop(basicCardDisplay);
        leftMenuContainer.setBottom(deckDisplay);
        leftMenu.getChildren().addAll(bg, leftMenuContainer);
        leftPane.getChildren().add(leftMenu);
    }

    private VBox createBasicCards() {
        VBox basicCards = new VBox();

        HBox treasureCards = new HBox();
        for (String name : TREASURE_CARDS) {
            PurchaseableCard purchaseableCard = new PurchaseableCard(name, game.getStock(name), GAME_CARD_WIDTH, GAME_CARD_HEIGHT);

            purchaseableCard.setOnMouseEntered(e -> {
                setCardArt(purchaseableCard.getCardArt());
            });

            treasureCards.getChildren().add(purchaseableCard);
        }

        HBox victoryCards = new HBox();
        for (String name : VICTORY_CARDS) {
            PurchaseableCard purchaseableCard = new PurchaseableCard(name, game.getStock(name), GAME_CARD_WIDTH, GAME_CARD_HEIGHT);

            purchaseableCard.setOnMouseEntered(e -> {
                setCardArt(purchaseableCard.getCardArt());
            });

            victoryCards.getChildren().add(purchaseableCard);
        }

        HBox curseCards = new HBox();
        for (String name : CURSE_CARDS) {
            PurchaseableCard purchaseableCard = new PurchaseableCard(name, game.getStock(name), GAME_CARD_WIDTH, GAME_CARD_HEIGHT);

            purchaseableCard.setOnMouseEntered(e -> {
                setCardArt(purchaseableCard.getCardArt());
            });

            curseCards.getChildren().add(purchaseableCard);
        }

        basicCards.getChildren().addAll(treasureCards, victoryCards, curseCards);

        return basicCards;
    }

    private HBox createDeckAndDiscard() {
        HBox deckAndDiscard = new HBox();

        DeckDisplay deckDisplay = new DeckDisplay(game.getDeckSize());
        DiscardDisplay discardPileDisplay = new DiscardDisplay();

        deckAndDiscard.getChildren().addAll(deckDisplay, discardPileDisplay);

        return deckAndDiscard;
    }


    /*
     * Contains the player names, kingdom cards, your player details (e.g. actions, buys, coins, deck, discard and hand)
     */
    private void setupCenter(VBox centerPane) {
        centerPane.setPrefWidth(580);

        // Player Tags
        HBox playerTags = createPlayerTags();
        playerTags.setAlignment(Pos.TOP_LEFT);

        // Kingdom Card Display
        kingdomCardDisplay = new HBox();
        kingdomCardDisplay.setSpacing(10);
        kingdomCardDisplay.setPadding(new Insets(10, 10, 10, 10));

        VBox kingdomCards = createKingdomCards();


        Image cardImage = new Image("/CardArts/CardBack/Card_Back.png");
        ImageView hoveredCard = new ImageView(cardImage);
        hoveredCard.setSmooth(true);
        hoveredCard.setCache(true);
        hoveredCard.setFitWidth(160);
        hoveredCard.setFitHeight(255);

        kingdomCardDisplay.getChildren().addAll(kingdomCards, hoveredCard);

        // Turn Information
        turnDetails = new GameDetails();

        // HandDisplay
        handDisplay = new HandDisplay();

        makeHandInteractable();

        centerPane.getChildren().addAll(playerTags, kingdomCardDisplay, turnDetails, handDisplay);
    }

    private HBox createPlayerTags() {
        HBox playerTags = new HBox();
        playerTags.setPadding(new Insets(10, 10, 0, 10));
        playerTags.setSpacing(10);

        StackPane playersBox = new StackPane();
        Text playersLabel = new Text("PLAYERS:");
        playersLabel.setFill(Color.WHITE);

        Rectangle bg = new Rectangle(100, 30);
        bg.setArcHeight(7.5);
        bg.setArcWidth(7.5);
        bg.setFill(Color.DIMGREY);

        playersBox.getChildren().addAll(bg, playersLabel);
        playerTags.getChildren().add(playersBox);

        for (String name : playerNames) {
            PlayerTag nameTag = new PlayerTag(name);
            playerTags.getChildren().add(nameTag);
        }

        return playerTags;
    }

    private VBox createKingdomCards() {
        VBox cardDisplay = new VBox();
        cardDisplay.setSpacing(5);
        cardDisplay.setPadding(new Insets(10, 0, 0, 0));

        int cardIndex = 0;

        for (int row = 0; row < 2; row++) {
            HBox cardRow = new HBox();
            cardRow.setSpacing(5);

            for (int col = 0; col < 5; col++) {
                String name = kingdomCards.get(cardIndex++);
                PurchaseableCard purchaseableCard = new PurchaseableCard(name, game.getStock(name), GAME_CARD_WIDTH, GAME_CARD_HEIGHT);
                cardRow.getChildren().add(purchaseableCard);

                purchaseableCard.setOnMouseEntered(e -> {
                    setCardArt(purchaseableCard.getCardArt());
                });
            }

            cardDisplay.getChildren().add(cardRow);
        }

        return cardDisplay;
    }

    /*
     * Contains the game log and the trash pile
     */
    private void setupRight(TabPane rightPane) {
        rightPane.setPrefWidth(235);
        rightPane.setPrefHeight(562);
        rightPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        rightPane.getStyleClass().add("floating");
        rightPane.setStyle("-fx-background: transparent; -fx-background-color: rgba(0, 0, 0, 0.8)");

        Log log = new Log();
        TrashPile trash = new TrashPile();

        rightPane.getTabs().addAll(log, trash);
    }

    private void setCardArt(ImageView cardView) {
        ImageView card = new ImageView(cardView.getImage());
        card.setSmooth(true);
        card.setCache(true);
        card.setFitWidth(160);
        card.setFitHeight(255);

        kingdomCardDisplay.getChildren().set(1, card);
    }

    private void makeHandInteractable() {
        for (Node card: handDisplay.getChildren()) {
            card.setOnMouseEntered(e -> {
                setCardArt((ImageView) card);
            });

            card.setOnMousePressed(e -> {
                //GUIHandler.handlePlayCard(cardName)
            });
        }
    }

    @Override
    public void setup() {
        getChildren().addAll(new Background());

        this.kingdomCards = new ArrayList<>(game.getKingdomCards());

        VBox leftPane = new VBox();
        setupLeft(leftPane);

        VBox centerPane = new VBox();
        setupCenter(centerPane);

        TabPane rightPane = new TabPane();
        setupRight(rightPane);

        setLeft(leftPane);
        setRight(rightPane);
        setCenter(centerPane);
    }
}