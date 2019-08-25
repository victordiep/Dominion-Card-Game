package Game.Card.Set.Victory;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.VICTORY_CARD_COSTS;

public class Duchy extends Card {

    public Duchy() {
        super("Duchy", VICTORY_CARD_COSTS.get("Duchy"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
