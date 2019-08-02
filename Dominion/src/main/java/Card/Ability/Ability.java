package Card.Ability;

import Card.Interfaces.IAbility;

import java.util.UUID;

public class Ability implements IAbility {
    protected UUID controllerId;
    protected UUID id;

    @Override
    public UUID getControllerId() {
        return controllerId;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
