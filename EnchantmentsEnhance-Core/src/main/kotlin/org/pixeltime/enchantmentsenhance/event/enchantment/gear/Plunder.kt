/*
 *     Copyright (C) 2017-Present HealPot
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

package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDeathEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.util.Util

class Plunder : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("More XP from entities", "击杀生物更多经验")
    }

    override fun lang(): Array<String> {
        return arrayOf("掠夺")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDeath(entityDeathEvent: EntityDeathEvent) {

        if (entityDeathEvent.entity.killer is Player && entityDeathEvent.entity !is Player) {
            val killer = entityDeathEvent.entity.killer
            if (killer.itemInHand != null && (Util.getMainHand(killer)).hasItemMeta() && Util.getMainHand(killer).itemMeta.hasLore() && killer.inventory.itemInMainHand.itemMeta.lore.contains(this.name() + " I")) {
                entityDeathEvent.droppedExp = 20
            }
        }
    }
}
