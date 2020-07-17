package org.pixeltime.enchantmentsenhance.listener;

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
            if (DroppedItemStack.getItemMeta().getLore().contains(Util.UNIQUEID + Util.toColor(
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
                    if (itemMoved.getItemMeta().getLore().contains(Util.UNIQUEID + Util.toColor(SettingsManager.lang.getString("lore.untradeableLore")))) {
                        e.setCancelled(true);
                        Util.sendMessage(SettingsManager.lang.getString("messages.noStorage"), e.getWhoClicked());
                    }
                }
            }
            if (e.getCurrentItem() != null) {
                if ((e.getCurrentItem().hasItemMeta()) && (e.getCurrentItem().getItemMeta().hasLore())) {
                    if (e.getCurrentItem().getItemMeta().getLore().contains(Util.UNIQUEID + Util.toColor(SettingsManager.lang.getString("lore.untradeableLore")))) {
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
                    if (currentItem.getItemMeta().getLore().contains(Util.UNIQUEID + Util.toColor(SettingsManager.lang.getString("lore.untradeableLore")))) {
                        inventoryClickEvent.setCancelled(true);
                        Util.sendMessage(SettingsManager.lang.getString("messages.noDrop"), inventoryClickEvent.getWhoClicked());
                    }
                }
                if (cursor != null && cursor.hasItemMeta() && (cursor.getItemMeta().hasLore())) {
                    if (cursor.getItemMeta().getLore().contains(Util.UNIQUEID + Util.toColor(SettingsManager.lang.getString("lore.untradeableLore")))) {
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