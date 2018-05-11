package org.pixeltime.enchantmentsenhance.events.blackspirit;

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


    public static double getChance(Player p, int enchantLevel) {
        int failstack = getLevel(p);
        int maximumFailstack =
                DataManager.maximumFailstackApplied[enchantLevel];
        double baseChance = DataManager.baseChance[enchantLevel];
        double increasePerFailstack =
                DataManager.chanceIncreasePerFailstack[enchantLevel];

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
