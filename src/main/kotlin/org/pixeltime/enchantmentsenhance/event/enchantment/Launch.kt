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
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.util.Vector
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Launch : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "launch"))
    @EventHandler
    fun onEntityDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.entity is Player && entityDamageByEntityEvent.damager is Player) {
            val player = entityDamageByEntityEvent.entity as Player
            val player2 = entityDamageByEntityEvent.damager as Player

            try {
                val armorContents = player2.inventory.armorContents + IM.getAccessorySlots(player2)
                for (itemStack in armorContents) {

                    val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                    if (level > 0 && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("launch.$level.chance")) {
                        player.velocity = Vector(0, SettingsManager.enchant.getInt("launch.$level.height"), 0)
                    }

                }
            } catch (ex: Exception) {

            }
        }
    }
}
