package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Village extends Card {

    public Village() {
        super("Village", CARD_COSTS.get("Village"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return new Village();
    }
}
