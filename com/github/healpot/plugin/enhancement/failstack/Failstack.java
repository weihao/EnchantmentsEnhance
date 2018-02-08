package com.github.healpot.plugin.enhancement.failstack;

import java.util.HashMap;
import org.bukkit.entity.Player;
import com.github.healpot.plugin.enhancement.main.SettingsManager;

public class Failstack {
    private static HashMap<Player, Integer> failstack = new HashMap<Player, Integer>();


    public static void loadLevels(Player player) {
        if (SettingsManager.data.contains("Failstack." + player.getName())
            || failstack.containsKey(player)) {
            failstack.put(player, SettingsManager.data.getInt("Failstack."
                + player.getName()));
        }
    }


    public static void saveLevels(Player player, boolean save) {
        SettingsManager.data.set("Failstack." + player.getName(), getLevel(
            player));
        if (save)
            SettingsManager.saveData();
    }


    public static void resetLevel(Player player) {
        setLevel(player, 0);
    }


    public static void setLevel(Player player, int level) {
        failstack.put(player, level);
    }


    public static void addLevel(Player player, int levelsToAdd) {
        int newLevel = getLevel(player) + levelsToAdd;
        setLevel(player, newLevel);
    }


    public static int getLevel(Player player) {
        if (failstack.containsKey(player)) {
            return failstack.get(player);
        }
        return 0;
    }


    public static double getChance(Player p, int enchantLevel) {
        int failstack = getLevel(p);
        int maximumFailstack = SettingsManager.config.getInt("maximumFailstack."
            + Integer.toString(enchantLevel));
        double baseChance = SettingsManager.config.getDouble("baseChance."
            + Integer.toString(enchantLevel));
        double increasePerFailstack = SettingsManager.config.getDouble(
            "increasePerFailstack." + Integer.toString(enchantLevel));

        if (failstack > maximumFailstack) {
            failstack = maximumFailstack;
        }

        return (baseChance + failstack * increasePerFailstack) / 100;
    }
}
