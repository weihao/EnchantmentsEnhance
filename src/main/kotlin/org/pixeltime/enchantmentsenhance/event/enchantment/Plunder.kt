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

package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Plunder : Listener {
    @EventHandler
    fun onDeath(entityDeathEvent: EntityDeathEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "plunder"))
        if (entityDeathEvent.entity.killer is Player && entityDeathEvent.entity !is Player) {
            val killer = entityDeathEvent.entity.killer
            if (killer.itemInHand != null && killer.itemInHand.hasItemMeta() && killer.itemInHand.itemMeta.hasLore() && killer.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                entityDeathEvent.droppedExp = 20
            }
        }
    }
}
