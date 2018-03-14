package org.pixeltime.healpot.enhancement.manager.modular;

import org.bukkit.inventory.ItemStack;
import org.pixeltime.healpot.enhancement.interfaces.GlowItem;
import org.pixeltime.healpot.enhancement.util.UnsafeGlow;

public class GlowItem_Unsafe implements GlowItem{

    @Override
    public ItemStack addGlow(ItemStack item) {
        UnsafeGlow.addGlow(item);
        return item;
    }
    
}
