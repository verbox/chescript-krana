package org.chescript.krana.common;

import junit.framework.TestCase;

import org.chescript.krana.common.model.CardImage;
import org.chescript.krana.common.model.CardPhenotype;
import org.chescript.krana.common.model.instance.CardInstance;
import org.chescript.krana.common.model.board.PlayerState;
import org.chescript.krana.common.model.board.Zone;

public class PlayerStateDrawTest extends TestCase {

    public void testDrawToHand() {
        PlayerState p = new PlayerState("p1");
        CardPhenotype ph = new CardPhenotype("A","A","d",1,1,0,new CardImage("id","file"));
        CardInstance c1 = new CardInstance(ph, "p1", Zone.DECK);
        CardInstance c2 = new CardInstance(ph, "p1", Zone.DECK);
        p.getDeck().addFirst(c1);
        p.getDeck().addFirst(c2);

        assertEquals(2, p.getDeck().size());
        p.drawToHand(1);
        assertEquals(1, p.getDeck().size());
        assertEquals(1, p.getHand().size());
    }
}

