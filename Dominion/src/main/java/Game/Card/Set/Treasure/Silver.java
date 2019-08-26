package Game.Card.Set.Treasure;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Silver extends Card {

    public Silver() {
        super("Silver", CARD_COSTS.get("Silver"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
