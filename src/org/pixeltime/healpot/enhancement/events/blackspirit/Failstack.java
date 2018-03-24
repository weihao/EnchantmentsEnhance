package org.pixeltime.healpot.enhancement.events.blackspirit;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.manager.DataManager;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;

public class Failstack {
    private static HashMap<String, Integer> failstack =
        new HashMap<String, Integer>();


    public static void loadLevels(Player player) {
        if (SettingsManager.data.contains(player.getDisplayName()
            + ".failstack") || failstack.containsKey(player.getDisplayName())) {
            failstack.put(player.getDisplayName(), SettingsManager.data.getInt(
                player.getName() + ".failstack"));
        }
    }


    public static void saveLevels(Player player, boolean save) {
        SettingsManager.data.set(player.getDisplayName() + ".failstack",
            getLevel(player));
        if (save)
            SettingsManager.saveData();
    }


    public static void resetLevel(Player player) {
        setLevel(player, 0);
    }


    public static void setLevel(Player player, int level) {
        failstack.put(player.getDisplayName(), level);
    }


    public static void addLevel(Player player, int levelsToAdd) {
        int newLevel = getLevel(player) + levelsToAdd;
        setLevel(player, newLevel);
    }


    public static int getLevel(Player player) {
        if (failstack.containsKey(player.getDisplayName())) {
            return failstack.get(player.getDisplayName());
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
        }
        else {
            return (baseChance + failstack * increasePerFailstack) / 100;
        }
    }
}
