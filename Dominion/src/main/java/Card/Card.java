package Card;

import Card.Interfaces.ICard;

import java.util.UUID;

public class Card implements ICard {
    @Override
    public UUID getOwnerId() {
        return null;
    }

    @Override
    public String getCardNumber() {
        return null;
    }
}
