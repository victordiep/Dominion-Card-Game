package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.KINGDOM_CARD_COSTS;

public class Festival extends Card {

    public Festival() {
        super("Festival", KINGDOM_CARD_COSTS.get("Festival"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
