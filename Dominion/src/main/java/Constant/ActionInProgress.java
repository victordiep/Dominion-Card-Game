package Constant;

/*
 * Used for actions that require user input
 *  DISCARD:  Discard X cards
 *  GAIN:     Gain X cards from supply, usually a condition is attached to it
 *  ACTIVATE: This currently only applies to the Moat card for reacting to Militia
 *  TRASH:    Trash X cards
 */

public enum ActionInProgress {
    NO_ACTION("No Action"),
    DISCARD("Discard Card"),
    GAIN("Gain Card"),
    ACTIVATE("Activate Card"),
    TRASH("Trash Card");

    private final String type;

    ActionInProgress(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
