package Game.Card.Effect.Type.Instant;

import Client.DominionManager;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.InstantEffect;

public class GainActionEffect extends InstantEffect {
    private int actions;

    public GainActionEffect(int actions) {
        super("Gain " + actions + " action(s)");
        this.actions = actions;
    }

    @Override
    public void apply() {
        DominionManager.getInstance().getGame().addActions(actions);
    }
}
