/*
 *     Copyright (C) 2017-Present 25
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

package org.pixeltime.enchantmentsenhance.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

public class GUIListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
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
        // Handles non-gui inventory.
        if (e.getRawSlot() > 53) {
            return;
        }

        Player player = (Player) e.getWhoClicked();
        String playerName = player.getName();
        GUIAbstract gui = GUIManager.getMap().get(playerName);
        if (gui != null && gui.getInventory().equals(e.getInventory())) {
            e.setCancelled(true);
            GUIAbstract.GUIAction action = gui.getActions().get(e.getSlot());
            if (action != null) {
                action.click(e.getClick());
                gui.update();
            }
        } else {
            if (isCreatedGUI(e.getInventory())) {
                e.setCancelled(true);
                player.closeInventory();
            }
        }
    }


    /**
     * Prevents item glitched into menu.
     *
     * @param e
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onInventoryClick(InventoryClickEvent e) {
        if ((e.getInventory().getType() != InventoryType.CRAFTING) && (e.getInventory().getType() != InventoryType.PLAYER)) {
            if ((e.getClick().equals(ClickType.NUMBER_KEY)) && (e.getWhoClicked().getInventory().getItem(e.getHotbarButton()) != null)) {
                Player player = (Player) e.getWhoClicked();
                String playerName = player.getName();
                GUIAbstract gui = GUIManager.getMap().get(playerName);
                if (gui != null && gui.getInventory().equals(e.getInventory())) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        String playerName = player.getName();
        GUIManager.getMap().remove(playerName);
    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();
        GUIManager.getMap().remove(playerName);
    }

    public boolean isCreatedGUI(Inventory inventory) {
        for (GUIAbstract inv : GUIManager.getSet()) {
            if (inventory.equals(inv.getInventory())) {
                return true;
            }
        }
        return false;
    }
}
