package Constants;

public enum AbilityType {
    ACTION("Action"),
    REACTION("Reaction"),
    TREASURE("Treasure");

    private final String text;

    AbilityType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
