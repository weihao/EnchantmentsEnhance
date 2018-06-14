package org.pixeltime.enchantmentsenhance.gui.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.gui.GUIManager;

public class MenuHandler implements Listener {

    @EventHandler(ignoreCancelled = false, priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent e) {
        // Handles invalid clicks.
        if (e.getSlot() < 0) {
            return;
        }
        // Handles invalid entity.
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        // Handles empty slot.
        if (e.getCurrentItem().getType() == (Material.AIR)) {
            return;
        }
        Player player = (Player) e.getWhoClicked();
        String playerName = player.getName();
        GUIAbstract gui = GUIManager.getMap().get(playerName);
        if (gui != null && gui.getInventory().equals(e.getInventory())) {
            e.setCancelled(true);
            if (!MainMenu.itemOnEnhancingSlot.containsKey(player.getName())) {
                if (Enhance.getValidationOfItem(e.getCurrentItem()) && e.getRawSlot() >= 54) {
                    MainMenu.itemOnEnhancingSlot.put(playerName, e.getCurrentItem());
                    gui.update();
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
    public void onInventoryClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        MainMenu.itemOnEnhancingSlot.remove(player.getName());
    }
}
