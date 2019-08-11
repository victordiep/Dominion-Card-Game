package Game;

import Game.Interfaces.IGame;

/*
 * Represents the game for the local player
 *
 * The game will only ever store information on the one local player rather than all players in the game.
 * This makes a it easier to manage the game as information such as deck shuffles, card order position, etc.
 * does not need to be sent to all players.
 *
 * As players purchase cards from the supply pile, a message will be sent to all players indicating what has been
 * bought so that their game can update their local supply pile to reflect the current state of the game.
 */

public class Game implements IGame {
    @Override
    public int getNumPlayers() {
        return 0;
    }
}
