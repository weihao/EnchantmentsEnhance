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

package org.pixeltime.enchantmentsenhance.gui.menu.handlers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.pixeltime.enchantmentsenhance.event.Enhance;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.gui.GUIManager;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;

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
            if (!MainMenu.itemOnEnhancingSlot.containsKey(playerName) && MainMenu.enhancingMode.containsKey(playerName)) {
                if (MainMenu.enhancingMode.get(playerName).equals(MainMenu.gear)) {
                    if (Enhance.getValidationOfItem(e.getCurrentItem()) && e.getRawSlot() >= 54) {
                        MainMenu.itemOnEnhancingSlot.put(playerName, e.getCurrentItem());
                        gui.update();
                    }
                } else if (MainMenu.enhancingMode.get(playerName).equals(MainMenu.tool)) {
                    if (Enhance.getValidationOfToolItem(e.getCurrentItem()) && e.getRawSlot() >= 54) {
                        MainMenu.itemOnEnhancingSlot.put(playerName, e.getCurrentItem());
                        gui.update();
                    }
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
        if (MainMenu.inProgress.containsKey(player.getName())) {
            MainMenu.inProgress.get(player.getName()).cancel();
        }
        MainMenu.clearPlayer(player.getName());
    }
}
