package org.chescript.krana.common.model.action;

import java.util.Optional;
import java.util.UUID;

import org.chescript.krana.common.model.instance.CardInstance;
import org.chescript.krana.common.model.board.BoardContext;
import org.chescript.krana.common.model.board.Zone;
import org.chescript.krana.common.util.GameLog;

/**
 * StateChange that moves a card (by id) to a target zone.
 */
public class MoveCardChange implements StateChange {

    private final UUID cardId;
    private final Zone target;

    public MoveCardChange(UUID cardId, Zone target) {
        this.cardId = cardId;
        this.target = target;
    }

    @Override
    public void apply(BoardContext ctx) {
        GameLog.event(ctx, "MoveCardChange.apply: cardId=" + cardId + " target=" + target);
        Optional<CardInstance> oc = ctx.findCard(cardId);
        if (oc.isPresent()) {
            ctx.moveCard(oc.get(), target);
            GameLog.event(ctx, "MoveCardChange: moved card " + cardId + " to " + target);
        } else {
            String msg = "MoveCardChange: card not found " + cardId;
            GameLog.warn(ctx, msg);
        }
    }

}
