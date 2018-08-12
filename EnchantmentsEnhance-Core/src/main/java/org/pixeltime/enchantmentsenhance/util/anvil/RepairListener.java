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
