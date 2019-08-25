package Game.Card.Effect;

import Constant.EffectType;
import Game.Game;

/*
 * An effect represents the ability text of a card or part of the ability text of a card
 */

public abstract class Effect {

    protected String text;
    protected EffectType effectType;

    public Effect(final Effect effect) {
        this.text = effect.text;
        this.effectType = effect.effectType;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public EffectType getType() { return effectType; }

    public abstract boolean apply(Game game);
}
