package Game.Card.Set.Victory;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.VICTORY_CARD_COSTS;

public class Province extends Card {

    public Province() {
        super("Province", VICTORY_CARD_COSTS.get("Province"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}