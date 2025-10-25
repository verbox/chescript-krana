package org.chescript.krana.common.model.action;

import org.chescript.krana.common.model.board.BoardContext;

/**
 * A state change represents a single, apply-able modification to the BoardContext.
 */
public interface StateChange {
    void apply(BoardContext ctx);
}

