/*
 *     Copyright (C) 2017-Present 25
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

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Immolation : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("When you sneak, you have chance to set nearby players on fire", "当你潜行攻击人时会造成他着火")
    }

    override fun lang(): Array<String> {
        return arrayOf("自焚")
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onSneak(playerToggleSneakEvent: PlayerToggleSneakEvent) {
        val player = playerToggleSneakEvent.player
        if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.world).getApplicableRegions(player.location).queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY) {
            return
        }
        try {
            val level = getLevel(player)
            if (level > 0 && (roll(level))) {
                for (entity in player.getNearbyEntities(SettingsManager.enchant.getDouble("immolation.$level.radius"), SettingsManager.enchant.getDouble("immolation.$level.radius"), SettingsManager.enchant.getDouble("immolation.$level.radius"))) {
                    if (entity is Player) {
                        entity.setFireTicks(SettingsManager.enchant.getInt("immolation.$level.duration") * 20)
                    }
                }
            }
        } catch (ex: Exception) {
        }

    }
}
