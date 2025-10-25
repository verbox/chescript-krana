package org.chescript.krana.common.model.action;

import org.chescript.krana.common.model.board.BoardContext;
import org.chescript.krana.common.model.board.PlayerState;
import org.chescript.krana.common.util.GameLog;

/**
 * StateChange that modifies player's resources (e.g., ink).
 */
public class ModifyResourceChange implements StateChange {

    private final String playerId;
    private final int delta;

    public ModifyResourceChange(String playerId, int delta) {
        this.playerId = playerId;
        this.delta = delta;
    }

    @Override
    public void apply(BoardContext ctx) {
        GameLog.event(ctx, "ModifyResourceChange.apply: playerId=" + playerId + " delta=" + delta);
        if (playerId == null) {
            GameLog.warn(ctx, "ModifyResourceChange: null playerId");
            return;
        }
        PlayerState ps = ctx.getPlayer(playerId).orElse(null);
        if (ps == null) {
            GameLog.warn(ctx, "ModifyResourceChange: player not found " + playerId);
            return;
        }
        ps.modifyResources(delta);
        GameLog.event(ctx, "ModifyResourceChange: player " + playerId + " resources change " + delta + " -> " + ps.getResources());
    }
}
