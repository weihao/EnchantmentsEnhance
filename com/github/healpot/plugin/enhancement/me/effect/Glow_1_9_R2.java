package com.github.healpot.plugin.enhancement.me.effect;

import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_9_R2.NBTTagCompound;
import net.minecraft.server.v1_9_R2.NBTTagList;

public class Glow_1_9_R2 implements Glow {
	public ItemStack addGlow(ItemStack item) {
		net.minecraft.server.v1_9_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound nbt = nmsItem.getTag() == null ? new NBTTagCompound() : nmsItem.getTag();
		NBTTagList ench = new NBTTagList();
		nbt.set("ench", ench);
		nmsItem.setTag(nbt);
		return CraftItemStack.asBukkitCopy(nmsItem);
	}
}
