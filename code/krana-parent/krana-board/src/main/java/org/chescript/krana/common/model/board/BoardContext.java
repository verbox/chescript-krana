package org.chescript.krana.common.model.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.chescript.krana.common.model.instance.CardInstance;
import org.chescript.krana.common.model.action.ActionEffect;
import org.chescript.krana.common.model.action.StateChange;
import org.chescript.krana.common.util.GameLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Minimal board context storing players and an event log. Contains helper methods
 * to find and move cards. This module contains only game logic and is independent
 * from I/O/serialization.
 */
public class BoardContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardContext.class);

    private final Map<String, PlayerState> players = new HashMap<>();
    private final List<String> eventLog = new ArrayList<>();
    private final List<StateChange> pendingChanges = new ArrayList<>();
    private String activePlayerId;
    private int turnNumber = 1;

    public BoardContext() {
    }

    public void addPlayer(PlayerState p) {
        players.put(p.getPlayerId(), p);
        LOGGER.debug("addPlayer: {}", p.getPlayerId());
    }

    public Optional<PlayerState> getPlayer(String playerId) {
        return Optional.ofNullable(players.get(playerId));
    }

    public List<String> getEventLog() {
        return eventLog;
    }

    public void appendEvent(String e) {
        if (e != null) eventLog.add(e);
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void nextTurn() {
        turnNumber++;
        LOGGER.debug("nextTurn -> {}", turnNumber);
    }

    public void applyEffect(ActionEffect effect) {
        if (effect == null) return;
        LOGGER.debug("Applying ActionEffect with {} changes and {} events", effect.getChanges().size(), effect.getEvents().size());
        effect.apply(this);
    }

    public Optional<CardInstance> findCard(UUID id) {
        for (PlayerState p : players.values()) {
            Optional<CardInstance> c = p.findCard(id);
            if (c.isPresent()) return c;
        }
        return Optional.empty();
    }

    /**
     * Move a card instance to the target zone. This removes the card from any
     * existing zone and places it into the appropriate collection for its owner.
     */
    public void moveCard(CardInstance card, Zone target) {
        if (card == null) return;
        // remove from all players
        for (PlayerState p : players.values()) {
            p.remove(card);
        }
        // place into owner's zone
        PlayerState owner = players.get(card.getOwnerId());
        if (owner == null) {
            String msg = "moveCard: owner not found for card " + card.getId();
            GameLog.warn(this, msg);
            return;
        }
        card.moveTo(target);
        switch (target) {
            case HAND:
                owner.getHand().add(card);
                break;
            case BATTLEFIELD:
                owner.getBattlefield().add(card);
                break;
            case GRAVEYARD:
                owner.getGraveyard().add(card);
                break;
            case DECK:
                owner.getDeck().addFirst(card);
                break;
            case EXILE:
            default:
                // for simplicity if EXILE or unknown, place in graveyard
                owner.getGraveyard().add(card);
                break;
        }
        GameLog.event(this, "moveCard: moved card " + card.getId() + " to " + target + " (owner=" + owner.getPlayerId() + ")");
    }

    /**
     * Add a StateChange to the pending queue. This allows StateChange.apply to
     * schedule follow-up changes while an effect is being applied.
     */
    public void addChange(StateChange change) {
        if (change != null) {
            pendingChanges.add(change);
        }
    }

    /**
     * Drain and return pending changes. The returned list is a copy and draining
     * clears the internal queue.
     */
    public List<StateChange> drainPendingChanges() {
        List<StateChange> copy = new ArrayList<>(pendingChanges);
        pendingChanges.clear();
        return copy;
    }

}
