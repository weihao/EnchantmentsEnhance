package org.pixeltime.enchantmentsenhance.manager

import org.pixeltime.enchantmentsenhance.Main.getMain
import org.pixeltime.enchantmentsenhance.util.Util

class KM {
    companion object {
        @JvmStatic
        fun log() {
            getMain().logger.info("Kotlin module is enabled: Hello World!")
        }

        @JvmStatic
        fun sortArray(a: IntArray) {
            a.sort()
        }

        @JvmStatic
        fun getLevel(lore: String, itemlore: List<String>): Int {
            itemlore.filter { it.contains(lore) }.forEach {
                var temp = it.split(" ")
                return (Util.romanToInt((temp)[temp.size - 1]))
            }
            return 0
        }
    }
}