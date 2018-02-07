package com.github.healpot.plugin.enhancement.failstack;

import java.util.HashMap;
import org.bukkit.entity.Player;
import com.github.healpot.plugin.enhancement.main.Main;
import com.github.healpot.plugin.enhancement.main.SettingsManager;

public class Failstack {
    private HashMap<Player, Integer> failstack = new HashMap<Player, Integer>();


    public void loadLevels(Main m, Player player) {
        if (SettingsManager.data.contains("Failstack." + player.getName())
            || failstack.containsKey(player)) {
            failstack.put(player, SettingsManager.data.getInt("Failstack."
                + player.getName()));
        }
    }


    public void saveLevels(Main m, Player player, boolean save) {
        SettingsManager.data.set("Failstack." + player.getName(), getLevel(m,
            player));
        if (save)
            SettingsManager.saveData();
    }


    public void resetLevel(Main m, Player player) {
        setLevel(m, player, 0);
    }


    public void setLevel(Main m, Player player, int level) {
        failstack.put(player, level);
    }


    public void addLevel(Main m, Player player, int levelsToAdd) {
        int newLevel = getLevel(m, player) + levelsToAdd;
        setLevel(m, player, newLevel);
    }


    public int getLevel(Main m, Player player) {
        if (failstack.containsKey(player)) {
            return failstack.get(player);
        }
        return 0;
    }


    public double getChance(Main m, Player p, int enchantLevel) {
        int failstack = getLevel(m, p);
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
