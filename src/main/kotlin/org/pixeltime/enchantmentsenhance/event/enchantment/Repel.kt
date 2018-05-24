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

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.ChatColor
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Repel : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamaged(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "repel"))
        if (entityDamageByEntityEvent.entity is Player) {
            try {
                val damager = entityDamageByEntityEvent.damager
                val player = entityDamageByEntityEvent.entity as Player
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.world).getApplicableRegions(player.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                    return
                }
                val armorContents = player.inventory.armorContents
                val length = armorContents.size
                var i = 0
                while (i < length) {
                    val itemStack = armorContents[i]
                    if (itemStack != null) {
                        if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("repel.level_I.chance")) {
                            damager.velocity = player.location.direction.multiply(SettingsManager.enchant.getInt("repel.level_I.power"))
                        }
                        if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("repel.level_II.chance")) {
                            damager.velocity = player.location.direction.multiply(SettingsManager.enchant.getInt("repel.level_II.power"))
                        }
                        if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("repel.level_III.chance")) {
                            damager.velocity = player.location.direction.multiply(SettingsManager.enchant.getInt("repel.level_III.power"))
                        }
                    }
                    ++i
                }
            } catch (ex: Exception) {
            }

        }
    }
}
