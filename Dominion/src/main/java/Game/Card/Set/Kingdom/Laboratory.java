package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.KINGDOM_CARD_COSTS;

public class Laboratory extends Card {

    public Laboratory() {
        super("Laboratory", KINGDOM_CARD_COSTS.get("Laboratory"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
