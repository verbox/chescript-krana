package org.chescript.krana.common;

import junit.framework.TestCase;

import java.util.List;

import org.chescript.krana.common.model.CardImage;
import org.chescript.krana.common.model.CardPhenotype;
import org.chescript.krana.common.model.action.ActionEffect;
import org.chescript.krana.common.model.action.DamageChange;
import org.chescript.krana.common.model.instance.CardInstance;
import org.chescript.krana.common.model.board.BoardContext;
import org.chescript.krana.common.model.board.PlayerState;
import org.chescript.krana.common.model.board.Zone;

public class DamageChangeTest extends TestCase {

    public void testDamageKillsCardAndMovesToGraveyard() {
        BoardContext ctx = new BoardContext();
        PlayerState p = new PlayerState("p1");
        ctx.addPlayer(p);

        CardPhenotype ph = new CardPhenotype("X","X","desc",1,2,0,new CardImage("id","file"));
        CardInstance c = new CardInstance(ph, "p1", Zone.BATTLEFIELD);
        p.getBattlefield().add(c);

        // initial hp from willpower = 2
        assertEquals(2, c.getHitPoints());

        ActionEffect eff = ActionEffect.defaultAction();
        eff.addChange(new DamageChange(c.getId(), 3)); // overkill

        ctx.applyEffect(eff);

        // card should be removed from battlefield and placed in graveyard
        assertEquals(0, p.getBattlefield().size());
        assertEquals(1, p.getGraveyard().size());
        assertTrue(ctx.getEventLog().stream().anyMatch(s -> s.contains("died")));
    }
}

