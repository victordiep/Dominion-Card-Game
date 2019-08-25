package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.KINGDOM_CARD_COSTS;

public class Moneylender extends Card {

    public Moneylender() {
        super("Moneylender", KINGDOM_CARD_COSTS.get("Moneylender"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
