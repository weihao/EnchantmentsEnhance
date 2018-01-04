package com.github.healpot.plugin.enhancement.me.effect;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Glow_1_10_R1 implements Glow {
	public ItemStack addGlow(ItemStack item) {
		item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
		return item;
	}
}
