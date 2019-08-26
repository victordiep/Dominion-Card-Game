package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class Bureaucrat extends Card {
    public Bureaucrat() {
        super("Bureaucrat", CARD_COSTS.get("Bureaucrat"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
