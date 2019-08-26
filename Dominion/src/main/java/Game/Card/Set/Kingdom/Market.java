package Game.Card.Set.Kingdom;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.Instant.DrawCardEffect;
import Game.Card.Effect.Type.Instant.GainActionEffect;
import Game.Card.Effect.Type.Instant.GainBuyEffect;
import Game.Card.Effect.Type.Instant.GainCoinEffect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Market extends Card {

    public Market() {
        super("Market", CARD_COSTS.get("Market"));
        addType(CardType.ACTION);

        addEffect(new DrawCardEffect(1));
        addEffect(new GainActionEffect(1));
        addEffect(new GainBuyEffect(1));
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
        return new Market();
    }
}
