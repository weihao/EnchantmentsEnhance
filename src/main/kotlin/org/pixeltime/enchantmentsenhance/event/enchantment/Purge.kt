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
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Purge : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "purge"))
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            try {
                val player = entityDamageByEntityEvent.damager as Player
                val player2 = entityDamageByEntityEvent.entity as Player
                val n = (Math.random() * 100.0).toInt()
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player2.world).getApplicableRegions(player2.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                    return
                }
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("purge.level_I.chance")) {
                    player2.world.strikeLightningEffect(player2.location)
                    val iterator = player2.activePotionEffects.iterator()
                    while (iterator.hasNext()) {
                        player2.removePotionEffect((iterator.next() as PotionEffect).type)
                        player2.damage(2.0)
                    }
                }
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("purge.level_II.chance")) {
                    player2.world.strikeLightningEffect(player2.location)
                    val iterator2 = player2.activePotionEffects.iterator()
                    while (iterator2.hasNext()) {
                        player2.removePotionEffect((iterator2.next() as PotionEffect).type)
                        player2.damage(2.0)
                    }
                }
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("purge.level_III.chance")) {
                    player2.world.strikeLightningEffect(player2.location)
                    val iterator3 = player2.activePotionEffects.iterator()
                    while (iterator3.hasNext()) {
                        player2.removePotionEffect((iterator3.next() as PotionEffect).type)
                        player2.damage(3.0)
                    }
                }
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " IV") && n < SettingsManager.enchant.getInt("purge.level_IV.chance")) {
                    player2.world.strikeLightningEffect(player2.location)
                    val iterator4 = player2.activePotionEffects.iterator()
                    while (iterator4.hasNext()) {
                        player2.removePotionEffect((iterator4.next() as PotionEffect).type)
                        player2.damage(4.0)
                    }
                }
            } catch (ex: Exception) {
            }

        }
    }
}
