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

package org.pixeltime.enchantmentsenhance.event.enchantment.hoe

import com.lgou2w.ldk.bukkit.compatibility.XMaterial
import com.sk89q.worldguard.bukkit.WGBukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerInteractEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Plow : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Hoes a bigger area", "锄头范围变大")
    }

    override fun lang(): Array<String> {
        return arrayOf("耕种")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onInteract(playerInteractEvent: PlayerInteractEvent) {
        if (playerInteractEvent.isCancelled) {
            return
        }

        val player = playerInteractEvent.player
        val level = getLevel(player)
        if (level > 0) {
            val clickedBlock = playerInteractEvent.clickedBlock
            if (clickedBlock.type != Material.DIRT && clickedBlock.type != Material.GRASS) {
                return
            }
            val radius = 1.0
            val location = clickedBlock.location
            var x = location.blockX - radius
            while (x <= location.blockX + radius) {
                var z = location.blockZ - radius
                while (z <= location.blockZ + radius) {
                    val block = location.world.getBlockAt(Location(clickedBlock.world, x, clickedBlock.y.toDouble(), z))
                    if (block.type != Material.GRASS && block.type != Material.DIRT) {
                        return
                    }
                    if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block)) {
                        return
                    }
                    block.type = XMaterial.FARMLAND.toBukkit()
                    z++
                }
                x++
            }
        }
    }
}