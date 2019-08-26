package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Festival extends Card {

    public Festival() {
        super("Festival", CARD_COSTS.get("Festival"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
