package org.pixeltime.healpot.enhancement.manager.modular;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Glow_1_12_R1 implements Glow {
    public ItemStack addGlow(ItemStack item) {
        glowing(item);
        return item;
    }


    public void glowing(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        meta.addEnchant(Enchantment.LURE, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        stack.setItemMeta(meta);
    }
}
