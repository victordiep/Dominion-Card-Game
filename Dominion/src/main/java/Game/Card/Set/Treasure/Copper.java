package Game.Card.Set.Treasure;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.Instant.GainCoinEffect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Copper extends Card {

    public Copper() {
        super("Copper", CARD_COSTS.get("Copper"));
        addType(CardType.TREASURE);
        addEffect(new GainCoinEffect(1));
    }

    @Override
    public void activate() {
        for (Effect effect : effects) {
            effect.apply();
        }
    }

    @Override
    public Card makeCopy() {
        return new Copper();
    }
}