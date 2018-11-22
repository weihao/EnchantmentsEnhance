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

package org.pixeltime.enchantmentsenhance.gui

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.Inventory

class GUIListener : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onClick(e: InventoryClickEvent) {
        // Handles invalid clicks.
        if (e.getSlot() < 0) {
            return
        }
        // Handles invalid entity.
        if (e.whoClicked !is Player) {
            return
        }
        // Handles empty slot.
        if (e.currentItem.type == Material.AIR) {
            return
        }
        // Handles non-gui inventory.
        if (e.rawSlot > 53) {
            return;
        }
        val player = e.whoClicked
        val playerName = player.name
        val gui = GUIManager.getMap().get(playerName)
        if (gui != null && gui.inventory == e.inventory) {
            e.isCancelled = true
            val action = gui.actions[e.slot]
            if (action != null) {
                action.click(e.click)
                gui.update()
            }
        } else {
            if (isCreatedGUI(e.inventory)) {
                e.isCancelled = true
                player.closeInventory()
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onClose(e: InventoryCloseEvent) {
        val player = e.player
        val playerName = player.name
        GUIManager.getMap().remove(playerName)
    }


    @EventHandler(priority = EventPriority.MONITOR)
    fun onQuit(e: PlayerQuitEvent) {
        val player = e.player
        val playerName = player.name
        GUIManager.getMap().remove(playerName)
    }

    fun isCreatedGUI(inventory: Inventory): Boolean {
        return GUIManager.getSet().find { it.inventory == inventory } != null
    }
}
