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
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Farmer : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "farmer"))

    @EventHandler
    fun onPlace(blockPlaceEvent: BlockPlaceEvent) {
        val player = blockPlaceEvent.player
        try {
            val armorContents = IM.getItemList(player)
            for (itemStack in armorContents) {

                val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                if (level > 0) {
                    if (player.itemInHand.type == Material.CARROT_ITEM) {
                        blockPlaceEvent.blockPlaced.setTypeIdAndData(Material.CARROT.id, 7.toByte(), true)
                    }
                    if (player.itemInHand.type == Material.MELON_SEEDS) {
                        blockPlaceEvent.blockPlaced.type = Material.MELON
                    }
                    if (player.itemInHand.type == Material.POTATO_ITEM) {
                        blockPlaceEvent.blockPlaced.setTypeIdAndData(Material.POTATO.id, 7.toByte(), true)
                    }
                    if (player.itemInHand.type == Material.SEEDS) {
                        blockPlaceEvent.blockPlaced.setTypeIdAndData(Material.CROPS.id, 7.toByte(), true)
                    }
                    if (player.itemInHand.type == Material.PUMPKIN_SEEDS) {
                        blockPlaceEvent.blockPlaced.type = Material.PUMPKIN
                    }
                }

            }
        } catch (ex: Exception) {
        }

    }
}
