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

interface AbstractAPI {

    fun getNumberOfStone(playerName: String, stoneId: Int): Int
    fun addCustomEnchant(item: ItemStack, enchantment: String, level: Int): Boolean
    fun setItem(player: String, type: Int, level: Int)
    fun addItem(player: String, type: Int, levelsToAdd: Int)
    fun getItem(player: String, type: Int): Int
    fun resetFailstack(player: String)
    fun setFailstack(player: String, level: Int)
    fun addFailstack(player: String, levelsToAdd: Int)
    fun getFailstack(player: String): Int
    fun hasFailstack(player: String): Boolean
    fun getChance(playerName: String, enchantLevel: Int): Double
    /**
     * Adds a player's failstack to the HashMap storage.
     *
     * @param player Targeted player.
     */
    fun addAdvice(player: String)

    /**
     * Adds a specific level of advice to a player.
     *
     * @param player Targeted player.
     */
    fun addAdvice(player: String, level: Int)

    fun getEnchantmentMaxLevel(ench: String): Int
}
