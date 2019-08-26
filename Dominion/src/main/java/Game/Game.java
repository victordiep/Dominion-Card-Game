package Game;

import static Constant.GuiSettings.GameSettings.*;

import Constant.TurnPhase;
import Game.Card.Card;
import Game.Card.CardFactory;
import Game.Card.SupplyPile;
import Game.Player.Player;

import java.util.*;


public class Game {

    private Player player;
    private final Map<UUID, String> playerNames;
    private final int numOfPlayers;

    private final List<String> kingdomCards;

    // Card piles available to all players
    private Map<String, SupplyPile> supply;
    private List<Card> trash;

    // Used to create cards
    private CardFactory cardFactory;

    private static TurnPhase turnPhase = TurnPhase.INACTIVE;

    public Game(List<String> kingdomCards, UUID playerId, String name, Map<UUID, String> players) {
        player = new Player(playerId, name);
        this.playerNames = new HashMap<>(players);
        numOfPlayers = this.playerNames.size();

        this.kingdomCards = new ArrayList<>(kingdomCards);
        cardFactory = new CardFactory(this);

        supply = new HashMap<>();
        trash = new ArrayList<>();

        turnPhase = TurnPhase.WAITING;

        setupGame();
    }

    private void setupGame() {
        populateSupply(supply);
        distributeStartingCards();
    }

    public void populateSupply(Map<String, SupplyPile> supply) {
        /*
         * KINGDOM CARDS
         */
        for (String name : kingdomCards) {
            supply.put(name, new SupplyPile(createCard(name), KINGDOM_CARD_PILE_SIZE));
        }

        /*
         * BASIC CARDS
         */
        // Treasure CardPile
        supply.put("Copper", new SupplyPile(createCard("Copper"), NUMBER_OF_COPPER)); // Every players starts with 7 Copper
        supply.put("Silver", new SupplyPile(createCard("Silver"), NUMBER_OF_SILVER));
        supply.put("Gold", new SupplyPile(createCard("Gold"), NUMBER_OF_GOLD));

        // Victory CardPile
        supply.put("Estate", new SupplyPile(createCard("Estate"), NUMBER_OF_ESTATE)); // Every players starts with 3 Estate
        supply.put("Duchy", new SupplyPile(createCard("Duchy"), NUMBER_OF_DUCHY));
        supply.put("Province", new SupplyPile(createCard("Province"), NUMBER_OF_PROVINCE));

        // Curse CardPile
        supply.put("Curse", new SupplyPile(createCard("Curse"), NUMBER_OF_CURSE));
    }

    private void distributeStartingCards() {
        // Each player starts with 7 copper and 3 estates, so we need to reduce the stock
        supply.get("Copper").reduceStock(numOfPlayers * 7);
        supply.get("Estate").reduceStock(numOfPlayers * 3);

        for (int i = 0; i < 7; i++) {
            player.addCardIntoDeck(cardFactory.createCard("Copper"));
        }

        for (int i = 0; i < 3; i++) {
            player.addCardIntoDeck(cardFactory.createCard("Estate"));
        }

        player.shuffleDeck();
        player.drawCards(HAND_SIZE);
    }

    public final int getStock(String name) {
        return supply.get(name).getStock();
    }

    public final int getDeckSize() {
        return player.getDeckSize();
    }

    public int getDiscardSize() {
        return player.getDiscardSize();
    }

    public final List<String> getHandAsString() {
        List<String> cardNames = new ArrayList<>();

        for (Card card : player.getHand()) {
            cardNames.add(card.getName());
        }

        return cardNames;
    }

    public UUID getPlayerId() {
        return player.getPlayerId();
    }

    public String getNameByPlayerId(UUID id) {
        return playerNames.get(id);
    }

    public Card createCard(String name) {
        return cardFactory.createCard(name);
    }

    public boolean isGameOver() {
        int emptySupplyPiles = 0;

        for (SupplyPile pile : supply.values()) {
            if (pile.getStock() == 0)
                emptySupplyPiles++;
        }

        if (supply.get("Province").getStock() == 0 || emptySupplyPiles == 3) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * DISCARD
     */
    public String peekDiscard() {
        return player.peekDiscard();
    }


    /*
     * TURN
     */
    public static void switchToInactive() { turnPhase = TurnPhase.INACTIVE; }
    public static void switchToWaitingPhase() { turnPhase = TurnPhase.WAITING; }
    public static void switchToActionPhase() { turnPhase = TurnPhase.ACTION; }
    public static void switchToBuyPhase() { turnPhase = TurnPhase.BUY; }
    public static void switchToGameOver() { turnPhase = TurnPhase.GAME_OVER; }
    public static TurnPhase getTurnPhase() { return turnPhase; }

    public void endTurn() {
        if (getTurnPhase() == TurnPhase.BUY) {
            switchToWaitingPhase();

            player.endTurn();
        }
    }

    /*
     * BUY
     */
    public int getPlayerActions() { return player.getActions(); }
    public int getPlayerBuys() { return player.getBuys(); }
    public int getPlayerCoins() { return player.getCoins(); }

    public boolean purchaseCard(String name) {
        return false;
    }

    public Card takeCard(String name) {
        return supply.get(name).take();
    }

    public List<String> getKingdomCards() { return kingdomCards; }
}
