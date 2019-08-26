package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Smithy extends Card {

    public Smithy() {
        super("Smithy", CARD_COSTS.get("Smithy"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return new Smithy();
    }
}
