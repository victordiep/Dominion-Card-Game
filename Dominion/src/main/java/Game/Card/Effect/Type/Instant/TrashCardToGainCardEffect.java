package Game.Card.Effect.Type.Instant;

import Client.DominionManager;
import Constant.ActionInProgress;
import Constant.CardType;
import Game.Card.Effect.Type.InstantEffect;
import Game.Game;

public class TrashCardToGainCardEffect extends InstantEffect {
    private int costAddition;
    private CardType cardType = null;

    public TrashCardToGainCardEffect(int costAddition) {
        super("Gain card from the supply");

        this.costAddition = costAddition;
    }

    public TrashCardToGainCardEffect(int costAddition, CardType cardType) {
        super("Gain card from the supply");

        this.costAddition = costAddition;
        this.cardType = cardType;
    }


    @Override
    public void apply() {
        Game.setActionInProgress(ActionInProgress.TRASH);
        DominionManager.getInstance().getGame().setCostCap(costAddition);
        DominionManager.getInstance().getGame().setGainType(cardType);
    }
}
