package Game.Card.Set.Curse;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CURSE_CARD_COSTS;

public class Curse extends Card {

    public Curse() {
        super("Curse", CURSE_CARD_COSTS.get("Curse"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
