package Constants;

public enum CardType {
    ACTION("Action"),
    CURSE("Curse"),
    REACTION("Reaction"),
    TREASURE("Treasure"),
    VICTORY("Victory");

    private final String text;

    CardType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static CardType fromString(String value) {
        for (CardType ct : CardType.values()) {
            if (ct.toString().equals(value)) {
                return ct;
            }
        }

        throw new IllegalArgumentException("Can't find card type enum value: " + value);
    }
}
