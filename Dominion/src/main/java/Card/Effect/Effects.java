package Card.Effect;

import Card.Effect.Interfaces.IEffect;

import java.util.ArrayList;

public class Effects extends ArrayList<IEffect> {

    public Effects() {
    }

    public Effects(Effect effect) {
        this.add(effect);
    }

    public Effects(final Effects effects) {
        for (IEffect effect : effects) {
            this.add(effect.copy());
        }
    }
}
