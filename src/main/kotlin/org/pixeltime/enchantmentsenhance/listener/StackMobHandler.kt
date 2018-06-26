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

package org.pixeltime.enchantmentsenhance.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.pixeltime.enchantmentsenhance.manager.DropManager
import org.pixeltime.stackmob.events.StackKilledEvent

class StackMobHandler : EnchantmentListener() {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onDeath(e: StackKilledEvent) {
        if (getResult(e.numberKilled, DropManager.killingChance)) {
            DropManager.randomDrop(e.player, DropManager.killingLootTable)
        }
    }

    companion object {
        @JvmStatic
        fun bernoulli(repeat: Int, chance: Double): Double {
            return Math.pow((1 - chance), repeat.toDouble())
        }

        @JvmStatic
        fun getResult(repeat: Int, chance: Double): Boolean {
            if (repeat == 0) {
                return false;
            }
            return (1 - bernoulli(repeat, chance) > Math.random())
        }
    }
}