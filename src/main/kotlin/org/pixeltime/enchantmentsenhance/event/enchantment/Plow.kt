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
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Plow : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onInteract(playerInteractEvent: PlayerInteractEvent) {
        if (playerInteractEvent.isCancelled) {
            return
        }
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "plow"))
        val player = playerInteractEvent.player
        val armorContents = IM.getItemList(player)
        for (itemStack in armorContents) {

            val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
            if (level > 0) {
                val clickedBlock = playerInteractEvent.clickedBlock
                if (clickedBlock.type != Material.DIRT && clickedBlock.type != Material.GRASS) {
                    return
                }
                val n = 0.5
                val location = clickedBlock.location
                var n2 = location.blockX - n
                while (n2 <= location.blockX + n) {
                    var n3 = location.blockZ - n
                    while (n3 <= location.blockZ + n) {
                        val block = location.world.getBlockAt(Location(clickedBlock.world, n2, clickedBlock.y.toDouble(), n3))
                        if (block.type != Material.GRASS && block.type != Material.DIRT) {
                            return
                        }
                        if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block)) {
                            return
                        }
                        block.type = Material.SOIL
                        ++n3
                    }
                    ++n2
                }

            }
        }
    }
}