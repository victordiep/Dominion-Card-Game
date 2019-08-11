package Card.Effect.Interfaces;

import Card.Ability.Ability;
import Constants.EffectType;
import Game.Game;
import Game.Target.Interfaces.ITargetPointer;

import java.io.Serializable;
import java.util.UUID;

public interface IEffect extends Serializable {

    UUID getId();

    void newId();

    boolean apply(Game game, Ability source);

    EffectType getEffectType();

    IEffect setTargetPointer(ITargetPointer targetPointer);

    ITargetPointer getTargetPointer();

    IEffect copy();
}
