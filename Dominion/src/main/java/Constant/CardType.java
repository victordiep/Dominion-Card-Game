package Constant;

public enum CardType {
    ACTION("Action"),
    ATTACK("Attack"),
    CURSE("Curse"),
    REACTION("Reaction"),
    TREASURE("Treasure"),
    VICTORY("Victory");

    private final String description;

    CardType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

    public static CardType byDescription(String desc) {
        for (CardType type : values()) {
            if (type.getDescription().equals(desc)) {
                return type;
            }
        }

        throw new IllegalArgumentException("Can't find CardType enum value: " + desc);
    }
}
