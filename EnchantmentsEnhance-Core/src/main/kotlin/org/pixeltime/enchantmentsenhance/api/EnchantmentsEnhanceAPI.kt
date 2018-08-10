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

package org.pixeltime.enchantmentsenhance.api

import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.gui.menu.icons.ItemIcon
import org.pixeltime.enchantmentsenhance.manager.DataManager
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat
import org.pixeltime.enchantmentsenhance.util.Util
import java.util.*

class API : AbstractAPI {
    override fun addAdvice(player: String, level: Int) {
        if (level != 0) {
            PlayerStat.getPlayerStats(player).valks.add(level)
            Collections.sort(PlayerStat.getPlayerStats(player)!!.valks, Collections.reverseOrder())
        }
    }

    override fun getNumberOfStone(playerName: String, stoneId: Int): Int {
        return ItemIcon.getOneStoneCountAsInt(playerName, stoneId)
    }

    override fun addCustomEnchant(item: ItemStack, enchantment: String, level: Int): Boolean {
        return ItemManager.applyEnchantmentToItem(item, enchantment, level)
    }


    override fun setItem(player: String, type: Int, level: Int) {
        try {
            PlayerStat.getPlayerStats(player)!!.items[type] = level
        } catch (e: Exception) {
            Main.getMain().logger.info(
                    "Error when setting the player data.")
        }

    }


    override fun addItem(player: String, type: Int, levelsToAdd: Int) {
        val newLevel = getItem(player, type) + levelsToAdd
        setItem(player, type, newLevel)
    }


    override fun getItem(player: String, type: Int): Int {
        return if (PlayerStat.getPlayerStats(player) == null) 0 else PlayerStat.getPlayerStats(player)!!.items[type]
    }


    override fun resetFailstack(player: String) {
        setFailstack(player, 0)
    }


    override fun setFailstack(player: String, level: Int) {
        PlayerStat.getPlayerStats(player)!!.failstack = level
    }


    override fun addFailstack(player: String, levelsToAdd: Int) {
        val newLevel = getFailstack(player) + levelsToAdd
        setFailstack(player, newLevel)
    }


    override fun getFailstack(player: String): Int {
        return if (PlayerStat.getPlayerStats(player) == null) 0 else PlayerStat.getPlayerStats(player)!!.failstack
    }


    override fun hasFailstack(player: String): Boolean {
        return getFailstack(player) > 0
    }


    override fun getChance(playerName: String, enchantLevel: Int): Double {
        var failstack = getFailstack(playerName)
        val maximumFailstack = DataManager.maximumFailstackApplied[enchantLevel]
        val baseChance = DataManager.baseChance[enchantLevel]
        val increasePerFailstack = DataManager.chanceIncreasePerFailstack[enchantLevel]

        if (failstack > maximumFailstack) {
            failstack = maximumFailstack
        }
        return if (increasePerFailstack == -1.0 || maximumFailstack == -1) {
            baseChance / 100
        } else {
            (baseChance + failstack * increasePerFailstack) / 100
        }
    }


    override fun addAdvice(player: String) {
        val level = getFailstack(player)
        if (level != 0) {
            PlayerStat.getPlayerStats(player)!!.valks.add(level)
            Collections.sort(PlayerStat.getPlayerStats(player)!!.valks, Collections.reverseOrder())
            Util.sendMessage(SettingsManager.lang.getString("Save.createFailstack")
                    .replace("%failstack%".toRegex(), Integer.toString(getFailstack(
                            player))), player)
            resetFailstack(player)
        }
    }


    override fun getEnchantmentMaxLevel(ench: String): Int {
        return if (SettingsManager.enchant.getConfigurationSection(ench.toLowerCase()) == null) {
            1
        } else {
            SettingsManager.enchant.getConfigurationSection(ench.toLowerCase()).getKeys(false).size
        }
    }

}