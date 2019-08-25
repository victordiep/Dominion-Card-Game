package Game.Card.Effect.Type.Instant;

import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.InstantEffect;
import Game.Game;

public class DrawCardEffect extends InstantEffect {

    public DrawCardEffect(Effect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game) {
        return false;
    }
}
