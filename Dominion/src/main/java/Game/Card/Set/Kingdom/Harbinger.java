package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.KINGDOM_CARD_COSTS;

public class Harbinger extends Card {

    public Harbinger() {
        super("Harbinger", KINGDOM_CARD_COSTS.get("Harbinger"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
