package Game.Card.Set.Treasure;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Copper extends Card {

    public Copper() {
        super("Copper", CARD_COSTS.get("Copper"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}