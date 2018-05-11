package org.pixeltime.enchantmentsenhance

import org.pixeltime.enchantmentsenhance.Main.getMain

class KM {
    companion object {
        @JvmStatic
        fun log() {
            getMain().logger.info("Kotlin module is enabled: Hello World!")
        }
        @JvmStatic
        fun sorting(a: IntArray)
        {
            a.sort()
        }
    }
}