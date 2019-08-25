package Constant;

public enum EffectType {
    INSTANT("Instant Effect"),
    REACTION("Reaction Effect");

    private final String text;

    EffectType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
