package Card.Ability;

import Card.Effect.Effect;
import Card.Effect.Effects;
import Card.Ability.Interfaces.IAbility;
import Constants.AbilityType;
import Game.Game;
import Game.Target.Target;
import Game.Target.Targets;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Ability implements IAbility {

    private static final Logger logger = LogManager.getLogger(Ability.class);

    protected String name;

    protected UUID id;
    protected UUID originalId;
    protected UUID controllerId;
    protected UUID sourceId;

    protected AbilityType abilityType;

    protected boolean activated = false;

    public Ability(AbilityType abilityType) {
        this.id = UUID.randomUUID();
        this.originalId = id;
        this.abilityType = abilityType;
    }

    public Ability(final Ability ability) {
        this.id = ability.id;
        this.originalId = ability.originalId;
        this.abilityType = ability.abilityType;
        this.controllerId = ability.controllerId;
        this.sourceId = ability.sourceId;
        this.name = ability.name;
    }

    @Override
    public UUID getId() { return id; }
    @Override
    public UUID getOriginalId() { return originalId; }

    @Override
    public void newId() {
        this.id = UUID.randomUUID();
        getEffects().newId();
    }

    @Override
    public void newOriginalId() {
        this.id = UUID.randomUUID();
        this.originalId = id;
        getEffects().newId();
    }

    @Override
    public UUID getControllerId() { return controllerId; }
    @Override
    public void setControllerId(UUID controllerId) { this.controllerId = controllerId; }

    @Override
    public UUID getSourceId() { return sourceId; }
    @Override
    public void setSourceId(UUID sourceId) { this.sourceId = sourceId; }

    @Override
    public AbilityType getAbilityType() { return abilityType; }

    @Override
    public Effects getEffects() {
        return null;
    }

    @Override
    public Targets getTargets() {
        return null;
    }

    @Override
    public UUID getFirstTarget() {
        return null;
    }

    @Override
    public void addTarget(Target target) {

    }

    @Override
    public String getRule() {
        return null;
    }

    @Override
    public boolean activate(Game game) {
        return false;
    }

    @Override
    public boolean isActivated() {
        return false;
    }

    @Override
    public IAbility copy() {
        return null;
    }

    @Override
    public String getGameLogMessage(Game game) {
        return null;
    }

    @Override
    public void reset(Game game) {

    }

    @Override
    public boolean resolve(Game game) {
        boolean result = true;

        for (Effect effect : getEffects()) {

        }

        return result;
    }

}
