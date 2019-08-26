package Game.Card.Set.Victory;

import Constant.CardType;
import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Province extends Card {

    public Province() {
        super("Province", CARD_COSTS.get("Province"));
        addType(CardType.VICTORY);
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return new Province();
    }
}