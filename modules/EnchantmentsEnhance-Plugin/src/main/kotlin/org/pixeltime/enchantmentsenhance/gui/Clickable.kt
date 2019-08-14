package org.pixeltime.enchantmentsenhance.gui

import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager

abstract class Clickable {

    abstract val position: Int
    abstract fun getItem(player: String): ItemStack

    open fun getGlowingItem(playerName: String): ItemStack {
        return CompatibilityManager.glow.addGlow(getItem(playerName))
    }

}
