package Game.Card.Set.Kingdom;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Moat extends Card {

    public Moat() {
        super("Moat", CARD_COSTS.get("Moat"));
        addType(CardType.ACTION);
        addType(CardType.REACTION);
    }

    @Override
    public void activate() {
        for (Effect effect : effects) {
            effect.apply();
        }
    }

    @Override
    public Card makeCopy() {
        return new Moat();
    }
}