package Constants;

public enum EffectType {
    ONETIME("One Shot Effect"),
    PREVENTION("Prevention Effect"),
    COSTMODIFICATION("Cost Modification Effect");

    private final String text;

    EffectType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
