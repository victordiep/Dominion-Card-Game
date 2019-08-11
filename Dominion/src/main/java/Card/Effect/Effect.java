package Card.Effect;

import Card.Ability.Ability;
import Card.Effect.Interfaces.IEffect;
import Constants.EffectType;
import Game.Game;
import Game.Target.Interfaces.ITargetPointer;

import java.util.Map;
import java.util.UUID;

public abstract class Effect implements IEffect {

    protected UUID id;
    protected EffectType effectType;
    //protected TargetPointer targetPointer = FirstTargetPointer.getInstance();
    protected String staticText = "";
    protected Map<String, Object> values;
    protected String concatPrefix = ""; // combines multiple effects in text rule

    public Effect(final Effect effect) {

    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public void newId() {

    }

    @Override
    public boolean apply(Game game, Ability source) {
        return false;
    }

    @Override
    public EffectType getEffectType() {
        return null;
    }

    @Override
    public IEffect setTargetPointer(ITargetPointer targetPointer) {
        return null;
    }

    @Override
    public ITargetPointer getTargetPointer() {
        return null;
    }
}
