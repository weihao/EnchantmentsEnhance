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
            //TODO
//            itemlore.filter { lore == ChatColor.stripColor(it)!!.split(" ")[0] }.forEach {
//                val temp = it.split(" ")
//                try {
//                    val level = Util.romanToInt((temp)[temp.size - 1])
//                    return level
//                } catch (ex: NumberFormatException) {
//                }
//            }
            return 0
        }
    }
}
