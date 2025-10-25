package org.chescript.krana.common.model.action;

import java.util.Optional;
import java.util.UUID;

import org.chescript.krana.common.model.instance.CardInstance;
import org.chescript.krana.common.model.board.BoardContext;
import org.chescript.krana.common.model.board.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * StateChange that applies damage to a card. If card's hit points drop to <= 0,
 * the card is moved to the graveyard.
 */
public class DamageChange implements StateChange {

    private static final Logger LOGGER = LoggerFactory.getLogger(DamageChange.class);

    private final UUID cardId;
    private final int amount;

    public DamageChange(UUID cardId, int amount) {
        this.cardId = cardId;
        this.amount = amount;
    }

    @Override
    public void apply(BoardContext ctx) {
        LOGGER.debug("DamageChange.apply: cardId={} amount={}", cardId, amount);
        Optional<CardInstance> oc = ctx.findCard(cardId);
        if (oc.isPresent()) {
            CardInstance c = oc.get();
            c.applyDamage(amount);
            LOGGER.debug("DamageChange: applied {} to {} (hp={})", amount, cardId, c.getHitPoints());
            ctx.appendEvent("DamageChange: applied " + amount + " to " + cardId + ", hp=" + c.getHitPoints());
            if (c.getHitPoints() <= 0) {
                ctx.appendEvent("DamageChange: card " + cardId + " died, moving to graveyard");
                LOGGER.debug("DamageChange: card {} died, moving to graveyard", cardId);
                ctx.moveCard(c, Zone.GRAVEYARD);
            }
        } else {
            String msg = "DamageChange: card not found " + cardId;
            ctx.appendEvent(msg);
            LOGGER.warn(msg);
        }
    }
}
