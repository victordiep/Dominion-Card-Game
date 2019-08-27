package Game.Card.Set.Kingdom;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.Instant.TrashCardToGainCardEffect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Remodel extends Card {

    public Remodel() {
        super("Remodel", CARD_COSTS.get("Remodel"));
        addType(CardType.ACTION);

        addEffect(new TrashCardToGainCardEffect(2));
    }

    @Override
    public void activate() {
        for (Effect effect : effects) {
            effect.apply();
        }
    }

    @Override
    public Card makeCopy() {
        return new Remodel();
    }
}