package Card.Effect;

import Card.Interfaces.IEffect;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Effect implements IEffect {

    protected UUID id;
    //protected EffectType effectType;
    //protected TargetPointer targetPointer = FirstTargetPointer.getInstance();
    protected String staticText = "";
    protected Map<String, Object> values;
    protected String concatPrefix = ""; // combines multiple effects in text rule

    public Effect(final Effect effect) {
        this.id = effect.id;
        this.staticText = effect.staticText;
        //this.effectType = effect.effectType;
        //this.targetPointer = effect.targetPointer.copy();
        this.concatPrefix = effect.concatPrefix;
        if (effect.values != null) {
            values = new HashMap<>();
            Map<String, Object> map = effect.values;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                values.put(entry.getKey(), entry.getValue());
            }
        }
    }

}
