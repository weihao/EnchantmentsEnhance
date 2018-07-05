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

import org.bukkit.Material
import org.bukkit.entity.Sheep
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Shearer : EnchantmentListener() {
    override fun lang(): Array<String> {
        return arrayOf("剪裁")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onInteract(playerInteractEvent: PlayerInteractEvent) {
        val player = playerInteractEvent.player

        if (playerInteractEvent.action == Action.LEFT_CLICK_AIR) {
            val level = IM.getHighestLevel(player, this.name())
            if (level > 0) {
                val int1 = SettingsManager.enchant.getInt("shearer.$level.radius")
                for (entity in player.getNearbyEntities(int1.toDouble(), int1.toDouble(), int1.toDouble())) {
                    if (entity is Sheep) {
                        if (!entity.isSheared) {
                            entity.isSheared = true
                            entity.world.dropItem(entity.location, ItemStack(Material.WOOL, 1, entity.color.woolData.toShort()))
                        }
                    }
                }
            }
        }
    }
}
