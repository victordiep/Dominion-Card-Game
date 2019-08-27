package Game.Card.Effect.Type.Instant;

import Client.DominionManager;
import Client.GUI.Screen.Game.GamePane;
import Game.Card.Effect.Effect;
import Game.Card.Effect.Type.InstantEffect;

public class PlayCardActivateEffectEffect extends InstantEffect {
    private final String card;
    private final Effect effect;

    public PlayCardActivateEffectEffect(String card, Effect effect) {
        super("Play " + card + " to activate:" + effect.getText());

        this.card = card;
        this.effect = effect;
    }

    @Override
    public void apply() {
        GamePane pane = (GamePane) DominionManager.getInstance().getContent();
        pane.setSpecialCard(card);
        pane.setSpecialEffect(effect);
    }
}