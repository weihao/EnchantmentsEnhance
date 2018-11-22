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

package org.pixeltime.enchantmentsenhance.manager

object DataManager {

    var levels: Int = 0
    lateinit var baseChance: DoubleArray
    lateinit var chanceIncreasePerFailstack: DoubleArray
    lateinit var maximumFailstackApplied: IntArray
    lateinit var failstackGainedPerFail: IntArray
    lateinit var costToForceEnchant: IntArray
    lateinit var fireworks: BooleanArray
    lateinit var fireworkRounds: IntArray
    lateinit var name: Array<String?>
    lateinit var downgradeChanceIfFail: DoubleArray
    lateinit var destroyChanceIfFail: DoubleArray
    lateinit var requireConcentratedStones: BooleanArray
    lateinit var broadcastEnhance: BooleanArray

    fun setUp() {
        val temp = SettingsManager.config.getConfigurationSection("enhance").getKeys(false)
        levels = temp.size
        DataManager.baseChance = DoubleArray(levels)
        DataManager.chanceIncreasePerFailstack = DoubleArray(levels)
        DataManager.maximumFailstackApplied = IntArray(levels)
        DataManager.failstackGainedPerFail = IntArray(levels)
        DataManager.costToForceEnchant = IntArray(levels)
        DataManager.fireworks = BooleanArray(levels)
        DataManager.fireworkRounds = IntArray(levels)
        DataManager.name = arrayOfNulls(levels)
        DataManager.downgradeChanceIfFail = DoubleArray(levels)
        DataManager.destroyChanceIfFail = DoubleArray(levels)
        DataManager.requireConcentratedStones = BooleanArray(levels)
        DataManager.broadcastEnhance = BooleanArray(levels)


        val temp2 = SettingsManager.config.getConfigurationSection(
                "enhance.0").getKeys(false)
        for (key2 in temp2) {
            for (i in 0 until levels) {
                if (key2.equals("baseChance", ignoreCase = true)) {
                    DataManager.baseChance[i] = SettingsManager.config
                        .getDouble("enhance.$i.$key2")
                } else if (key2.equals("chanceIncreasePerFailstack", ignoreCase = true)) {
                    DataManager.chanceIncreasePerFailstack[i] = SettingsManager.config.getDouble("enhance." + i + "."
                                                                                                 + key2)
                } else if (key2.equals("maximumFailstackApplied", ignoreCase = true)) {
                    DataManager.maximumFailstackApplied[i] = SettingsManager.config.getInt("enhance." + i + "."
                                                                                           + key2)
                } else if (key2.equals("failstackGainedPerFail", ignoreCase = true)) {
                    DataManager.failstackGainedPerFail[i] = SettingsManager.config.getInt("enhance." + i + "."
                                                                                          + key2)
                } else if (key2.equals("costToForceEnchant", ignoreCase = true)) {
                    DataManager.costToForceEnchant[i] = SettingsManager.config
                        .getInt("enhance.$i.$key2")
                } else if (key2.equals("downgradeChanceIfFail", ignoreCase = true)) {
                    DataManager.downgradeChanceIfFail[i] = SettingsManager.config
                        .getDouble("enhance.$i.$key2")
                } else if (key2.equals("destroyChanceIfFail", ignoreCase = true)) {
                    DataManager.destroyChanceIfFail[i] = SettingsManager.config
                        .getDouble("enhance.$i.$key2")
                } else if (key2.equals("requireConcentratedStones", ignoreCase = true)) {
                    DataManager.requireConcentratedStones[i] = SettingsManager.config
                        .getBoolean("enhance.$i.$key2")
                } else if (key2.equals("fireworks", ignoreCase = true)) {
                    DataManager.fireworks[i] = SettingsManager.config
                        .getBoolean("enhance.$i.$key2")
                } else if (key2.equals("fireworkRounds", ignoreCase = true)) {
                    DataManager.fireworkRounds[i] = SettingsManager.config
                        .getInt("enhance.$i.$key2")
                } else if (key2.equals("name", ignoreCase = true)) {
                    DataManager.name[i] = SettingsManager.config.getString(
                            "enhance.$i.$key2")
                } else if (key2.equals("broadcastEnhance", ignoreCase = true)) {
                    DataManager.broadcastEnhance[i] = SettingsManager.config.getBoolean(
                            "enhance.$i.$key2")
                }
            }
        }
    }
}
