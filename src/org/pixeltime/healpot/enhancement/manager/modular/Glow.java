package org.pixeltime.healpot.enhancement.manager.modular;

import org.bukkit.inventory.ItemStack;

public interface Glow {
    /**
     * Makes an item glowing.
     * 
     * @param item
     * @return
     */
    public ItemStack addGlow(ItemStack item);
}
