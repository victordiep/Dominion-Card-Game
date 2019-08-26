package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Militia extends Card {

    public Militia() {
        super("Militia", CARD_COSTS.get("Militia"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return new Militia();
    }
}
