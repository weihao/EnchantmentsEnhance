package org.pixeltime.enchantmentsenhance.gui.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;

public class MenuHandler implements Listener {

    @EventHandler
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
        GUIAbstract gui = GUIAbstract.playerMap.get(playerName);
        if (gui != null && gui.getInventory().equals(e.getInventory())) {
            e.setCancelled(true);
            if (!Menu.itemOnEnhancingSlot.containsKey(player.getName())) {
                if (Enhance.getValidationOfItem(e.getCurrentItem())) {
                    ((Menu) GUIAbstract.playerMap.get(playerName)).update(player, e.getCurrentItem());
                }
            }
        }
    }

    /**
     * Removes current item placed on the enhancing slot.
     *
     * @param e
     */
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        Menu.itemOnEnhancingSlot.remove(player.getName());
    }
}
