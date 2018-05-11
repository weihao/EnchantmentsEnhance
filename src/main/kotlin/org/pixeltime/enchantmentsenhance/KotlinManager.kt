package org.pixeltime.enchantmentsenhance

import org.pixeltime.enchantmentsenhance.Main.getMain

class KM {
    companion object {
        @JvmStatic
        fun foo() {
            getMain().logger.info("Kotlin module is enabled: Hello World!")
        }
    }
}