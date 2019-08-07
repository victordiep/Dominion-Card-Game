package Game.Event;

import java.util.EventObject;

public class PlayerQueryEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public PlayerQueryEvent(Object source) {
        super(source);
    }
}
