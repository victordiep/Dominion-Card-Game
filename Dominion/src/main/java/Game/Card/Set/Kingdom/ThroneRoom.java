package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.KINGDOM_CARD_COSTS;

public class ThroneRoom extends Card {

    public ThroneRoom() {
        super("ThroneRoom", KINGDOM_CARD_COSTS.get("ThroneRoom"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}