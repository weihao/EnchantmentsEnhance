/*
 *     Copyright (C) 2017-Present HealPot
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

package org.pixeltime.enchantmentsenhance.listener

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

class ItemUseListener : Listener {

    @EventHandler
    fun onItemClick(event: PlayerInteractEvent) {
        val player = event.player
        if (event.material == Material.AIR) {
            return
        }
        if (event.action == Action.LEFT_CLICK_BLOCK || event.action == Action.LEFT_CLICK_AIR) {
            return
        }
        val item = event.item
        if (!ItemManager.getGive(item).isEmpty()) {
            val give = ItemManager.getGive(item).split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (give.size == 2) {
                val id = Integer.parseInt(give[0])
                val amount = Integer.parseInt(give[1])
                if (id < 0) {
                    Main.getApi().addAdvice(player.name, amount)
                    Util.sendMessage(SettingsManager.lang.getString("materialize.adviceSucess")
                        .replace("%level%", Integer.toString(amount)), player)
                } else {
                    Main.getApi().addItem(player.name, id, amount)
                    Util.sendMessage(SettingsManager.lang.getString("materialize.success")
                        .replace("%amount%", Integer.toString(amount))
                        .replace("%item%", SettingsManager.lang.getString("item.$id")), player)
                }

                // Consume the item.
                if (event.hand == EquipmentSlot.HAND) {
                    player.inventory.itemInMainHand.amount = player.inventory.itemInMainHand.amount - 1
                } else if (event.hand == EquipmentSlot.OFF_HAND) {
                    player.inventory.itemInOffHand.amount = player.inventory.itemInOffHand.amount - 1
                }
                event.isCancelled = true
            }
        }
    }
}
