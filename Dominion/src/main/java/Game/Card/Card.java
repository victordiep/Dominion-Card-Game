package Game.Card;

import Constant.CardType;

import java.util.HashSet;
import java.util.Set;

/*
 * A card utilizes
 */

public abstract class Card {

    protected String name;
    protected int cost;
    protected Set<CardType> types;

    public Card(String name, int cost) {
        this.name = name;
        this.cost = cost;

        types = new HashSet<>();
    }

    public Card(final Card card) {
        this.name = card.name;
        this.cost = card.cost;

        this.types = new HashSet<>(card.types);
    }

    public String getName() { return name; }
    public int getCoste() { return cost; }
    public Set<CardType> getType() { return types; }

    public void addType(CardType type) {
        types.add(type);
    }

    public boolean hasType(CardType type) {
        if (type == null) {
            return false;
        }

        if (types.contains(type)) {
            return true;
        } else {
            return false;
        }
    }

    // Activate the card
    public abstract void activate();

    public abstract Card makeCopy();

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() { return name.hashCode(); }

    @Override
    public boolean equals(Object o) { return name.equals(o); }
}
