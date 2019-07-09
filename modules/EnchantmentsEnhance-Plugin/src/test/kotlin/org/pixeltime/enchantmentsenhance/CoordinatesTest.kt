/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

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