package Game.Card.Set.Kingdom;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Smithy extends Card {

    public Smithy() {
        super("Smithy", CARD_COSTS.get("Smithy"));
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
        return new Smithy();
    }
}
