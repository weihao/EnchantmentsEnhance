package org.pixeltime.enchantmentsenhance.manager;

import java.util.Set;

public class ConfigManager {
    public static int levels;
    public static double[] baseChance;
    public static double[] chanceIncreasePerFailstack;
    public static int[] maximumFailstackApplied;
    public static int[] failstackGainedPerFail;
    public static int[] costToForceEnchant;
    public static boolean[] fireworks;
    public static int[] fireworkRounds;
    public static String[] name;
    public static double[] downgradeChanceIfFail;
    public static double[] destroyChanceIfFail;
    public static boolean[] requireConcentratedStones;
    public static boolean[] broadcastEnhance;

    public static void setUp() {
        Set<String> temp = SettingsManager.config.getConfigurationSection(
                "enhance").getKeys(false);
        levels = temp.size();
        ConfigManager.baseChance = new double[levels];
        ConfigManager.chanceIncreasePerFailstack = new double[levels];
        ConfigManager.maximumFailstackApplied = new int[levels];
        ConfigManager.failstackGainedPerFail = new int[levels];
        ConfigManager.costToForceEnchant = new int[levels];
        ConfigManager.fireworks = new boolean[levels];
        ConfigManager.fireworkRounds = new int[levels];
        ConfigManager.name = new String[levels];
        ConfigManager.downgradeChanceIfFail = new double[levels];
        ConfigManager.destroyChanceIfFail = new double[levels];
        ConfigManager.requireConcentratedStones = new boolean[levels];
        ConfigManager.broadcastEnhance = new boolean[levels];


        Set<String> temp2 = SettingsManager.config.getConfigurationSection(
                "enhance.0").getKeys(false);
        for (String key2 : temp2) {
            for (int i = 0; i < levels; i++) {
                if (key2.equalsIgnoreCase("baseChance")) {
                    ConfigManager.baseChance[i] = SettingsManager.config
                            .getDouble("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("chanceIncreasePerFailstack")) {
                    ConfigManager.chanceIncreasePerFailstack[i] =
                            SettingsManager.config.getDouble("enhance." + i + "."
                                    + key2);
                } else if (key2.equalsIgnoreCase("maximumFailstackApplied")) {
                    ConfigManager.maximumFailstackApplied[i] =
                            SettingsManager.config.getInt("enhance." + i + "."
                                    + key2);
                } else if (key2.equalsIgnoreCase("failstackGainedPerFail")) {
                    ConfigManager.failstackGainedPerFail[i] =
                            SettingsManager.config.getInt("enhance." + i + "."
                                    + key2);
                } else if (key2.equalsIgnoreCase("costToForceEnchant")) {
                    ConfigManager.costToForceEnchant[i] = SettingsManager.config
                            .getInt("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("downgradeChanceIfFail")) {
                    ConfigManager.downgradeChanceIfFail[i] = SettingsManager.config
                            .getDouble("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("destroyChanceIfFail")) {
                    ConfigManager.destroyChanceIfFail[i] = SettingsManager.config
                            .getDouble("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("requireConcentratedStones")) {
                    ConfigManager.requireConcentratedStones[i] = SettingsManager.config
                            .getBoolean("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("fireworks")) {
                    ConfigManager.fireworks[i] = SettingsManager.config
                            .getBoolean("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("fireworkRounds")) {
                    ConfigManager.fireworkRounds[i] = SettingsManager.config
                            .getInt("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("name")) {
                    ConfigManager.name[i] = SettingsManager.config.getString(
                            "enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("broadcastEnhance")) {
                    ConfigManager.broadcastEnhance[i] = SettingsManager.config.getBoolean(
                            "enhance." + i + "." + key2);
                }
            }
        }
    }
}
