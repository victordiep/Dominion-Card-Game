package Card.Ability.Interfaces;

import Card.Effect.Effects;
import Card.Interfaces.IControllable;
import Constants.AbilityType;
import Game.Game;
import Game.Target.Target;
import Game.Target.Targets;

import java.util.UUID;

/*
 * Nearly everything in the game runs off of an ability.
 * This interface describes what an ability in Dominion is composed of at the highest level
 */

public interface IAbility extends IControllable {

    // Gets a universally unique ID of an ability contained within the game
    @Override
    UUID getId();
    UUID getOriginalId();

    // Generates a new universally unique ID of an ability contained within the game
    void newId();
    void newOriginalId();

    // Get and set the id of the controller of this ability
    @Override
    UUID getControllerId();
    void setControllerId(UUID controllerId);

    // Get and set the id of the object that put this ability in motion
    UUID getSourceId();
    void setSourceId(UUID sourceID);

    AbilityType getAbilityType();

    Effects getEffects();

    // Retrieves a list of targets that must be satisfied before this ability is fulfilled
    Targets getTargets();

    // Gets the id of the 0th index in Targets
    UUID getFirstTarget();

    // Adds a target to this ability that must be satisfied before this ability is fulfilled
    void addTarget(Target target);

    // Retrieves a human-readable string that represents what the ability states it accomplishes
    String getRule();

    // Activates the ability and checks if the ability was activated
    boolean activate(Game game);
    boolean isActivated();

    // Resolves the ability and puts any effect it produces into play. Should only be called in the
    // activate(Game game) method returned true
    boolean resolve(Game game);

    // Resets the state of this ability
    void reset(Game game);

    // Creates a copy of this ability
    IAbility copy();

    // Creates a message about the ability activating to post in the game log
    String getGameLogMessage(Game game);
}
