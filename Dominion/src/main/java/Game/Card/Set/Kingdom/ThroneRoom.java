package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class ThroneRoom extends Card {

    public ThroneRoom() {
        super("ThroneRoom", CARD_COSTS.get("ThroneRoom"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}