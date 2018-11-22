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

package org.pixeltime.enchantmentsenhance.gui.menu.handlers

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.pixeltime.enchantmentsenhance.event.Enhance
import org.pixeltime.enchantmentsenhance.gui.GUIManager
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu

class MenuHandler : Listener {

    @EventHandler(ignoreCancelled = false, priority = EventPriority.HIGHEST)
    fun onClick(e: InventoryClickEvent) {
        // Handles invalid clicks.
        if (e.slot < 0) {
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
        val player = e.whoClicked as Player
        val playerName = player.name
        val gui = GUIManager.getMap()[playerName]
        if (gui != null && gui.inventory == e.inventory) {
            e.isCancelled = true
            if (!MainMenu.itemOnEnhancingSlot.containsKey(playerName)) {
                if (MainMenu.enhancingMode[playerName] == MainMenu.gear) {
                    if (Enhance.getValidationOfItem(e.currentItem) && e.rawSlot >= 54) {
                        MainMenu.itemOnEnhancingSlot[playerName] = e.currentItem
                        gui.update()
                    }
                }
            } else if (MainMenu.enhancingMode[playerName] == MainMenu.tool) {
                if (Enhance.getValidationOfToolItem(e.currentItem) && e.rawSlot >= 54) {
                    MainMenu.itemOnEnhancingSlot[playerName] = e.currentItem
                    gui.update()
                }
            }
        }
    }

    /**
     * Removes current item placed on the enhancing slot.
     *
     * @param e
     */
    @EventHandler(priority = EventPriority.MONITOR)
    fun onInventoryClose(e: InventoryCloseEvent) {
        val player = e.player
        if (MainMenu.inProgress.containsKey(player.name))
            MainMenu.inProgress[player.name]?.cancel()
        MainMenu.clearPlayer(player.name)
    }
}
