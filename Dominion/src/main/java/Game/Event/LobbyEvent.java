package Game.Event;

import Game.Game;
import Game.Match.MatchOptions;

import java.io.Serializable;
import java.util.EventObject;
import java.util.UUID;

public class LobbyEvent extends EventObject implements ExternalEvent, Serializable {

    public enum EventType {
        UPDATE, INFO, STATUS,
        START_CARD_SELECT, SUBMIT_CARDS, START_MATCH,
        END, END_GAME_INFO, ERROR, CHECK_STATE_PLAYERS,
        START_MULTIPLAYER_MATCH
    }

    private Game game;
    private EventType eventType;
    private String message;
    private Exception ex;
    //private Cards cards;
    private UUID playerId;
    //private Deck deck;
    private MatchOptions options;
    private int timeout;
    private boolean withTime;

    public LobbyEvent(EventType eventType) {
        super(eventType);
        this.eventType = eventType;
    }
}
