package Game.Card.Effect.Type;

import Constant.EffectType;
import Game.Card.Effect.Effect;

/*
 * An instant effect is an effect that applies immediately
 */

public abstract class InstantEffect extends Effect {

    public InstantEffect(String text) {
        super(text, EffectType.INSTANT);
    }

}
