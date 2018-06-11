package org.pixeltime.enchantmentsenhance.interfaces;

import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;

public abstract class Clickable {
    public abstract ItemStack getItem();

    public ItemStack getGlowingItem() {
        return CompatibilityManager.glow.addGlow(getItem());
    }

    public abstract int getPosition();


}
