package Game.Card.Set.Victory;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Duchy extends Card {

    public Duchy() {
        super("Duchy", CARD_COSTS.get("Duchy"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
