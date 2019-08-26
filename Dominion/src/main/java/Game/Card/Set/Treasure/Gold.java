package Game.Card.Set.Treasure;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.Instant.GainCoinEffect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Gold extends Card {

    public Gold() {
        super("Gold", CARD_COSTS.get("Gold"));
        addType(CardType.TREASURE);
        addEffect(new GainCoinEffect(3));
    }

    @Override
    public void activate() {
        for (Effect effect : effects) {
            effect.apply();
        }
    }

    @Override
    public Card makeCopy() {
        return new Gold();
    }
}