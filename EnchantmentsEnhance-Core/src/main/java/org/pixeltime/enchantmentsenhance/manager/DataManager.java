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
    public static boolean[] downgradeIfFail;
    public static boolean[] destroyIfFail;
    public static boolean[] requireConcentratedStones;
    public static boolean[] broadcastEnhance;

    public static void setUp() {
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
        DataManager.downgradeIfFail = new boolean[levels];
        DataManager.destroyIfFail = new boolean[levels];
        DataManager.requireConcentratedStones = new boolean[levels];
        DataManager.broadcastEnhance = new boolean[levels];


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
                } else if (key2.equalsIgnoreCase("downgradeIfFail")) {
                    DataManager.downgradeIfFail[i] = SettingsManager.config
                            .getBoolean("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("destroyIfFail")) {
                    DataManager.destroyIfFail[i] = SettingsManager.config
                            .getBoolean("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("requireConcentratedStones")) {
                    DataManager.requireConcentratedStones[i] = SettingsManager.config
                            .getBoolean("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("fireworks")) {
                    DataManager.fireworks[i] = SettingsManager.config
                            .getBoolean("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("fireworkRounds")) {
                    DataManager.fireworkRounds[i] = SettingsManager.config
                            .getInt("enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("name")) {
                    DataManager.name[i] = SettingsManager.config.getString(
                            "enhance." + i + "." + key2);
                } else if (key2.equalsIgnoreCase("broadcastEnhance")) {
                    DataManager.broadcastEnhance[i] = SettingsManager.config.getBoolean(
                            "enhance." + i + "." + key2);
                }
            }
        }
    }
}
