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

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDeathListener implements Listener {
    private static final Main m = Main.getMain();


    public PlayerDeathListener() {
    }


    /**
     * Prevents enhanced item dropped from death.
     *
     * @param e
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        List<ItemStack> newInventory = new ArrayList<ItemStack>();
        File playerFile = new File(m.getDataFolder() + "/death/" + p.getName() + ".yml");
        FileConfiguration pFile = YamlConfiguration.loadConfiguration(playerFile);
        pFile.set("PlayerName", p.getName());
        if (!e.getDrops().isEmpty() || e.getDrops() != null) {
            for (int i = 0; i < e.getDrops().size(); i++) {
                ItemStack stack = e.getDrops().get(i);
                if (stack.hasItemMeta()) {
                    ItemMeta meta = stack.getItemMeta();
                    if (meta.hasLore()) {
                        List<String> lore = meta.getLore();
                        for (String s : lore) {
                            s = ChatColor.stripColor(s);
                            if (s.contains(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("lore.untradeableLore"))))
                                    || s.contains(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("lore.tradeableLore"))))) {
                                newInventory.add(e.getDrops().get(i));
                            }
                        }
                    }
                }
            }
            ItemStack[] newStack = newInventory.toArray(new ItemStack[newInventory.size()]);
            pFile.set("Items", newStack);
            try {
                pFile.save(playerFile);
            } catch (IOException e1) {

            }
            e.getDrops().removeAll(newInventory);
        }
    }


    /**
     * Returns enhanced item to the player when respawn.
     *
     * @param e
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        File playerFile = new File(m.getDataFolder() + "/death/" + p.getName() + ".yml");
        FileConfiguration pFile = YamlConfiguration.loadConfiguration(
                playerFile);
        if (playerFile.exists()) {
            ItemStack[] content = ((List<?>) pFile.get("Items"))
                    .toArray(new ItemStack[0]);
            p.getInventory().addItem(content);

            if (playerFile.delete()) {
                // Delete a file.
            }
        }
    }
}
