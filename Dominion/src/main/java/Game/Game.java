package Game;

import static Constant.CardSettings.DominionCards.CARD_COSTS;
import static Constant.GuiSettings.GameSettings.*;

import Client.DominionManager;
import Constant.ActionInProgress;
import Constant.CardType;
import Constant.TurnPhase;
import Game.Card.Card;
import Game.Card.CardFactory;
import Game.Card.SupplyPile;
import Game.Player.Player;
import protobuf.PacketProtos;

import java.io.IOException;
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
    private static ActionInProgress actionType = ActionInProgress.NO_ACTION;

    private int costCap = 99;
    private CardType gainType = null;

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

    public int getCostCap() { return costCap; }
    public void setCostCap(int costCap) { this.costCap = costCap; }

    public CardType getGainType() { return gainType; }
    public void setGainType(CardType gainType) { this.gainType = gainType; }

    private void setupGame() {
        populateSupply(supply);
        distributeStartingCards();
    }

    /*
     * SUPPLY
     */
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
    public Card createCard(String name) {
        return cardFactory.createCard(name);
    }
    public Card getCard(String name) {
        return supply.get(name).getCard();
    }
    public Card takeCard(String name) {
        return supply.get(name).take();
    }
    public List<String> getKingdomCards() { return kingdomCards; }

    /*
     * TRASH
     */

    public final List<Card> getTrash() { return trash; }
    public void trashCardFromHand(String card) throws IOException {
        trash.add(player.takeCardFromHand(card));

        PacketProtos.Packet.Builder trashBuilder = PacketProtos.Packet.newBuilder()
                                                        .setUUID(DominionManager.getInstance().getGame().getPlayerId().toString())
                                                        .setType(PacketProtos.Packet.Type.TRASH);
        for (Card trashedCard : trash) {
            trashBuilder.addMessage(trashedCard.getName());
        }

        DominionManager.getInstance().sendEvent(trashBuilder.build());
    }

    public void setTrash(ArrayList<String> trashList) {
        trash.clear();

        for (String trashedCard : trashList) {
            trash.add(createCard(trashedCard));
        }
    }

    public Card removeTrash(String name) {
        for (int i = 0; i < trash.size(); i++) {
            if (trash.get(i).getName().equals(name))
                return trash.remove(i);
        }

        return null;
    }

    /*
     * DECK
     */
    public final int getDeckSize() {
        return player.getDeckSize();
    }

    /*
     * DISCARD
     */
    public String peekDiscard() {
        return player.peekDiscard();
    }
    public int getDiscardSize() {
        return player.getDiscardSize();
    }
    public boolean discard(String name) { return player.discard(name); }

    public boolean putCardInDiscard(String name) throws IOException {
        if (getActionInProgress() == ActionInProgress.GAIN) {
            if (supply.get(name).getStock() > 0) {
                if (supply.get(name).getCard().getCost() <= costCap) {
                    Card card = supply.get(name).getCard();

                    if (gainType == null || card.getType().contains(gainType)) {
                        player.putCardIntoDiscard(supply.get(name).take());

                        DominionManager.getInstance().sendEvent(PacketProtos.Packet.newBuilder()
                                .setUUID(DominionManager.getInstance().getGame().getPlayerId().toString())
                                .setType(PacketProtos.Packet.Type.BUY_CARD)
                                .addMessage(name)
                                .build());

                        return true;
                    }
                }
            }
        }

        return false;
    }

    /*
     * HAND
     */
    public List<Card> drawCard(int num) { return player.drawCards(num); }
    public boolean playCard(String name) { return player.playCard(name); }

    public final List<String> getHandAsString() {
        List<String> cardNames = new ArrayList<>();

        for (Card card : player.getHand()) {
            cardNames.add(card.getName());
        }

        return cardNames;
    }

    /*
     * PLAYER
     */
    public UUID getPlayerId() {
        return player.getPlayerId();
    }
    public String getNameByPlayerId(UUID id) {
        return playerNames.get(id);
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

    public int calculateVictoryPoints() {
        return player.calculateVictoryPoints();
    }

    /*
     * ACTION
     */
    public int getPlayerActions() { return player.getActions(); }
    public void addActions(int num) { player.addActions(num); }

    /*
     * ACTION IN PROGRESS
     */
    public static ActionInProgress getActionInProgress() { return actionType; }
    public static void setActionInProgress(ActionInProgress type) { actionType = type; }

    /*
     * BUY
     */
    public int getPlayerBuys() { return player.getBuys(); }
    public void addBuys(int num) { player.addBuys(num); }
    public int getPlayerCoins() { return player.getCoins(); }
    public void addCoins(int num) { player.addCoins(num); }

    public boolean purchaseCard(String name) throws IOException {
        if (getTurnPhase() == TurnPhase.BUY) {
            if (supply.get(name).getStock() > 0 && getPlayerCoins() >= CARD_COSTS.get(name) && getPlayerBuys() > 0) {
                player.buyCard(supply.get(name).take());

                DominionManager.getInstance().sendEvent(PacketProtos.Packet.newBuilder()
                                                            .setUUID(DominionManager.getInstance().getGame().getPlayerId().toString())
                                                            .setType(PacketProtos.Packet.Type.BUY_CARD)
                                                            .addMessage(name)
                                                            .build());
                costCap = 99;
                return true;
            }
        }

        return false;
    }
}
