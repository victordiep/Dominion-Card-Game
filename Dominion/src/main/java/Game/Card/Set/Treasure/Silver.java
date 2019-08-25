package Game.Card.Set.Treasure;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.TREASURE_CARD_COSTS;

public class Silver extends Card {

    public Silver() {
        super("Silver", TREASURE_CARD_COSTS.get("Silver"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
