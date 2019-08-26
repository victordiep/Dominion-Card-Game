package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Library extends Card {

    public Library() {
        super("Library", CARD_COSTS.get("Library"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
