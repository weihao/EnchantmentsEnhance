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

package org.pixeltime.enchantmentsenhance.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.event.blacksmith.SecretBook;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Failstack;
import org.pixeltime.enchantmentsenhance.event.inventory.Inventory;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class PlayerStreamHandler implements Listener {

    public static final Main m = Main.getMain();


    public PlayerStreamHandler() {
    }


    /**
     * Sends a message to greet joined player.
     *
     * @param e
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Util.sendMessage(SettingsManager.lang.getString("Config.welcome")
                .replaceAll("%player%", player.getName()), player);
        Failstack.loadLevels(player);
        SecretBook.loadStorage(player);
        Inventory.loadInventory(player);
    }


    /**
     * When a player leaves the server, listener saves a player's data from
     * hashmap to file, but will not write to disk.
     *
     * @param e
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        Failstack.saveLevels(player, false);
        SecretBook.saveStorageToDisk(player, false);
        Inventory.saveInventoryToDisk(player, false);
    }


    /**
     * When a player gets kicked off the server, listener saves a player's data
     * from hashmap to file, but will not write to disk.
     *
     * @param e
     */
    @EventHandler
    public void onKick(PlayerKickEvent e) {
        Player player = e.getPlayer();
        Failstack.saveLevels(player, false);
        SecretBook.saveStorageToDisk(player, false);
        Inventory.saveInventoryToDisk(player, false);
    }

    /**
     * Informs Plugin author.
     *
     * @param e
     */
    @EventHandler
    public void onJoin2(PlayerJoinEvent e) {
        if (e.getPlayer() != null) {
            Player player = e.getPlayer();
            if (player.getName().equalsIgnoreCase("Fearr")) {
                Util.sendMessage(
                        ("&cThis server is using your Enchantment Enhance plugin. It is using v"
                                + Bukkit.getServer().getPluginManager().getPlugin(
                                "EnchantmentsEnhance").getDescription().getVersion()
                                + "."), player);
            }
        }
    }
}
