package Card.Interfaces;

import java.util.UUID;

/*
 *
 */

public interface IControllable {
    UUID getControllerId();
    UUID getId();

    default boolean isControlledBy(UUID controllerID){
        if(getControllerId() == null){
            return false;
        }
        return getControllerId().equals(controllerID);
    }
}
