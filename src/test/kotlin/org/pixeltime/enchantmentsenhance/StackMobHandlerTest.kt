/*
 *     Copyright (C) 2017-Present HealPotion
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