package org.chescript.krana.common;

import junit.framework.TestCase;

import org.chescript.krana.common.model.action.ActionEffect;
import org.chescript.krana.common.model.action.ModifyResourceChange;
import org.chescript.krana.common.model.board.BoardContext;
import org.chescript.krana.common.model.board.PlayerState;

public class ModifyResourceChangeTest extends TestCase {

    public void testModifyResources() {
        BoardContext ctx = new BoardContext();
        PlayerState p = new PlayerState("p1");
        ctx.addPlayer(p);

        assertEquals(0, p.getResources());

        ActionEffect eff = ActionEffect.defaultAction();
        eff.addChange(new ModifyResourceChange("p1", 5));

        ctx.applyEffect(eff);

        assertEquals(5, p.getResources());
        assertTrue(ctx.getEventLog().stream().anyMatch(s -> s.contains("resources change")));
    }
}

