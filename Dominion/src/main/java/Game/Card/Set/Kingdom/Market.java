package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.KINGDOM_CARD_COSTS;

public class Market extends Card {

    public Market() {
        super("Market", KINGDOM_CARD_COSTS.get("Market"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
