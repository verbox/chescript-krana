package org.chescript.krana.common.model.instance;

import java.util.UUID;

import org.chescript.krana.common.model.CardPhenotype;
import org.chescript.krana.common.model.board.Zone;

/**
 * Runtime instance of a card on a board or in a player's collection.
 */
public class CardInstance {
    private final UUID id = UUID.randomUUID();
    private final CardPhenotype phenotype;
    private final String ownerId;
    private Zone zone;
    private boolean exhausted = false;
    private int hitPoints;

    public CardInstance(CardPhenotype phenotype, String ownerId, Zone initialZone) {
        this.phenotype = phenotype;
        this.ownerId = ownerId;
        this.zone = initialZone;
        // initialize hit points from phenotype (use willpower as a simple proxy)
        this.hitPoints = phenotype != null ? phenotype.getWillpower() : 0;
    }

    public UUID getId() {
        return id;
    }

    public CardPhenotype getPhenotype() {
        return phenotype;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Zone getZone() {
        return zone;
    }

    public void moveTo(Zone z) {
        this.zone = z;
    }

    public boolean isExhausted() {
        return exhausted;
    }

    public void setExhausted(boolean exhausted) {
        this.exhausted = exhausted;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void applyDamage(int amount) {
        this.hitPoints -= amount;
    }
}
