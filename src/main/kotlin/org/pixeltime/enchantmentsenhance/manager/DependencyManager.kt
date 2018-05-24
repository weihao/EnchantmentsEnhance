package org.pixeltime.enchantmentsenhance.manager


import net.milkbowl.vault.economy.Economy
import org.pixeltime.enchantmentsenhance.Main
import uk.antiperson.stackmob.StackMob

class DM {

    companion object {
        @JvmField
        var economy: Economy = Main.getMain().server.servicesManager.getRegistration<Any>(Economy::class.java as Class<Any>).provider as Economy
        @JvmField
        var stackmob: StackMob = Main.getMain().server.servicesManager.getRegistration<Any>(StackMob::class.java as Class<Any>).provider as StackMob


        @JvmStatic
        fun setupEconomy(): Boolean {
            return  economy != null
        }

        @JvmStatic
        fun setupStackMob(): Boolean {
            return stackmob != null
        }
    }
}
