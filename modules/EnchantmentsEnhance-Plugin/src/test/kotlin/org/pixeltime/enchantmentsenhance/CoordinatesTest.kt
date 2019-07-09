package org.pixeltime.enchantmentsenhance

import junit.framework.TestCase
import org.junit.Test
import org.pixeltime.enchantmentsenhance.gui.MenuCoord
import org.pixeltime.enchantmentsenhance.util.Util

class CoordinatesTest : TestCase() {
    @Test
    fun testCoords() {
        for (i in MenuCoord.getPlaceHolderCoords()) {
            assertTrue(i < 55)
        }
    }

    @Test
    fun testOrder() {
        for (i in 0..54) {
            assertTrue(Util.getSlot((i % 9) + 1, (i / 9) + 1) >= 0)
            // println("${(i % 9) + 1}, ${(i / 9) + 1} : ${Util.getSlot((i % 9) + 1, (i / 9) + 1)}")
        }
    }
}