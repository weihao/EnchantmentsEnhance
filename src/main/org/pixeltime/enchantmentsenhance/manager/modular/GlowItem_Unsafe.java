package org.pixeltime.enchantmentsenhance.manager.modular;

import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.interfaces.GlowItem;
import org.pixeltime.enchantmentsenhance.util.UnsafeGlow;

public class GlowItem_Unsafe implements GlowItem {
    public ItemStack addGlow(ItemStack item) {
        UnsafeGlow.addGlow(item);
        return item;
    }

}
