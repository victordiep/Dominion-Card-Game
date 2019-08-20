package Client.GUI.Screen.Menus;

import Client.GUI.Element.Background;
import Client.GUI.Element.Card.CardArt;
import Client.GUI.Screen.SceneState;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardSelectPane extends BorderPane implements SceneState {

    private VBox options;

    private Button btnSubmit;

    private static final int cardsPerRow = 6;

    private List<String> selectedCardNames;
    private HashMap<String, CardArt> availableCards;

    public CardSelectPane() {
        setup();
    }

    @Override
    public void setup() {
        getChildren().add(new Background());

        List<String> cards = new ArrayList<>();

        cards.add("Artisan");
        cards.add("Bandit");
        cards.add("Bureaucrat");
        cards.add("Cellar");
        cards.add("Chapel");
        cards.add("CouncilRoom");
        cards.add("Festival");
        cards.add("Gardens");
        cards.add("Harbinger");
        cards.add("Laboratory");
        cards.add("Library");
        cards.add("Market");
        cards.add("Merchant");
        cards.add("Militia");
        cards.add("Mine");
        cards.add("Moat");
        cards.add("Moneylender");
        cards.add("Poacher");
        cards.add("Remodel");
        cards.add("Sentry");
        cards.add("Smithy");
        cards.add("ThroneRoom");
        cards.add("Vassal");
        cards.add("Village");
        cards.add("Witch");
        cards.add("Workshop");

        setPrefSize(1080, 562);

        ScrollPane cardDisplay = new ScrollPane();
        cardDisplay.setStyle("-fx-background: transparent; -fx-background-color: rgba(0, 0, 0, 0.5)");
        cardDisplay.setPadding(new Insets(10, 50, 10, 50));
        cardDisplay.setPrefViewportWidth(650);

        selectedCardNames = new ArrayList<>();
        availableCards = new HashMap<>();

        setCardsToDisplay(cardDisplay, cards);

        options = new VBox();
        setOptions(options);

        setLeft(cardDisplay);
        setRight(options);
    }

    private void setCardsToDisplay(ScrollPane cardDisplay, List<String> cards) {
        int cardIndex  = 0;
        int numOfCards = cards.size();
        int numOfRows  = (int) Math.ceil((double)numOfCards / cardsPerRow);

        VBox display = new VBox();
        display.setSpacing(10);

        for (int row = 0; row < numOfRows; row++) {
            HBox displayRow = new HBox();
            displayRow.setSpacing(10);

            for (int col = 0; col < cardsPerRow && cardIndex < numOfCards; col++) {
                CardArt card = new CardArt(cards.get(cardIndex));
                setMouseEffects(card);

                availableCards.put(cards.get(cardIndex++), card);

                displayRow.getChildren().add(card);
            }

            display.getChildren().add(displayRow);
        }

        cardDisplay.setContent(display);
    }

    private void setMouseEffects(CardArt cardArt) {
        cardArt.setOnMousePressed( e -> {
            if (cardArt.getEffect() == null && selectedCardNames.size() < 10) {
                DropShadow shadow = new DropShadow(50, Color.YELLOW);
                shadow.setRadius(10);
                shadow.setInput(new Glow(20));
                cardArt.setEffect(shadow);

                selectedCardNames.add(cardArt.getName());
            }
            else if (cardArt.getEffect() != null) {
                selectedCardNames.remove(cardArt.getName());
                cardArt.setEffect(null);
            }

            if (selectedCardNames.size() == 10) {
                btnSubmit.setDisable(false);
            }
            else {
                btnSubmit.setDisable(true);
            }
        });

        cardArt.setOnMouseEntered( e -> {
            setCardArt(cardArt);
        });
    }

    private void setCardArt(ImageView cardArt) {
        StackPane enlargedCard = new StackPane();
        enlargedCard.setPadding(new Insets(10, 50, 0, 50));

        ImageView card = new ImageView(cardArt.getImage());
        card.setSmooth(true);
        card.setCache(true);
        card.setFitWidth(cardArt.getFitWidth() * 2);
        card.setFitHeight(cardArt.getFitHeight() * 2);

        enlargedCard.getChildren().add(card);

        options.getChildren().set(0, enlargedCard);
    }

    private void setOptions(VBox options) {
        options.setSpacing(20);

        // Setting up the zoomed in card
        StackPane enlargedCard = new StackPane();
        //enlargedCard.setPadding(new Insets(10, 0, 0, 0));

        Image imageBack = new Image("/CardArts/CardBack/Card_Back.png");
        ImageView cardBack = new ImageView(imageBack);
        cardBack.setSmooth(true);
        cardBack.setCache(true);
        cardBack.setFitWidth(200);
        cardBack.setFitHeight(318);

        enlargedCard.getChildren().add(cardBack);

        // Setting up the options
        VBox setupOptions = new VBox();
        setupOptions.setPadding(new Insets(10,10,10,10));
        setupOptions.setSpacing(10);
        setupOptions.setPrefSize(330, 213);
        setupOptions.setStyle("-fx-background: transparent; -fx-background-color: rgba(0, 0, 0, 0.8)");

        Text text = new Text("Select 10 cards or a preset");
        text.setFont(Font.font("Verdana", FontWeight.LIGHT, 15));
        text.setFill(Color.WHITE);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,0,20,0));
        grid.setHgap(10);
        grid.setVgap(10);

        Text presetText = new Text("Presets:");
        presetText.setFont(Font.font("Verdana", FontWeight.LIGHT, 12));
        presetText.setFill(Color.WHITE);
        grid.add(presetText, 0, 0);

        Button btnPresetFirstGame = new Button("First Game");
        btnPresetFirstGame.setMinWidth(80);
        btnPresetFirstGame.setFocusTraversable(false);
        btnPresetFirstGame.setStyle("-fx-text-fill: black; -fx-background: black; -fx-background-color: darkgrey");
        btnPresetFirstGame.setOnMousePressed( e -> {
            setPresetFirstGame();
        });
        grid.add(btnPresetFirstGame, 0, 1);

        Button btnPresetSizeDistortion = new Button("Size Distortion");
        btnPresetSizeDistortion.setDisable(true);
        btnPresetSizeDistortion.setMaxWidth(80);
        btnPresetSizeDistortion.setFocusTraversable(false);
        btnPresetSizeDistortion.setStyle("-fx-text-fill: black; -fx-background: black; -fx-background-color: darkgrey");
        btnPresetSizeDistortion.setOnMousePressed( e -> {
            setPresetSizeDistortion();
        });
        grid.add(btnPresetSizeDistortion, 1, 1);

        Button btnPresetDeckTop = new Button("Deck Top");
        btnPresetDeckTop.setDisable(true);
        btnPresetDeckTop.setMinWidth(80);
        btnPresetDeckTop.setFocusTraversable(false);
        btnPresetDeckTop.setStyle("-fx-text-fill: black; -fx-background: black; -fx-background-color: darkgrey");
        btnPresetDeckTop.setOnMousePressed( e -> {
            setPresetDeckTop();
        });
        grid.add(btnPresetDeckTop, 2, 1);

        Button btnPresetImprovements = new Button("Improvements");
        btnPresetImprovements.setDisable(true);
        btnPresetImprovements.setMaxWidth(80);
        btnPresetImprovements.setFocusTraversable(false);
        btnPresetImprovements.setStyle("-fx-text-fill: black; -fx-background: black; -fx-background-color: darkgrey");
        btnPresetImprovements.setOnMousePressed( e -> {
            setPresetImprovements();
        });
        grid.add(btnPresetImprovements, 0, 2);

        Button btnPresetSilverAndGold = new Button("Big Money");
        btnPresetSilverAndGold.setDisable(true);
        btnPresetSilverAndGold.setMinWidth(80);
        btnPresetSilverAndGold.setFocusTraversable(false);
        btnPresetSilverAndGold.setStyle("-fx-text-fill: black; -fx-background: black; -fx-background-color: darkgrey");
        btnPresetSilverAndGold.setOnMousePressed( e -> {
            setPresetSilverAndGold();
        });
        grid.add(btnPresetSilverAndGold, 1, 2);

        Button btnReset = new Button("Reset");
        btnReset.setMinWidth(80);
        btnReset.setFocusTraversable(false);
        btnReset.setStyle("-fx-text-fill: black; -fx-background: black; -fx-background-color: indianred");
        btnReset.setOnMousePressed( e -> {
            resetSelectedCards();
            selectedCardNames.clear();
        });
        grid.add(btnReset, 2, 2);

        btnSubmit = new Button("Host Game");
        btnSubmit.setDisable(true);
        btnSubmit.setFocusTraversable(false);
        btnSubmit.setStyle("-fx-text-fill: white; -fx-background: white; -fx-background-color: green");

        btnSubmit.setOnMousePressed( e -> {
            submitCards();
        });

        setupOptions.getChildren().addAll(text, grid, btnSubmit);

        options.getChildren().addAll(enlargedCard, setupOptions);
    }

    private void setPresetFirstGame() {
        selectedCardNames.clear();
        selectedCardNames.add(availableCards.get("Cellar").getName());
        selectedCardNames.add(availableCards.get("Market").getName());
        selectedCardNames.add(availableCards.get("Merchant").getName());
        selectedCardNames.add(availableCards.get("Militia").getName());
        selectedCardNames.add(availableCards.get("Mine").getName());
        selectedCardNames.add(availableCards.get("Moat").getName());
        selectedCardNames.add(availableCards.get("Remodel").getName());
        selectedCardNames.add(availableCards.get("Smithy").getName());
        selectedCardNames.add(availableCards.get("Village").getName());
        selectedCardNames.add(availableCards.get("Workshop").getName());
        setSelectOnCards();

        btnSubmit.setDisable(false);
    }

    private void setPresetSizeDistortion() {
        selectedCardNames.clear();
        selectedCardNames.add(availableCards.get("Artisan").getName());
        selectedCardNames.add(availableCards.get("Bandit").getName());
        selectedCardNames.add(availableCards.get("Bureaucrat").getName());
        selectedCardNames.add(availableCards.get("Chapel").getName());
        selectedCardNames.add(availableCards.get("Festival").getName());
        selectedCardNames.add(availableCards.get("Gardens").getName());
        selectedCardNames.add(availableCards.get("Sentry").getName());
        selectedCardNames.add(availableCards.get("Throne Room").getName());
        selectedCardNames.add(availableCards.get("Witch").getName());
        selectedCardNames.add(availableCards.get("Workshop").getName());
        setSelectOnCards();

        btnSubmit.setDisable(false);
    }

    private void setPresetDeckTop() {
        selectedCardNames.clear();
        selectedCardNames.add(availableCards.get("Artisan").getName());
        selectedCardNames.add(availableCards.get("Bureaucrat").getName());
        selectedCardNames.add(availableCards.get("Council Room").getName());
        selectedCardNames.add(availableCards.get("Festival").getName());
        selectedCardNames.add(availableCards.get("Harbinger").getName());
        selectedCardNames.add(availableCards.get("Laboratory").getName());
        selectedCardNames.add(availableCards.get("Moneylender").getName());
        selectedCardNames.add(availableCards.get("Sentry").getName());
        selectedCardNames.add(availableCards.get("Vassal").getName());
        selectedCardNames.add(availableCards.get("Village").getName());
        setSelectOnCards();

        btnSubmit.setDisable(false);
    }

    private void setPresetImprovements() {
        selectedCardNames.clear();
        selectedCardNames.add(availableCards.get("Artisan").getName());
        selectedCardNames.add(availableCards.get("Cellar").getName());
        selectedCardNames.add(availableCards.get("Market").getName());
        selectedCardNames.add(availableCards.get("Merchant").getName());
        selectedCardNames.add(availableCards.get("Mine").getName());
        selectedCardNames.add(availableCards.get("Moat").getName());
        selectedCardNames.add(availableCards.get("Moneylender").getName());
        selectedCardNames.add(availableCards.get("Poacher").getName());
        selectedCardNames.add(availableCards.get("Remodel").getName());
        selectedCardNames.add(availableCards.get("Witch").getName());
        setSelectOnCards();

        btnSubmit.setDisable(false);
    }

    private void setPresetSilverAndGold() {
        selectedCardNames.clear();
        selectedCardNames.add(availableCards.get("Bandit").getName());
        selectedCardNames.add(availableCards.get("Bureaucrat").getName());
        selectedCardNames.add(availableCards.get("Chapel").getName());
        selectedCardNames.add(availableCards.get("Harbinger").getName());
        selectedCardNames.add(availableCards.get("Laboratory").getName());
        selectedCardNames.add(availableCards.get("Merchant").getName());
        selectedCardNames.add(availableCards.get("Mine").getName());
        selectedCardNames.add(availableCards.get("Moneylender").getName());
        selectedCardNames.add(availableCards.get("Throne Room").getName());
        selectedCardNames.add(availableCards.get("Vassal").getName());
        setSelectOnCards();

        btnSubmit.setDisable(false);
    }

    private void resetSelectedCards() {
        for (Map.Entry<String, CardArt> card : availableCards.entrySet()) {
            card.getValue().setEffect(null);
        }

        btnSubmit.setDisable(true);
    }

    private void setSelectOnCards() {
        resetSelectedCards();

        for (String cardName : selectedCardNames) {
            DropShadow shadow = new DropShadow(50, Color.YELLOW);
            shadow.setRadius(10);
            shadow.setInput(new Glow(20));
            availableCards.get(cardName.trim()).setEffect(shadow);
        }
    }

    private void submitCards() {

    }
}
