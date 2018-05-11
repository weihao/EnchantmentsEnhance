package org.pixeltime.enchantmentsenhance.manager;

import java.util.Set;

public class DataManager {
    public static int levels;
    public static double[] baseChance;
    public static double[] chanceIncreasePerFailstack;
    public static int[] maximumFailstackApplied;
    public static int[] failstackGainedPerFail;
    public static int[] costToForceEnchant;
    public static boolean[] fireworks;
    public static int[] fireworkRounds;
    public static String[] name;

    public static int firstPhase;
    public static int secondPhase;
    public static int downgradePhase;

    public DataManager() {
        firstPhase = SettingsManager.config.getInt("FirstPhaseStarts");
        secondPhase = SettingsManager.config.getInt("SecondPhaseStarts");
        downgradePhase = SettingsManager.config.getInt("DowngradePhaseStarts");


        Set<String> temp = SettingsManager.config.getConfigurationSection(
                "enhance").getKeys(false);
        levels = temp.size();
        DataManager.baseChance = new double[levels];
        DataManager.chanceIncreasePerFailstack = new double[levels];
        DataManager.maximumFailstackApplied = new int[levels];
        DataManager.failstackGainedPerFail = new int[levels];
        DataManager.costToForceEnchant = new int[levels];
        DataManager.fireworks = new boolean[levels];
        DataManager.fireworkRounds = new int[levels];
        DataManager.name = new String[levels];

        Set<String> temp2 = SettingsManager.config.getConfigurationSection(
                "enhance.0").getKeys(false);
        for (String key2 : temp2) {
            for (int i = 0; i < levels; i++) {
                if (key2.equalsIgnoreCase("baseChance")) {
                    DataManager.baseChance[i] = SettingsManager.config
                            .getDouble("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("chanceIncreasePerFailstack")) {
                    DataManager.chanceIncreasePerFailstack[i] =
                            SettingsManager.config.getDouble("enhance." + i + "."
                                    + key2);
                } else if (key2.equalsIgnoreCase("maximumFailstackApplied")) {
                    DataManager.maximumFailstackApplied[i] =
                            SettingsManager.config.getInt("enhance." + i + "."
                                    + key2);
                } else if (key2.equalsIgnoreCase("failstackGainedPerFail")) {
                    DataManager.failstackGainedPerFail[i] =
                            SettingsManager.config.getInt("enhance." + i + "."
                                    + key2);
                } else if (key2.equalsIgnoreCase("costToForceEnchant")) {
                    DataManager.costToForceEnchant[i] = SettingsManager.config
                            .getInt("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("fireworks")) {
                    DataManager.fireworks[i] = SettingsManager.config
                            .getBoolean("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("fireworkRounds")) {
                    DataManager.fireworkRounds[i] = SettingsManager.config
                            .getInt("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("name")) {
                    DataManager.name[i] = SettingsManager.config.getString(
                            "enhance." + i + "." + key2);
                }
            }
        }

    }
}
