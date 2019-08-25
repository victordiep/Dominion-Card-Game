package Game.Card.Set.Treasure;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.TREASURE_CARD_COSTS;

public class Gold extends Card {

    public Gold() {
        super("Gold", TREASURE_CARD_COSTS.get("Gold"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}