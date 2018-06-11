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

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.manager.DataManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.HashMap;

public class Failstack {
    private static HashMap<String, Integer> failstack =
            new HashMap<String, Integer>();


    public static void loadLevels(Player player) {
        if (SettingsManager.data.contains(player.getName()
                + ".failstack") || failstack.containsKey(player.getName())) {
            failstack.put(player.getName(), SettingsManager.data.getInt(
                    player.getName() + ".failstack"));
        }
    }


    public static void saveLevels(Player player, boolean save) {
        SettingsManager.data.set(player.getName() + ".failstack",
                getLevel(player));
        if (save)
            SettingsManager.saveData();
    }


    public static void resetLevel(Player player) {
        setLevel(player, 0);
    }


    public static void setLevel(Player player, int level) {
        failstack.put(player.getName(), level);
    }


    public static void addLevel(Player player, int levelsToAdd) {
        int newLevel = getLevel(player) + levelsToAdd;
        setLevel(player, newLevel);
    }


    public static int getLevel(Player player) {
        if (failstack.containsKey(player.getName())) {
            return failstack.get(player.getName());
        }
        return 0;
    }

    public static int getLevel(String playerName) {
        if (failstack.containsKey(playerName)) {
            return failstack.get(playerName);
        }
        return 0;
    }

    public static double getChance(String playerName, int enchantLevel) {
        int failstack = getLevel(playerName);
        int maximumFailstack =
                DataManager.maximumFailstackApplied[enchantLevel];
        double baseChance = DataManager.baseChance[enchantLevel];
        double increasePerFailstack = DataManager.chanceIncreasePerFailstack[enchantLevel];

        if (failstack > maximumFailstack) {
            failstack = maximumFailstack;
        }
        if (increasePerFailstack == -1 || maximumFailstack == -1) {
            return baseChance / 100;
        } else {
            return (baseChance + failstack * increasePerFailstack) / 100;
        }
    }
}
