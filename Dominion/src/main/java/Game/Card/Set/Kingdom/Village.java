package Game.Card.Set.Kingdom;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Village extends Card {

    public Village() {
        super("Village", CARD_COSTS.get("Village"));
        addType(CardType.ACTION);
    }

    @Override
    public void activate() {
        for (Effect effect : effects) {
            effect.apply();
        }
    }

    @Override
    public Card makeCopy() {
        return new Village();
    }
}
