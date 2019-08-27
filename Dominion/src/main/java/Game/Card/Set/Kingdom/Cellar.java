package Game.Card.Set.Kingdom;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.Instant.DiscardXToDrawXEffect;
import Game.Card.Effect.Type.Instant.GainActionEffect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Cellar extends Card {
    public Cellar() {
        super("Cellar", CARD_COSTS.get("Cellar"));
        addType(CardType.ACTION);

        addEffect(new GainActionEffect(1));
        addEffect(new DiscardXToDrawXEffect());
    }

    @Override
    public void activate() {
        for (Effect effect : effects) {
            effect.apply();
        }
    }

    @Override
    public Card makeCopy() {
        return new Cellar();
    }
}
