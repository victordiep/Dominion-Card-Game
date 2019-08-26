package Game.Card.Set.Kingdom;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Militia extends Card {

    public Militia() {
        super("Militia", CARD_COSTS.get("Militia"));
        addType(CardType.ACTION);
        addType(CardType.ATTACK);
    }

    @Override
    public void activate() {
        for (Effect effect : effects) {
            effect.apply();
        }
    }

    @Override
    public Card makeCopy() {
        return new Militia();
    }
}
