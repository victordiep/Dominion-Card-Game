package Game.Event;

import java.io.Serializable;

@FunctionalInterface
public interface Listener<E extends ExternalEvent> extends Serializable {
    void event(E event);
}
