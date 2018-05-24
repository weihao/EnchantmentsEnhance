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
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Pyromaniac : Listener {
    @EventHandler
    fun onDamage(entityDamageEvent: EntityDamageEvent) {
        if (entityDamageEvent.entity is Player) {
            val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "pyromaniac"))
            val player = entityDamageEvent.entity as Player
            if (entityDamageEvent.cause == EntityDamageEvent.DamageCause.FIRE || entityDamageEvent.cause == EntityDamageEvent.DamageCause.FIRE_TICK) {
                val n = (Math.random() * 100.0).toInt()
                try {
                    val armorContents = player.inventory.armorContents
                    val length = armorContents.size
                    var i = 0
                    while (i < length) {
                        val itemStack = armorContents[i]
                        if (itemStack != null) {
                            if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("pyromaniac.level_I.chance")) {
                                player.health = 20.0
                                player.foodLevel = 20
                            }
                            if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("pyromaniac.level_II.chance")) {
                                player.health = 20.0
                                player.foodLevel = 20
                            }
                            if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("pyromaniac.level_III.chance")) {
                                player.health = 20.0
                                player.foodLevel = 20
                            }
                            if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " IV") && n < SettingsManager.enchant.getInt("pyromaniac.level_IV.chance")) {
                                player.health = 20.0
                                player.foodLevel = 20
                            }
                            if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " V") && n < SettingsManager.enchant.getInt("pyromaniac.level_V.chance")) {
                                player.health = 20.0
                                player.foodLevel = 20
                            }
                        }
                        ++i
                    }
                } catch (ex: Exception) {
                }

            }
        }
    }
}
