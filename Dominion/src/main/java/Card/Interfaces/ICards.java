package Card.Interfaces;

import Card.Card;
import Game.Game;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ICards extends Set<UUID> {
    void add(Card card);

    Card get(UUID cardId, Game game);

    void remove(Card card);

    void setOwner(UUID ownerId, Game game);

    void addAll(List<Card> createCards);

    void addAll(Set<Card> createCards);

    Set<Card> getCards(Game game);

    String getValue(Game game);

    Collection<Card> getUniqueCards(Game game);

    Card getRandom(Game game);

    ICards copy();
}
