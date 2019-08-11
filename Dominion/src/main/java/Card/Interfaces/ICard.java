package Card.Interfaces;

import Card.Ability.Abilities;
import Card.Ability.Ability;
import Card.Ability.ActionAbility;
import Game.Game;

import java.util.UUID;

public interface ICard {

    UUID getOwnerId();

    String getCardNumber();

    void setOwnerId(UUID ownerId);

    Abilities<Ability> getAbilities(Game game);

    void setActionAbility(ActionAbility ability);

}
