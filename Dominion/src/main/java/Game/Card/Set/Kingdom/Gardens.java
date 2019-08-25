package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.KINGDOM_CARD_COSTS;

public class Gardens extends Card {

    public Gardens() {
        super("Gardens", KINGDOM_CARD_COSTS.get("Gardens"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
