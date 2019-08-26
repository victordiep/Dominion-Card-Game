package Game.Card.Set.Kingdom;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.Instant.DrawCardEffect;
import Game.Card.Effect.Type.Instant.GainActionEffect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Village extends Card {

    public Village() {
        super("Village", CARD_COSTS.get("Village"));
        addType(CardType.ACTION);

        addEffect(new DrawCardEffect(1));
        addEffect(new GainActionEffect(2));
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
