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

        hand = new ArrayList<>();
        deck = new CardPile();
        inPlay = new CardPile();
        discard = new CardPile();
    }

    public final UUID getPlayerId() { return playerId; }
    public final String getName() { return name; }

    public final int getActions() { return actions; }
    public final int getBuys() { return buys; }

    public final int getDeckSize() { return deck.size(); }
    public final int getDiscardSize() { return discard.size(); }

    public final List<Card> getHand() { return hand; }

    public void addActions(int num) { actions = actions + num; }
    public void addBuys(int num) { actions = buys + num; }

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
    public void addCardIntoDeck(Card card) {
        deck.push(card);
    }

    // Called at the end of the game to count Victory Points
    public void shuffleAllIntoDeck() {
        putCardsInPlayIntoDiscard();
        putHandIntoDiscard();

        shuffleDiscardIntoDeck();
    }

    public void drawCard() {
        hand.add(deck.pop());
    }

    public void drawCards(int num) {
        for (int i = 0; i < num; i++) {
            if (deck.size() == 0)
                shuffleDiscardIntoDeck();

            drawCard();
        }
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

        if (!result)
            System.out.println("Can't play card: " + name);

        return result;
    }
}
