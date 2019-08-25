package Game.Card.Effect.Type.Reaction;

import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.ReactionEffect;
import Game.Game;

public class NullifyAttackEffect extends ReactionEffect {

    public NullifyAttackEffect(Effect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game) {
        return false;
    }

}
