package Game.Card.Set.Kingdom;

import Constant.CardType;
import Game.Card.Card;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.Instant.GainCardFromSupplyEffect;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Workshop extends Card {

    public Workshop() {
        super("Workshop", CARD_COSTS.get("Workshop"));
        addType(CardType.ACTION);

        addEffect(new GainCardFromSupplyEffect(4));
    }

    @Override
    public void activate() {
        for (Effect effect : effects) {
            effect.apply();
        }
    }

    @Override
    public Card makeCopy() {
        return new Workshop();
    }
}