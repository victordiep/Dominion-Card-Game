package Game.Card.Set.Kingdom;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.Instant.TrashCardToGainCardEffect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Mine extends Card {

    public Mine() {
        super("Mine", CARD_COSTS.get("Mine"));
        addType(CardType.ACTION);

        addEffect(new TrashCardToGainCardEffect(3, CardType.TREASURE));
    }

    @Override
    public void activate() {
        for (Effect effect : effects) {
            effect.apply();
        }
    }

    @Override
    public Card makeCopy() {
        return new Mine();
    }
}