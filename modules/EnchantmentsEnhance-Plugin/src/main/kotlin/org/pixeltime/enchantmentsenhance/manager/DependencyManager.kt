package org.pixeltime.enchantmentsenhance.manager


import net.milkbowl.vault.economy.Economy
import org.pixeltime.enchantmentsenhance.Main

class DependencyManager {

    companion object {
        @JvmField
        var economy: Economy? = null


        @JvmStatic
        fun setupEconomy(): Boolean {
            val registration =
                Main.getMain().server.servicesManager.getRegistration<Any>(Economy::class.java as Class<Any>)
                    ?: return false
            economy = registration.provider as Economy
            return true
        }
    }
}
