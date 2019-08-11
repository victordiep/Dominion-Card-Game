package Constants;

public enum Zone {
    HAND,
    DISCARD,
    SUPPLY,
    TRASH;

    public boolean match(Zone zone) {
        return this == zone;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
