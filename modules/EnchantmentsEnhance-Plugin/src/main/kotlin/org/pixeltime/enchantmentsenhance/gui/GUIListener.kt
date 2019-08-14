package org.pixeltime.enchantmentsenhance.gui

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.Inventory

class GUIListener : Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
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
        if (e.currentItem != null && e.currentItem!!.type == Material.AIR) {
            return
        }
        // Handles non-gui inventory.
        if (e.rawSlot > 53) {
            return
        }

        val player = e.whoClicked as Player
        val playerName = player.name
        val gui = GUIManager.map[playerName]
        if (gui != null && gui.inventory == e.inventory) {
            e.isCancelled = true
            val action = gui.getActions()[e.slot]
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


    /**
     * Prevents item glitched into menu.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.inventory.type != InventoryType.CRAFTING && e.inventory.type != InventoryType.PLAYER) {
            if (e.click == ClickType.NUMBER_KEY && e.whoClicked.inventory.getItem(e.hotbarButton) != null) {
                val player = e.whoClicked as Player
                val playerName = player.name
                val gui = GUIManager.map[playerName]
                if (gui != null && gui.inventory == e.inventory) {
                    e.isCancelled = true
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onClose(e: InventoryCloseEvent) {
        val player = e.player as Player
        val playerName = player.name
        GUIManager.map.remove(playerName)
    }


    @EventHandler(priority = EventPriority.MONITOR)
    fun onQuit(e: PlayerQuitEvent) {
        val player = e.player
        val playerName = player.name
        GUIManager.map.remove(playerName)
    }

    fun isCreatedGUI(inventory: Inventory): Boolean {
        for (inv in GUIManager.set) {
            if (inventory == inv.inventory) {
                return true
            }
        }
        return false
    }
}
