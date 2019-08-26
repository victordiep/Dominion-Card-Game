package Game.Card.Set.Curse;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Curse extends Card {

    public Curse() {
        super("Curse", CARD_COSTS.get("Curse"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
