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

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerDropItemEvent
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util
import java.util.*

class EnhancedItemListener : Listener {

    companion object {
        private val onClicking = HashSet<String>()
    }

    /**
     * Prevents enhanced item from dropping.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    fun onItemDrop(e: PlayerDropItemEvent) {
        val droppedItem = e.itemDrop
        val droppedItemStack = droppedItem.itemStack
        val p = e.player
        // Checks if the item is a bounded item
        if (droppedItemStack.hasItemMeta() && droppedItemStack.itemMeta
                    .lore != null) {
            if (droppedItemStack.itemMeta.lore.contains(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                            SettingsManager.lang.getString("lore.untradeableLore")))) {
                e.isCancelled = true
                Util.sendMessage(SettingsManager.lang.getString(
                        "messages.noDrop"), p)
            }
        }
    }

    /**
     * Prevents enhanced item from storing.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.inventory.type != InventoryType.CRAFTING && e.inventory.type != InventoryType.PLAYER) {
            if (e.click == ClickType.NUMBER_KEY && e.whoClicked.inventory.getItem(e.hotbarButton) != null) {
                val itemMoved = e.whoClicked.inventory.getItem(e.hotbarButton)
                if (itemMoved.hasItemMeta() && itemMoved.itemMeta.hasLore()) {
                    if (itemMoved.itemMeta.lore.contains(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("lore.untradeableLore")))) {
                        e.isCancelled = true
                        Util.sendMessage(SettingsManager.lang.getString("messages.noStorage"), e.whoClicked)
                    }
                }
            }
            if (e.currentItem != null) {
                if (e.currentItem.hasItemMeta() && e.currentItem.itemMeta.hasLore()) {
                    if (e.currentItem.itemMeta.lore.contains(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("lore.untradeableLore")))) {
                        e.isCancelled = true
                        Util.sendMessage(SettingsManager.lang.getString("messages.noStorage"), e.whoClicked)
                    }
                }
            }
        }
    }

    /**
     * Prevents enhanced item from glitching.
     *
     * @param inventoryClickEvent
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    fun onItemGlitch(inventoryClickEvent: InventoryClickEvent) {
        val currentItem = inventoryClickEvent.currentItem
        val cursor = inventoryClickEvent.cursor
        if (inventoryClickEvent.clickedInventory != null && inventoryClickEvent.clickedInventory.firstEmpty() == -1) {
            if (currentItem != null && currentItem.hasItemMeta() && currentItem.itemMeta.hasLore()) {
                if (currentItem.itemMeta.lore.contains(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("lore.untradeableLore")))) {
                    inventoryClickEvent.isCancelled = true
                    Util.sendMessage(SettingsManager.lang.getString("messages.noDrop"), inventoryClickEvent.whoClicked)
                }
            }
            if (cursor != null && cursor.hasItemMeta() && cursor.itemMeta.hasLore()) {
                if (cursor.itemMeta.lore.contains(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("lore.untradeableLore")))) {
                    inventoryClickEvent.isCancelled = true
                    Util.sendMessage(SettingsManager.lang.getString("messages.noDrop"), inventoryClickEvent.whoClicked)
                }
            }
        }
    }
}
