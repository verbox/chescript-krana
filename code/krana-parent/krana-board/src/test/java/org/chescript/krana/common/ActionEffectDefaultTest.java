package org.chescript.krana.common;

import junit.framework.TestCase;

import org.chescript.krana.common.model.action.ActionEffect;

public class ActionEffectDefaultTest extends TestCase {

    public void testDefaultActionNoop() {
        ActionEffect eff = ActionEffect.defaultAction();
        assertNotNull(eff);
        assertTrue(eff.isNoop());
    }
}

