package Game.Card.Set.Kingdom;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.Instant.DrawCardEffect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Moat extends Card {

    public Moat() {
        super("Moat", CARD_COSTS.get("Moat"));
        addType(CardType.ACTION);
        addType(CardType.REACTION);

        addEffect(new DrawCardEffect(2));
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