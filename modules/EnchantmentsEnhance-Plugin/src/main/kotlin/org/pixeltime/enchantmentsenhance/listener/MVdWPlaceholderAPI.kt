package org.pixeltime.enchantmentsenhance.listener

import be.maximvdw.placeholderapi.PlaceholderAPI
import org.bukkit.Bukkit
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.manager.MaterialManager

class MVdWPlaceholderAPI {
    companion object {
        @JvmStatic
        fun setUp() {
            if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
                for (Int in 0 until MaterialManager.stoneTypes.size) {
                    PlaceholderAPI.registerPlaceholder(Main.getMain(),
                            "EE_item_$Int"
                    ) { event ->
                        Main.getApi().getItem(event.player.name, Int).toString()
                    }
                }
                PlaceholderAPI.registerPlaceholder(Main.getMain(),
                        "EE_failstack"
                ) { event ->
                    Main.getApi().getFailstack(event.player.displayName).toString()
                }
            }
        }
    }
}