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
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.pixeltime.enchantmentsenhance.manager.DM
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Thief : Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "thief"))
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            val player2 = entityDamageByEntityEvent.entity as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player2.world).getApplicableRegions(player2.location).queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY) {
                return
            }
            try {
                val level = IM.getHighestLevel(player, translateAlternateColorCodes)
                if (level > 0 && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("thief.$level.chance")) {
                    val n2 = SettingsManager.enchant.getInt("thief.$level.money-percent") / 100.0 * (DM.economy!!.getBalance(player2 as OfflinePlayer))
                    DM.economy!!.withdrawPlayer(player2 as OfflinePlayer, n2)
                    DM.economy!!.depositPlayer(player as OfflinePlayer, n2)
                }
            } catch (ex: Exception) {
            }
        }
    }
}
