package org.pixeltime.enchantmentsenhance

import junit.framework.TestCase
import org.junit.Test
import org.pixeltime.enchantmentsenhance.listener.StackMobHandler

class StackMobHandlerTest : TestCase() {
    @Test
fun testBernoulli() {
    assertTrue(StackMobHandler.bernoulli(100, 0.0001) < 1)
    assertTrue(StackMobHandler.getResult(1, 1.0))
    assertFalse(StackMobHandler.getResult(1, 0.0))
    assertFalse(StackMobHandler.getResult(0, 1.0))
}
}