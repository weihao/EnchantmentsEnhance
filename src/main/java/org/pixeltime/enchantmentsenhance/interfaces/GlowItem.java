package org.pixeltime.enchantmentsenhance.interfaces;

import org.bukkit.inventory.ItemStack;

public interface GlowItem {
    /**
     * Makes an item glowing.
     *
     * @param item
     * @return
     */
    public ItemStack addGlow(ItemStack item);
}
