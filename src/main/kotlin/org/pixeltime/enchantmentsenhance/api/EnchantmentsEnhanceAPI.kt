/*
 *     Copyright (C) 2017-Present HealPotion
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

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.gui.menu.icons.ItemIcon
import org.pixeltime.enchantmentsenhance.manager.DataManager
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat
import org.pixeltime.enchantmentsenhance.util.Util
import java.util.*

class API {
    companion object {

        @JvmStatic
        fun getNumberOfStone(player: Player, stoneId: Int): Int {
            return ItemIcon.getOneStoneCountAsInt(player.name, stoneId)
        }

        @JvmStatic
        fun AddCustomEnchant(item: ItemStack, enchantment: String, level: Int) {
            ItemManager.applyEnchantmentToItem(item, enchantment, level)
        }

        @JvmStatic
        fun setItem(player: String, type: Int, level: Int) {
            try {
                PlayerStat.getPlayerStats(player)!!.items[type] = level
            } catch (e: Exception) {
                Main.getMain().logger.info(
                        "Error when setting the player data.")
            }

        }

        @JvmStatic
        fun addItem(player: String, type: Int, levelsToAdd: Int) {
            val newLevel = getItem(player, type) + levelsToAdd
            setItem(player, type, newLevel)
        }

        @JvmStatic
        fun getItem(player: String, type: Int): Int {
            return if (PlayerStat.getPlayerStats(player) == null) 0 else PlayerStat.getPlayerStats(player)!!.items[type]
        }

        @JvmStatic
        fun resetFailstack(player: String) {
            setFailstack(player, 0)
        }

        @JvmStatic
        fun setFailstack(player: String, level: Int) {
            PlayerStat.getPlayerStats(player)!!.failstack = level
        }

        @JvmStatic
        fun addFailstack(player: String, levelsToAdd: Int) {
            val newLevel = getFailstack(player) + levelsToAdd
            setFailstack(player, newLevel)
        }

        @JvmStatic
        fun getFailstack(player: String): Int {
            return if (PlayerStat.getPlayerStats(player) == null) 0 else PlayerStat.getPlayerStats(player)!!.failstack
        }

        @JvmStatic
        fun getChance(playerName: String, enchantLevel: Int): Double {
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


        /**
         * Adds a player's failstack to the HashMap storage.
         *
         * @param player Targeted player.
         */
        @JvmStatic
        fun addAdvice(player: String) {
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
    }
}