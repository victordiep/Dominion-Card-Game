package Game.Card.Set.Kingdom;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.Instant.DrawCardEffect;
import Game.Card.Effect.Type.Instant.GainActionEffect;
import Game.Card.Effect.Type.Instant.GainCoinEffect;
import Game.Card.Effect.Type.Instant.PlayCardActivateEffectEffect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Merchant extends Card {

    public Merchant() {
        super("Merchant", CARD_COSTS.get("Merchant"));
        addType(CardType.ACTION);

        addEffect(new DrawCardEffect(1));
        addEffect(new GainActionEffect(1));
        addEffect(new PlayCardActivateEffectEffect("Silver", new GainCoinEffect(1)));
    }

    @Override
    public void activate() {
        for (Effect effect : effects) {
            effect.apply();
        }
    }

    @Override
    public Card makeCopy() {
        return new Merchant();
    }
}