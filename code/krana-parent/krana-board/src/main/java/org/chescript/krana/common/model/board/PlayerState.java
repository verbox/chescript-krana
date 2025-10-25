package org.chescript.krana.common.model.board;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.chescript.krana.common.model.instance.CardInstance;

/**
 * Minimal representation of a player's state: zones (deck, hand, battlefield, graveyard)
 */
public class PlayerState {
    private final String playerId;
    private final Deque<CardInstance> deck = new LinkedList<>();
    private final List<CardInstance> hand = new ArrayList<>();
    private final List<CardInstance> battlefield = new ArrayList<>();
    private final List<CardInstance> graveyard = new ArrayList<>();
    private int resources = 0; // player's generic resource (ink etc.)

    public PlayerState(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public Deque<CardInstance> getDeck() {
        return deck;
    }

    public List<CardInstance> getHand() {
        return hand;
    }

    public List<CardInstance> getBattlefield() {
        return battlefield;
    }

    public List<CardInstance> getGraveyard() {
        return graveyard;
    }

    public int getResources() {
        return resources;
    }

    public void modifyResources(int delta) {
        this.resources += delta;
    }

    public void drawToHand(int count) {
        for (int i = 0; i < count; i++) {
            CardInstance c = deck.pollFirst();
            if (c == null) break;
            c.moveTo(Zone.HAND);
            hand.add(c);
        }
    }

    public Optional<CardInstance> findCard(UUID id) {
        for (CardInstance c : hand) if (c.getId().equals(id)) return Optional.of(c);
        for (CardInstance c : battlefield) if (c.getId().equals(id)) return Optional.of(c);
        for (CardInstance c : graveyard) if (c.getId().equals(id)) return Optional.of(c);
        for (CardInstance c : deck) if (c.getId().equals(id)) return Optional.of(c);
        return Optional.empty();
    }

    public void remove(CardInstance c) {
        hand.remove(c);
        battlefield.remove(c);
        graveyard.remove(c);
        deck.remove(c);
    }

}
