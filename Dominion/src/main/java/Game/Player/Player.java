package Game.Player;

import static Constant.GuiSettings.GameSettings.*;

import Constant.CardType;
import Constant.TurnPhase;
import Game.Card.Card;
import Game.Card.CardPile;
import Game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * Represents the player
 */

public class Player {

    // Player identification
    private final UUID playerId;
    private String name;

    // Game turn
    private int actions;
    private int buys;
    private int coins;

    // Card collections
    private ArrayList<Card> hand; // Represents the hand
    private CardPile deck; // Represents the deck
    private CardPile inPlay; // Represents the cards that were played during the turn
    private CardPile discard; // Represents the discard pile

    public Player(UUID playerId, String name) {
        this.playerId = playerId;
        this.name = name;

        actions = NUMBER_OF_ACTIONS;
        buys = NUMBER_OF_BUYS;
        coins = NUMBER_OF_COINS;

        hand = new ArrayList<>();
        deck = new CardPile();
        inPlay = new CardPile();
        discard = new CardPile();
    }

    public final UUID getPlayerId() { return playerId; }
    public final String getName() { return name; }

    public final int getActions() { return actions; }
    public final int getBuys() { return buys; }
    public final int getCoins() { return coins; }

    public void spendCoins(int cost) { coins = coins - cost; }
    public void useAction() { actions--; }
    public void useBuy() { buys--; }

    public final int getDeckSize() { return deck.size(); }
    public final int getDiscardSize() { return discard.size(); }

    public final List<Card> getHand() { return hand; }
    public final String peekDiscard() {
        if (discard.peek() == null)
            return null;
        else
            return discard.peek().getName();
    }

    public void addActions(int num) { actions = actions + num; }
    public void addBuys(int num) { buys = buys + num; }
    public void addCoins(int num) { coins = coins + num; }

    public void shuffleDeck() {
        deck.shuffle();
    }

    // Called when player runs out of cards to draw
    public void shuffleDiscardIntoDeck() {
        deck.combine(discard);
        shuffleDeck();
    }

    // Called at the end of the player's turn
    public void putCardsInPlayIntoDiscard() {
        discard.combine(inPlay);
    }

    // Called at the end of the player's turn
    public void putHandIntoDiscard() {
        discard.combine(hand);
    }

    // Called at the end of the player's turn
    public void putCardIntoPlay(Card card) {
        inPlay.push(card);
        hand.remove(card);
    }

    // Called at the end of the player's turn
    public void putCardIntoDiscard(Card card) {
        discard.push(card);
    }

    // Called at the end of the player's turn
    public void addCardIntoDeck(Card card) {
        deck.push(card);
    }

    // Called at the end of the game to count Victory Points
    public void shuffleAllIntoDeck() {
        putCardsInPlayIntoDiscard();
        putHandIntoDiscard();

        shuffleDiscardIntoDeck();
    }

    public Card drawCard() {
        if (deck.size() == 0)
            shuffleDiscardIntoDeck();

        Card card = deck.pop();
        hand.add(card);
        return card;
    }

    public List<Card> drawCards(int num) {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            cards.add(drawCard());
        }

        return cards;
    }

    public Card findCardInHand(String name) {
        for (Card card : hand) {
            if (card.getName().equals(name)) {
                return card;
            }
        }

        return null;
    }

    public boolean playCard(String name) {
        Card card = findCardInHand(name);
        boolean result = false;

        // Invalid card
        if (card == null)
            return result;

        if (card.getType().contains(CardType.ACTION) && actions > 0 && Game.getTurnPhase() == TurnPhase.ACTION) {
            actions--;
            putCardIntoPlay(card);
            card.activate();
            result = true;
        }
        else if (card.getType().contains(CardType.TREASURE) && Game.getTurnPhase() == TurnPhase.BUY) {
            putCardIntoPlay(card);
            card.activate();
            result = true;
        }

        return result;
    }

    public void buyCard(Card card) {
        if (coins >= card.getCost()){
            spendCoins(card.getCost());
            useBuy();
            putCardIntoDiscard(card);
        }
    }

    public void endTurn() {
        putCardsInPlayIntoDiscard();
        putHandIntoDiscard();

        drawCards(HAND_SIZE);

        actions = NUMBER_OF_ACTIONS;
        buys = NUMBER_OF_BUYS;
        coins = NUMBER_OF_COINS;
    }
}
