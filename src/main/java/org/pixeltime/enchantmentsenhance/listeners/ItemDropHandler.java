package org.pixeltime.enchantmentsenhance.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ItemDropHandler implements Listener {
    /**
     * Prevents enhanced item from dropping.
     *
     * @param e
     */
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        Item droppedItem = e.getItemDrop();
        ItemStack DroppedItemStack = droppedItem.getItemStack();
        Player p = e.getPlayer();
        List<String> loreList = new ArrayList<String>();
        if ((DroppedItemStack.hasItemMeta()) && (DroppedItemStack.getItemMeta()
                .getLore() != null)) {
            int loreSize = DroppedItemStack.getItemMeta().getLore().size();
            for (int i = 0; i < loreSize; i++) {
                loreList.add(DroppedItemStack.getItemMeta().getLore()
                        .get(i));
            }
            // Checks if the item is a bounded item
            if (loreList.contains(ChatColor.translateAlternateColorCodes('&',
                    SettingsManager.lang.getString("Lore.untradeableLore")))) {
                e.setCancelled(true);
                Util.sendMessage(SettingsManager.lang.getString(
                        "Messages.noDrop"), p);
                return;
            }
        }
    }
}
