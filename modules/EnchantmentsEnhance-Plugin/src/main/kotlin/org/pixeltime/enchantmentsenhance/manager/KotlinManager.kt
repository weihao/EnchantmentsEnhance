/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

import org.bukkit.ChatColor
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.util.Util

class KotlinManager {
    companion object {

        @JvmStatic
        fun sortArray(a: IntArray) {
            a.sort()
        }

        @JvmStatic
        fun getLevel(lore: String, itemlore: List<String>): Int {
            itemlore.filter { lore == ChatColor.stripColor(it)!!.split(" ")[0] }.forEach {
                val temp = it.split(" ")
                try {
                    val level = Util.romanToInt((temp)[temp.size - 1])
                    return level
                } catch (ex: NumberFormatException) {
                }
            }
            return 0
        }

        @JvmStatic
        fun stripLore(item: ItemStack): List<String>? {
            if (item.hasItemMeta() && item.itemMeta!!.hasLore() && item.itemMeta!!.lore!!.isNotEmpty()) {
                val lores = item.itemMeta!!.lore
                return lores!!.filter { !it.startsWith(Util.UNIQUEID) }
            }
            return null
        }
    }
}
