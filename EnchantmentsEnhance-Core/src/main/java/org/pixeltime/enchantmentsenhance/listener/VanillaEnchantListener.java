/*
 *     Copyright (C) 2017-Present HealPot
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


import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.XMaterial;


public class VanillaEnchantListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onOpenEnchantmentTable(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == XMaterial.ENCHANTING_TABLE.parseMaterial()) {
            if (SettingsManager.config.getString("openMethod").equalsIgnoreCase("LEFT_CLICK")) {
                if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    event.setCancelled(true);
                    new MainMenu(event.getPlayer()).open();
                }
            } else if (SettingsManager.config.getString("openMethod").equalsIgnoreCase("RIGHT_CLICK")) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    event.setCancelled(true);
                    new MainMenu(event.getPlayer()).open();
                }
            } else if (SettingsManager.config.getString("openMethod").equalsIgnoreCase("SHIFT_AND_LEFT_CLICK")) {
                if (event.getAction() == Action.LEFT_CLICK_BLOCK && event.getPlayer().isSneaking()) {
                    event.setCancelled(true);
                    new MainMenu(event.getPlayer()).open();
                }
            } else if (SettingsManager.config.getString("openMethod").equalsIgnoreCase("SHIFT_AND_RIGHT_CLICK")) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getPlayer().isSneaking()) {
                    event.setCancelled(true);
                    new MainMenu(event.getPlayer()).open();
                }
            }
        }
    }
}