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

package org.pixeltime.enchantmentsenhance.util.anvil

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.AnvilInventory

class RepairListener : Listener {

    @EventHandler
    fun onAnvilGUIClick(event: InventoryClickEvent) {
        if (event.inventory.type == InventoryType.ANVIL && event.whoClicked is Player) {
            val player = event.whoClicked as Player
            val inv = event.inventory as AnvilInventory
            var task = AnvilTask.getTask(inv)
            if (task == null) {
                task = AnvilTask(inv, player)
            }
            if (event.rawSlot == 2) {
                val translatedItem = ColorHandler.getTranslatedItem(player, inv, task!!)
                event.currentItem = translatedItem
            }
        }
    }
}
