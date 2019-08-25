package Game.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;

/*
 * Used to represent a stack of cards, E.g. deck, discard pile
 */

public class CardPile {

    private ArrayList<Card> cards;

    public CardPile() {
        cards = new ArrayList<>();
    }

    private List<Card> getCards() {
        return cards;
    }

    public void push(Card card) {
        cards.add(card);
    }

    public void combine(List<Card> cards) {
        this.cards.addAll(cards);
        cards.clear();
    }

    public void combine(CardPile cards) {
        this.cards.addAll(cards.getCards());
        cards.empty();
    }

    public Card pop() {
        if (!isEmpty())
            return cards.remove(size()-1);
        else
            throw new EmptyStackException();
    }

    public Card peek() {
        if (!isEmpty())
            return cards.get(size() - 1);
        else
            throw new EmptyStackException();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void empty() {
        cards.clear();
    }

    public boolean isEmpty() {
        return (cards.size() == 0);
    }

    public int size() {
        return cards.size();
    }
}
