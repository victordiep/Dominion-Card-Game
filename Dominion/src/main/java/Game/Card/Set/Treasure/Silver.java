package Game.Card.Set.Treasure;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.Instant.GainCoinEffect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Silver extends Card {

    public Silver() {
        super("Silver", CARD_COSTS.get("Silver"));
        addType(CardType.TREASURE);
        addEffect(new GainCoinEffect(2));
    }

    @Override
    public void activate() {
        for (Effect effect : effects) {
            effect.apply();
        }
    }

    @Override
    public Card makeCopy() {
        return new Silver();
    }
}
