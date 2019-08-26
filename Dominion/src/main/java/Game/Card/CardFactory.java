package Game.Card;

import Game.Card.Set.Curse.*;
import Game.Card.Set.Kingdom.*;
import Game.Card.Set.Treasure.*;
import Game.Card.Set.Victory.*;
import Game.Game;

public class CardFactory {

    private Game game;

    public CardFactory(Game game) {
        this.game = game;
    }

    public synchronized Card createCard(String name) {
        // Kingdom CardPile
        if (name.equalsIgnoreCase("Artisan")) {
            return new Artisan();
        }
        else if (name.equalsIgnoreCase("Bandit")) {
            return new Bandit();
        }
        else if (name.equalsIgnoreCase("Bureaucrat")) {
            return new Bureaucrat();
        }
        else if (name.equalsIgnoreCase("Cellar")) {
            return new Cellar();
        }
        else if (name.equalsIgnoreCase("Chapel")) {
            return new Chapel();
        }
        else if (name.equalsIgnoreCase("CouncilRoom")) {
            return new CouncilRoom();
        }
        else if (name.equalsIgnoreCase("Festival")) {
            return new Festival();
        }
        else if (name.equalsIgnoreCase("Gardens")) {
            return new Gardens();
        }
        else if (name.equalsIgnoreCase("Harbinger")) {
            return new Harbinger();
        }
        else if (name.equalsIgnoreCase("Laboratory")) {
            return new Laboratory();
        }
        else if (name.equalsIgnoreCase("Library")) {
            return new Library();
        }
        else if (name.equalsIgnoreCase("Market")) {
            return new Market();
        }
        else if (name.equalsIgnoreCase("Merchant")) {
            return new Merchant();
        }
        else if (name.equalsIgnoreCase("Militia")) {
            return new Militia();
        }
        else if (name.equalsIgnoreCase("Mine")) {
            return new Mine();
        }
        else if (name.equalsIgnoreCase("Moat")) {
            return new Moat();
        }
        else if (name.equalsIgnoreCase("Moneylender")) {
            return new Moneylender();
        }
        else if (name.equalsIgnoreCase("Poacher")) {
            return new Poacher();
        }
        else if (name.equalsIgnoreCase("Remodel")) {
            return new Remodel();
        }
        else if (name.equalsIgnoreCase("Sentry")) {
            return new Sentry();
        }
        else if (name.equalsIgnoreCase("Smithy")) {
            return new Smithy();
        }
        else if (name.equalsIgnoreCase("ThroneRoom")) {
            return new ThroneRoom();
        }
        else if (name.equalsIgnoreCase("Vassal")) {
            return new Vassal();
        }
        else if (name.equalsIgnoreCase("Village")) {
            return new Village();
        }
        else if (name.equalsIgnoreCase("Witch")) {
            return new Witch();
        }
        else if (name.equalsIgnoreCase("Workshop")) {
            return new Workshop();
        }

        // Treasure CardPile
        if (name.equalsIgnoreCase("Copper")) {
            return new Copper();
        }
        else if (name.equalsIgnoreCase("Silver")) {
            return new Silver();
        }
        else if (name.equalsIgnoreCase("Gold")) {
            return new Gold();
        }

        // Victory CardPile
        if (name.equalsIgnoreCase("Estate")) {
            return new Estate();
        }
        else if (name.equalsIgnoreCase("Duchy")) {
            return new Duchy();
        }
        else if (name.equalsIgnoreCase("Province")) {
            return new Province();
        }

        // Curse CardPile
        if (name.equalsIgnoreCase("Curse")) {
            return new Curse();
        }

        return null;

    }


}
