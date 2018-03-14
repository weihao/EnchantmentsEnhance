package org.pixeltime.healpot.enhancement.manager.modular;

import org.bukkit.inventory.ItemStack;
import org.pixeltime.healpot.enhancement.util.UnsafeGlow;

public class Glow_Unsafe implements Glow{

    @Override
    public ItemStack addGlow(ItemStack item) {
        UnsafeGlow.addGlow(item);
        return item;
    }
    
}
