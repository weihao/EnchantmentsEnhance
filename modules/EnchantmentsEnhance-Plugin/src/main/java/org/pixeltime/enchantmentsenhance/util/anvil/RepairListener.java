package org.pixeltime.enchantmentsenhance.util.anvil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

public class RepairListener implements Listener {
    @EventHandler
    public void onAnvilGUIClick(final InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.ANVIL && event.getWhoClicked() instanceof Player) {
            if (!(event.getInventory() instanceof  AnvilInventory)) {
                return;
            }
            final Player player = (Player) event.getWhoClicked();
            final AnvilInventory inv = (AnvilInventory) event.getInventory();
            AnvilTask task = AnvilTask.getTask(inv);
            if (task == null) {
                task = new AnvilTask(inv, player);
            }
            if (event.getRawSlot() == 2) {
                final ItemStack translatedItem = ColorHandler.getTranslatedItem(player, inv, task);
                event.setCurrentItem(translatedItem);
            }
        }
    }
}
