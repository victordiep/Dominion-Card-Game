package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.KINGDOM_CARD_COSTS;

public class Militia extends Card {

    public Militia() {
        super("Militia", KINGDOM_CARD_COSTS.get("Militia"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
