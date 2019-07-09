/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

package org.pixeltime.enchantmentsenhance.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.HashSet;
import java.util.Set;

public class EnhancedItemListener implements Listener {
    private static Set<String> onClicking = new HashSet<>();

    /**
     * Prevents enhanced item from dropping.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onItemDrop(PlayerDropItemEvent e) {
        Item droppedItem = e.getItemDrop();
        ItemStack DroppedItemStack = droppedItem.getItemStack();
        Player p = e.getPlayer();
        // Checks if the item is a bounded item
        if ((DroppedItemStack.hasItemMeta()) && (DroppedItemStack.getItemMeta()
                .getLore() != null)) {
            if (DroppedItemStack.getItemMeta().getLore().contains(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                    SettingsManager.lang.getString("lore.untradeableLore")))) {
                e.setCancelled(true);
                Util.sendMessage(SettingsManager.lang.getString(
                        "messages.noDrop"), p);
            }
        }
    }

    /**
     * Prevents enhanced item from storing.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onInventoryClick(InventoryClickEvent e) {
        if ((e.getInventory().getType() != InventoryType.CRAFTING) && (e.getInventory().getType() != InventoryType.PLAYER)) {
            if ((e.getClick().equals(ClickType.NUMBER_KEY)) && (e.getWhoClicked().getInventory().getItem(e.getHotbarButton()) != null)) {
                ItemStack itemMoved = e.getWhoClicked().getInventory().getItem(e.getHotbarButton());
                if ((itemMoved.hasItemMeta()) && (itemMoved.getItemMeta().hasLore())) {
                    if (itemMoved.getItemMeta().getLore().contains(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("lore.untradeableLore")))) {
                        e.setCancelled(true);
                        Util.sendMessage(SettingsManager.lang.getString("messages.noStorage"), e.getWhoClicked());
                    }
                }
            }
            if (e.getCurrentItem() != null) {
                if ((e.getCurrentItem().hasItemMeta()) && (e.getCurrentItem().getItemMeta().hasLore())) {
                    if (e.getCurrentItem().getItemMeta().getLore().contains(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("lore.untradeableLore")))) {
                        e.setCancelled(true);
                        Util.sendMessage(SettingsManager.lang.getString("messages.noStorage"), e.getWhoClicked());
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
    public void onItemGlitch(InventoryClickEvent inventoryClickEvent) {
        ItemStack currentItem = inventoryClickEvent.getCurrentItem();
        ItemStack cursor = inventoryClickEvent.getCursor();
        try {
            if (inventoryClickEvent.getClickedInventory() != null && inventoryClickEvent.getClickedInventory().firstEmpty() == -1) {
                if (currentItem != null && currentItem.hasItemMeta() && (currentItem.getItemMeta().hasLore())) {
                    if (currentItem.getItemMeta().getLore().contains(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("lore.untradeableLore")))) {
                        inventoryClickEvent.setCancelled(true);
                        Util.sendMessage(SettingsManager.lang.getString("messages.noDrop"), inventoryClickEvent.getWhoClicked());
                    }
                }
                if (cursor != null && cursor.hasItemMeta() && (cursor.getItemMeta().hasLore())) {
                    if (cursor.getItemMeta().getLore().contains(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("lore.untradeableLore")))) {
                        inventoryClickEvent.setCancelled(true);
                        Util.sendMessage(SettingsManager.lang.getString("messages.noDrop"), inventoryClickEvent.getWhoClicked());
                    }
                }
            }
        } catch (NoSuchMethodError ex) {
            // craftbukkit
        }
    }
}