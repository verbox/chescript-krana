package org.chescript.krana.common.util;

import org.chescript.krana.common.model.board.BoardContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper for logging game-related events: it writes to a logger (DEBUG/WARN/ERROR)
 * and appends the same message to the BoardContext event log. Use this helper
 * from places where you want the event to be visible both in logs and in the
 * game's event stream.
 */
public final class GameLog {

    private static final Logger LOGGER = LoggerFactory.getLogger("org.chescript.krana.game");

    private GameLog() {}

    public static void event(BoardContext ctx, String message) {
        if (message == null) return;
        LOGGER.debug(message);
        if (ctx != null) ctx.appendEvent(message);
    }

    public static void warn(BoardContext ctx, String message) {
        if (message == null) return;
        LOGGER.warn(message);
        if (ctx != null) ctx.appendEvent(message);
    }

    public static void error(BoardContext ctx, String message, Throwable t) {
        if (message == null) return;
        LOGGER.error(message, t);
        if (ctx != null) ctx.appendEvent(message);
    }
}

