package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.CARD_COSTS;

public class CouncilRoom extends Card {

    public CouncilRoom() {
        super("CouncilRoom", CARD_COSTS.get("CouncilRoom"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
