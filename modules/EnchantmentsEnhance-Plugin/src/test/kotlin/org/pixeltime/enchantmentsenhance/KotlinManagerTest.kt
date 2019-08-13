package org.pixeltime.enchantmentsenhance

import junit.framework.Assert
import junit.framework.TestCase
import org.junit.Test

class KotlinManagerTest : TestCase() {
    @Test
    fun testItemUpgrade() {
        var level = 0
        var aimingLevel = 10
        for (i in level + 1..aimingLevel) {

        }

        level = 10
        aimingLevel = 1
        for (i in level downTo aimingLevel + 1) {
            assertTrue(i != 0)
        }

    }

    fun testForloop() {
        var current = 1
        var notexceeding = 7
        for (i in current until notexceeding) {
            assertTrue(i < notexceeding)
        }
    }
}