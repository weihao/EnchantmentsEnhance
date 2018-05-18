package org.pixeltime.enchantmentsenhance.manager


import net.milkbowl.vault.economy.Economy
import org.pixeltime.enchantmentsenhance.Main
import uk.antiperson.stackmob.StackMob

class DM {

    companion object {
        @JvmField
        var economy: Economy? = null
        @JvmField
        var stackmob: StackMob? = null


        @JvmStatic
        fun setupEconomy(): Boolean {
            val registration = Main.getMain().server.servicesManager.getRegistration<Any>(Economy::class.java as Class<Any>)
                    ?: return false
            economy = registration.provider as Economy
            return true
        }

        @JvmStatic
        fun setupStackMob(): Boolean {
            val registration1 = Main.getMain().server.servicesManager.getRegistration<Any>(StackMob::class.java as Class<Any>)
                    ?: return false
            var stackmob = registration1.provider as StackMob

            return true
        }
    }
}
