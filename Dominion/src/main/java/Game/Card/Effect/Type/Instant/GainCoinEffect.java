package Game.Card.Effect.Type.Instant;

import Client.DominionManager;
import Game.Card.Effect.Type.InstantEffect;

public class GainCoinEffect extends InstantEffect {
    private final int coins;

    public GainCoinEffect(int coins) {
        super("Gain " + coins + " coin(s)");
        this.coins = coins;
    }

    @Override
    public void apply() {
        DominionManager.getInstance().getGame().addCoins(coins);
    }
}
