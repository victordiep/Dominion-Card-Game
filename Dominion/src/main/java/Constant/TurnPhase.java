package Constant;

public enum TurnPhase {
    INACTIVE("Game not active"),
    WAITING("Waiting"),
    ACTION("Action Phase"),
    ACTION_IN_PROGRESS("Action In Progress"),
    BUY("Buy Phase"),
    GAME_OVER("Game Over");

    private final String phase;

    TurnPhase(String phase) {
        this.phase = phase;
    }

    @Override
    public String toString() {
        return phase;
    }
}
