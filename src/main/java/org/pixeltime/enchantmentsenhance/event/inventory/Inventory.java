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

package org.pixeltime.enchantmentsenhance.event.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    // int[0] = weapon stone, int[1] = armor stone, int[2] = conc weapon; int[3]
    // =conc armor.
    private static Map<String, int[]> backpack = new HashMap<String, int[]>();


    public static void loadInventory(Player player) {
        if (SettingsManager.data.contains(player.getName() + ".backpack")
                || backpack.containsKey(player.getName())) {
            try {
                String[] temp = SettingsManager.data.getString(player.getName()
                        + ".backpack").replace("[", "").replace("]", "").split(", ");
                int[] inventory = new int[temp.length];
                if (temp.length > 0) {
                    for (int i = 0; i < temp.length; i++) {
                        inventory[i] = Integer.parseInt(temp[i]);
                    }
                    backpack.put(player.getName(), inventory);
                } else {
                    backpack.put(player.getName(), new int[]{0, 0, 0, 0});
                }
            } catch (Exception e) {
                Bukkit.getLogger().severe("Error in loading " + player
                        .getName() + "'s" + " inventory.");
            }
        } else {
            backpack.put(player.getName(), new int[]{0, 0, 0, 0});
        }
    }


    public static void saveInventoryToDisk(Player player, boolean save) {
        String str = Arrays.toString(backpack.get(player.getName()));
        SettingsManager.data.set(player.getName() + ".backpack", str);

        if (save) {
            SettingsManager.saveData();
        }

    }


    public static void setLevel(Player player, int type, int level) {
        try {
            int[] temp = backpack.get(player.getName());
            temp[type] = level;
        } catch (Exception e) {
            Main.getMain().getLogger().info(
                    "Error when setting the player data.");
        }
    }


    public static void addLevel(Player player, int type, int levelsToAdd) {
        int newLevel = getLevel(type, player) + levelsToAdd;
        setLevel(player, type, newLevel);
    }


    public static int getLevel(int type, Player player) {
        if (backpack.containsKey(player.getName())) {
            return backpack.get(player.getName())[type];
        }
        return 0;
    }


    public static int[] getPlayer(Player player) {
        return backpack.get(player.getName());
    }
}
