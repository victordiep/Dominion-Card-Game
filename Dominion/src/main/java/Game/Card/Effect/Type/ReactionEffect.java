package Game.Card.Effect.Type;

import Game.Card.Effect.Effect;

/*
 * A reaction effect is an effect that is applied in reaction to an attack
 */

public abstract class ReactionEffect extends Effect {

    public ReactionEffect(Effect effect) {
        super(effect);
    }

}
