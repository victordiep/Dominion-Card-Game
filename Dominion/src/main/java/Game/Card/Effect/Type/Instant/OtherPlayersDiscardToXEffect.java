package Game.Card.Effect.Type.Instant;

import Client.DominionManager;
import Game.Card.Effect.Type.InstantEffect;
import protobuf.PacketProtos.Packet;

import java.io.IOException;

public class OtherPlayersDiscardToXEffect extends InstantEffect {
    private final int discardTo;

    public OtherPlayersDiscardToXEffect(int discardTo) {
        super("Other players discard to " + discardTo);
        this.discardTo = discardTo;
    }

    @Override
    public void apply() {
        try {
            DominionManager.getInstance().sendEvent(Packet.newBuilder()
                    .setUUID(DominionManager.getInstance().getGame().getPlayerId().toString())
                    .setType(Packet.Type.PLAY_CARD)
                    .addMessage("Militia")
                    .addAddon(Integer.toString(discardTo))
                    .build());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

