package Game.Card.Effect;

import Constant.EffectType;

/*
 * An effect represents the ability text of a card or part of the ability text of a card
 */

public abstract class Effect {

    protected String text;
    protected EffectType effectType;

    public Effect(String text, EffectType effectType) {
        this.text = text;
        this.effectType = effectType;
    }

    public Effect(final Effect effect) {
        this.text = effect.text;
        this.effectType = effect.effectType;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public EffectType getType() { return effectType; }

    public abstract void apply();
}
