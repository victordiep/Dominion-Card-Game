package Game.Card.Set.Kingdom;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.Instant.GainCoinEffect;
import Game.Card.Effect.Type.Instant.OtherPlayersDiscardToXEffect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Militia extends Card {

    public Militia() {
        super("Militia", CARD_COSTS.get("Militia"));
        addType(CardType.ACTION);
        addType(CardType.ATTACK);

        addEffect(new GainCoinEffect(1));
        addEffect(new OtherPlayersDiscardToXEffect(3));
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
