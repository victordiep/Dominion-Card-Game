package Card.Interfaces;

import java.util.UUID;

/*
 * Used for classes the objects created from said classes are under control by a given player
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
