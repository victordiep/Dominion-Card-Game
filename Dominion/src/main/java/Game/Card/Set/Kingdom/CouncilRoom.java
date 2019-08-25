package Game.Card.Set.Kingdom;

import Game.Card.Card;

import static Constant.CardSettings.DominionCards.KINGDOM_CARD_COSTS;

public class CouncilRoom extends Card {

    public CouncilRoom() {
        super("CouncilRoom", KINGDOM_CARD_COSTS.get("CouncilRoom"));
    }

    @Override
    public void activate() {

    }

    @Override
    public Card makeCopy() {
        return null;
    }
}
