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
    private final List<UUID> playerIds;
    private final List<String> playerNames;
    private final int numOfPlayers;

    private final List<String> kingdomCards;

    // Card piles available to all players
    private Map<String, SupplyPile> supply;
    private List<Card> trash;

    // Used to create cards
    private CardFactory cardFactory;

    private static TurnPhase turnPhase = TurnPhase.INACTIVE;

    public Game(List<String> kingdomCards, UUID playerId, String name, List<UUID> playerIds, List<String> playerNames) {
        this.player = new Player(playerId, name);
        this.playerNames = new ArrayList<>(playerNames);
        this.playerIds = new ArrayList<>(playerIds);
        this.numOfPlayers = this.playerNames.size();

        this.kingdomCards = new ArrayList<>(kingdomCards);
        cardFactory = new CardFactory(this);

        supply = new HashMap<>();
        populateSupply(supply);
        trash = new ArrayList<>();

        turnPhase = TurnPhase.WAITING;
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
        supply.put("Copper", new SupplyPile(createCard("Copper"), NUMBER_OF_COPPER - (numOfPlayers * 7))); // Every players starts with 7 Copper
        supply.put("Silver", new SupplyPile(createCard("Silver"), NUMBER_OF_SILVER));
        supply.put("Gold", new SupplyPile(createCard("Gold"), NUMBER_OF_GOLD));

        // Victory CardPile
        supply.put("Estate", new SupplyPile(createCard("Estate"), NUMBER_OF_ESTATE - (numOfPlayers * 3))); // Every players starts with 3 Estate
        supply.put("Duchy", new SupplyPile(createCard("Duchy"), NUMBER_OF_DUCHY));
        supply.put("Province", new SupplyPile(createCard("Province"), NUMBER_OF_PROVINCE));

        // Curse CardPile
        supply.put("Curse", new SupplyPile(createCard("Curse"), NUMBER_OF_CURSE));
    }

    public int getStock(String name) {
        return supply.get(name).getStock();
    }

    public int getDeckSize() {
        return player.getDeckSize();
    }

    public int getDiscardSize() {
        return player.getDiscardSize();
    }

    public Card createCard(String name) {
        return cardFactory.createCard(name);
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

    /*
     * BUY
     */
    public Card takeCard(String name) {
        return supply.get(name).take();
    }

    public List<String> getKingdomCards() { return kingdomCards; }
}
