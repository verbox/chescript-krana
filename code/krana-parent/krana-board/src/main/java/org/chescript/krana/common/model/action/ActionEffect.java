package org.chescript.krana.common.model.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.chescript.krana.common.model.board.BoardContext;
import org.chescript.krana.common.util.GameLog;

/**
 * ActionEffect describes the result of executing an action: a list of state changes
 * and human-readable events/messages. It is intentionally lightweight; concrete
 * state changes implement {@link StateChange} and are applied by {@link BoardContext}.
 */
public class ActionEffect {

    private boolean success = true;
    private final List<StateChange> changes = new ArrayList<>();
    private final List<String> events = new ArrayList<>();

    public ActionEffect() {
    }

    public static ActionEffect defaultAction() {
        return new ActionEffect();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<StateChange> getChanges() {
        return Collections.unmodifiableList(changes);
    }

    public List<String> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public void addChange(StateChange c) {
        if (c != null) {
            changes.add(c);
        }
    }

    public void addEvent(String e) {
        if (e != null) {
            events.add(e);
        }
    }

    public boolean isNoop() {
        return changes.isEmpty() && events.isEmpty();
    }

    /**
     * Merge another effect into this one (useful when composing effects).
     */
    public void merge(ActionEffect other) {
        if (other == null) return;
        this.success = this.success && other.success;
        this.changes.addAll(other.changes);
        this.events.addAll(other.events);
    }

    /**
     * Apply this effect to the given board context by running each StateChange
     * and appending events to the board's event log.
     */
    public void apply(BoardContext ctx) {
        for (StateChange c : new ArrayList<>(changes)) {
            c.apply(ctx);
        }
        for (String e : events) {
            GameLog.event(ctx, e);
        }
    }

}
