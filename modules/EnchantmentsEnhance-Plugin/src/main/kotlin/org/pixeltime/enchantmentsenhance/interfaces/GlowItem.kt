package org.pixeltime.enchantmentsenhance.interfaces

import org.bukkit.inventory.ItemStack

interface GlowItem {
    /**
     * Makes an item glowing.
     *
     * @param item
     * @return
     */
    fun addGlow(item: ItemStack): ItemStack
}
