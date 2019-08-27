package Game.Card.Effect.Type.Instant;

import Client.DominionManager;
import Client.GUI.Screen.Game.GamePane;
import Constant.ActionInProgress;
import Game.Card.Effect.Type.InstantEffect;
import Game.Game;
import javafx.scene.control.Button;

public class DiscardXToDrawXEffect extends InstantEffect {

    public DiscardXToDrawXEffect() {
        super("Discard card(s) to draw card(s)");
    }

    @Override
    public void apply() {
        Game.setActionInProgress(ActionInProgress.DISCARD);

        GamePane pane = (GamePane) DominionManager.getInstance().getContent();
        Button test = pane.specialAction();
        test.setOnAction(e -> {
            test.setText("End Action");
            test.setStyle("-fx-text-fill: black; -fx-background: black; -fx-background-color: turquoise");

            DominionManager.getInstance().getGame().drawCard(pane.getCount());
            Game.setActionInProgress(ActionInProgress.NO_ACTION);
            pane.updateHand();
            pane.makeHandInteractable();
        });
    }
}
