package Game.Card.Effect.Type.Instant;

import Client.DominionManager;
import Constant.ActionInProgress;
import Game.Card.Effect.Type.InstantEffect;
import Game.Game;

public class GainCardFromSupplyEffect extends InstantEffect {
    private int costCap;

    public GainCardFromSupplyEffect(int costCap) {
        super("Gain card from the supply");

        this.costCap = costCap;
    }

    @Override
    public void apply() {
        DominionManager.getInstance().getGame().setCostCap(costCap);
        Game.setActionInProgress(ActionInProgress.GAIN);
    }
}
