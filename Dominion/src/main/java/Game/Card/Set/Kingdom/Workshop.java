package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Workshop extends Card {

    public Workshop() {
        super("Workshop", CARD_COSTS.get("Workshop"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return new Workshop();
    }
}