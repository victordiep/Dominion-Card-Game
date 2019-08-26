package Game.Card.Effect.Type.Instant;

import Client.DominionManager;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.InstantEffect;

public class DrawCardEffect extends InstantEffect {
    private final int cardsToDraw;

    public DrawCardEffect(int cardsToDraw) {
        super("Draw " + cardsToDraw + " card(s)");
        this.cardsToDraw = cardsToDraw;
    }

    @Override
    public void apply() {
        DominionManager.getInstance().getGame().drawCard(cardsToDraw);
    }
}
