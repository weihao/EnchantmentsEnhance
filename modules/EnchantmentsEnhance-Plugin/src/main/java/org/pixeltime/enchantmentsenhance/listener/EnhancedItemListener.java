package org.pixeltime.enchantmentsenhance.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
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
        ItemStack droppedItemStack = e.getItemDrop().getItemStack();
        Player p = e.getPlayer();

        // Checks if the item is a bounded item
        if (ItemManager.getSoulbound(droppedItemStack) && !ItemManager.getTradeable(droppedItemStack)) {
            e.setCancelled(true);
            Util.sendMessage(SettingsManager.lang.getString(
                    "messages.noDrop"), p);
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
            if (e.getCurrentItem() != null) {
                if ((e.getClick().equals(ClickType.NUMBER_KEY)) && (e.getWhoClicked().getInventory().getItem(e.getHotbarButton()) != null)) {
                    ItemStack itemMoved = e.getWhoClicked().getInventory().getItem(e.getHotbarButton());
                    if (ItemManager.getSoulbound(itemMoved) && !ItemManager.getTradeable(itemMoved)) {
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
                if (ItemManager.getSoulbound(currentItem) && !ItemManager.getTradeable(currentItem)) {
                    inventoryClickEvent.setCancelled(true);
                    Util.sendMessage(SettingsManager.lang.getString("messages.noDrop"), inventoryClickEvent.getWhoClicked());
                }
                if (ItemManager.getSoulbound(cursor) && !ItemManager.getTradeable(cursor)) {
                    inventoryClickEvent.setCancelled(true);
                    Util.sendMessage(SettingsManager.lang.getString("messages.noDrop"), inventoryClickEvent.getWhoClicked());
                }
            }
        } catch (NoSuchMethodError ex) {
            // craftbukkit
        }
    }
}