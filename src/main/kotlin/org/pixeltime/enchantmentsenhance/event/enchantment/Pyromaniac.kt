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
import org.bukkit.event.entity.EntityDamageEvent
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Pyromaniac : Listener {
    @EventHandler
    fun onDamage(entityDamageEvent: EntityDamageEvent) {
        if (entityDamageEvent.entity is Player) {
            val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "pyromaniac"))
            val player = entityDamageEvent.entity as Player
            if (entityDamageEvent.cause == EntityDamageEvent.DamageCause.FIRE || entityDamageEvent.cause == EntityDamageEvent.DamageCause.FIRE_TICK) {
                try {
                    val armorContents = player.inventory.armorContents + IM.getAccessorySlots(player)
                    for (itemStack in armorContents) {
                        if (itemStack.hasItemMeta() && itemStack.itemMeta.hasLore()) {
                            val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                            if (level > 0 && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("pyromaniac.$level.chance")) {
                                player.health = 20.0
                                player.foodLevel = 20
                            }
                        }
                    }
                } catch (ex: Exception) {
                }
            }
        }
    }
}
