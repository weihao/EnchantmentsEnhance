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

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ItemUseListener implements Listener {
    @EventHandler
    public void onItemClick(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (event.getMaterial() == Material.AIR) {
            return;
        }
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.LEFT_CLICK_AIR)) {
            return;
        }
        final ItemStack item = event.getItem();
        if (!ItemManager.getGive(item).isEmpty()) {
            String[] give = ItemManager.getGive(item).split(":");
            if (give.length == 2) {
                int id = Integer.parseInt(give[0]);
                int amount = Integer.parseInt(give[1]);
                if (id < 0) {
                    Main.getApi().addAdvice(player.getName(), amount);
                    Util.sendMessage(SettingsManager.lang.getString("materialize.adviceSucess")
                            .replace("%level%", Integer.toString(amount)), player);
                } else {
                    Main.getApi().addItem(player.getName(), id, amount);
                    Util.sendMessage(SettingsManager.lang.getString("materialize.success")
                            .replace("%amount%", Integer.toString(amount))
                            .replace("%item%", SettingsManager.lang.getString("item." + id)), player);
                }

                // Consume the item.
                if ((item.getAmount() <= 1)) {
                    player.getInventory().removeItem(item);
                } else {
                    item.setAmount(item.getAmount() - 1);
                }
                event.setCancelled(true);
            }
        }
    }
}
