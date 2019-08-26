package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Cellar extends Card {
    public Cellar() {
        super("Cellar", CARD_COSTS.get("Cellar"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return new Cellar();
    }
}
