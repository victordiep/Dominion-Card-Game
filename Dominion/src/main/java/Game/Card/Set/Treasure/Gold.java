package Game.Card.Set.Treasure;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Gold extends Card {

    public Gold() {
        super("Gold", CARD_COSTS.get("Gold"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return new Gold();
    }
}