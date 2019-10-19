package org.pixeltime.enchantmentsenhance.gui;

import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;

public abstract class Clickable {
    public abstract ItemStack getItem(String player);

    public ItemStack getGlowingItem(String playerName) {
        return CompatibilityManager.glow.addGlow(getItem(playerName));
    }

    public abstract int getPosition();

}
