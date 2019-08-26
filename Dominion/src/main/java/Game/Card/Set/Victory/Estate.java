package Game.Card.Set.Victory;

import Constant.CardType;
import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Estate extends Card {

    public Estate() {
        super("Estate", CARD_COSTS.get("Estate"));
        addType(CardType.VICTORY);
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return new Estate();
    }
}