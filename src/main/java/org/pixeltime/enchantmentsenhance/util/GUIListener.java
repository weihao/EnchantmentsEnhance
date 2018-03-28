package org.pixeltime.enchantmentsenhance.util;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GUIListener implements Listener {
    private GUI gui;


    public GUIListener(GUI gui) {
        this.gui = gui;
    }


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player)e.getWhoClicked();
        String playerUUID = player.getDisplayName();
        UUID inventoryUUID = GUI.openInventories.get(playerUUID);
        if (inventoryUUID != null) {
            e.setCancelled(true);
            GUI gui = GUI.getInventoriesByUUID().get(inventoryUUID);
            GUI.GUIAction action = gui.getActions().get(e.getSlot());

            if (action != null) {
                action.click(player);
            }
        }
    }


    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player)e.getPlayer();
        String playerUUID = player.getDisplayName();

        GUI.openInventories.remove(playerUUID);
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        String playerUUID = player.getDisplayName();

        GUI.openInventories.remove(playerUUID);
    }


    public void delete() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            UUID u = GUI.openInventories.get(p.getDisplayName());
            if (u.equals(gui.getUuid())) {
                p.closeInventory();
            }
        }
        GUI.inventoriesByUUID.remove(gui.getUuid());
    }
}
