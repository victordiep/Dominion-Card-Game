package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Moneylender extends Card {

    public Moneylender() {
        super("Moneylender", CARD_COSTS.get("Moneylender"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
