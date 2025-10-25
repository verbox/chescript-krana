package org.chescript.krana.common;

import junit.framework.TestCase;

import java.util.UUID;

import org.chescript.krana.common.model.CardImage;
import org.chescript.krana.common.model.CardPhenotype;
import org.chescript.krana.common.model.action.ActionEffect;
import org.chescript.krana.common.model.action.MoveCardChange;
import org.chescript.krana.common.model.instance.CardInstance;
import org.chescript.krana.common.model.board.BoardContext;
import org.chescript.krana.common.model.board.PlayerState;
import org.chescript.krana.common.model.board.Zone;

public class MoveCardChangeTest extends TestCase {

    public void testMoveCardChangeMovesCard() {
        BoardContext ctx = new BoardContext();
        PlayerState p = new PlayerState("p1");
        ctx.addPlayer(p);

        CardPhenotype ph = new CardPhenotype("Foo","F","desc",1,1,0,new CardImage("id","file"));
        CardInstance c = new CardInstance(ph, "p1", Zone.DECK);
        p.getDeck().addFirst(c);

        // ensure card is in deck
        assertEquals(1, p.getDeck().size());

        ActionEffect eff = ActionEffect.defaultAction();
        eff.addChange(new MoveCardChange(c.getId(), Zone.HAND));

        ctx.applyEffect(eff);

        // after applying effect, card should be in hand
        assertEquals(0, p.getDeck().size());
        assertEquals(1, p.getHand().size());
        assertEquals(c.getId(), p.getHand().get(0).getId());
    }
}

