package Game.Card;

import java.util.EmptyStackException;

/*
 * Used to represent a stack of the same card in the supply pile
 */

public class SupplyPile {

    private final String name;
    private final Card card;
    private int stock;

    public SupplyPile(Card card, int stock) {
        this.name = card.name;
        this.card = card.makeCopy();
        this.stock = stock;
    }

    public void add() {
        stock++;
    }

    public void add(int num) {
        stock = stock + num;
    }

    public Card getCard() {
        return card.makeCopy();
    }

    public Card take() {
        if (!isEmpty()) {
            stock--;
            return card.makeCopy();
        }
        else
            throw new EmptyStackException();
    }

    public void reduceStock(int num) {
        if (num <= stock)
            stock = stock - num;
        else
            stock = 0;
    }

    public boolean isEmpty() {
        return stock == 0;
    }

    public final String getName() { return name; }
    public final int getStock() { return stock; }
}
