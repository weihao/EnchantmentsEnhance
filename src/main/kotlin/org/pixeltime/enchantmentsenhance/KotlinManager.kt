package org.pixeltime.enchantmentsenhance

import org.pixeltime.enchantmentsenhance.Main.getMain

class KM {
    companion object {
        @JvmStatic
        fun log() {
            getMain().logger.info("Kotlin module is enabled: Hello World!")
        }
        @JvmStatic
        fun sortArray(a: IntArray)
        {
            a.sort()
        }
    }
}