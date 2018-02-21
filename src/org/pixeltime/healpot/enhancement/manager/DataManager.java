package org.pixeltime.healpot.enhancement.manager;

import java.util.HashMap;
import java.util.Set;
import org.bukkit.Bukkit;
import org.pixeltime.healpot.enhancement.Main;
import org.pixeltime.healpot.enhancement.util.ArrayBag;

public class DataManager {
    public static int levels;
    public static double[] baseChance;
    public static double[] chanceIncreasePerFailstack;
    public static double[] maximumFailstackApplied;
    public static double[] failstackGainedPerFail;
    public static double[] costToForceEnchant;
    public static boolean[] fireworks;
    public static double[] fireworkRounds;
    public static String[] name;


    public DataManager() {
        Set<String> temp = SettingsManager.config.getConfigurationSection(
            "enhance").getKeys(false);
        levels = temp.size();
        DataManager.baseChance = new double[levels];
        DataManager.chanceIncreasePerFailstack = new double[levels];
        DataManager.maximumFailstackApplied = new double[levels];
        DataManager.failstackGainedPerFail = new double[levels];
        DataManager.costToForceEnchant = new double[levels];
        DataManager.fireworks = new boolean[levels];
        DataManager.fireworkRounds = new double[levels];
        DataManager.name = new String[levels];

        Set<String> temp2 = SettingsManager.config.getConfigurationSection(
            "enhance.0").getKeys(false);
        for (String key2 : temp2) {
            for (int i = 0; i < levels; i++) {
                if (key2.equalsIgnoreCase("baseChance")) {
                    DataManager.baseChance[i] = SettingsManager.config
                        .getDouble("enhance." + i + "." + key2);
                }
                else if (key2.equalsIgnoreCase("chanceIncreasePerFailstack")) {
                    DataManager.chanceIncreasePerFailstack[i] =
                        SettingsManager.config.getDouble("enhance." + i + "."
                            + key2);
                }
                else if (key2.equalsIgnoreCase("maximumFailstackApplied")) {
                    DataManager.chanceIncreasePerFailstack[i] =
                        SettingsManager.config.getDouble("enhance." + i + "."
                            + key2);
                }
                else if (key2.equalsIgnoreCase("failstackGainedPerFail")) {
                    DataManager.failstackGainedPerFail[i] =
                        SettingsManager.config.getDouble("enhance." + i + "."
                            + key2);
                }
                else if (key2.equalsIgnoreCase("costToForceEnchant")) {
                    DataManager.costToForceEnchant[i] = SettingsManager.config
                        .getDouble("enhance." + i + "." + key2);
                }
                else if (key2.equalsIgnoreCase("fireworks")) {
                    DataManager.fireworks[i] = SettingsManager.config
                        .getBoolean("enhance." + i + "." + key2);
                }
                else if (key2.equalsIgnoreCase("fireworkRounds")) {
                    DataManager.fireworkRounds[i] = SettingsManager.config
                        .getDouble("enhance." + i + "." + key2);
                }
                else if (key2.equalsIgnoreCase("name")) {
                    DataManager.name[i] = SettingsManager.config.getString(
                        "enhance." + i + "." + key2);
                }
            }
        }

    }
}
