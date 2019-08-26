package Game.Card.Effect.Type.Instant;

import Client.DominionManager;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.InstantEffect;

public class GainBuyEffect extends InstantEffect {
    private int buys;

    public GainBuyEffect(int buys) {
        super("Gain " + buys + " buy(s)");
        this.buys = buys;
    }

    @Override
    public void apply() {
        DominionManager.getInstance().getGame().addBuys(buys);
    }
}