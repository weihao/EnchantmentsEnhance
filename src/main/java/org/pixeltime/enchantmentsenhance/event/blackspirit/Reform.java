/*
 *     Copyright (C) 2017-Present HealPotion
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

package org.pixeltime.enchantmentsenhance.event.blackspirit;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.GUI;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.HashMap;
import java.util.Map;

public class Reform extends GUI implements Listener {
    private static Map<String, ItemStack> itemOnReformingSlot =
            new HashMap<String, ItemStack>();


    public Reform() {
        super(27, SettingsManager.lang.getString("Reform.gui.title"));
        setItem(Util.getSlot(5, 3), Util.createButton(DyeColor.BLACK,
                SettingsManager.lang.getString("Reform.reform")), player -> {
            if (itemOnReformingSlot.containsKey(player.getName())) {

            }
        });
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getSlot() < 0) {
            return;
        }
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        if (e.getCurrentItem().getType() == (Material.AIR)) {
            return;
        }
        if (this.getInventory().getName().equals(e.getInventory().getName())) {
            e.setCancelled(true);
        }
    }
}
