package com.github.healpot.plugin.enhancement.me.effect;

import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_7_R4.NBTTagCompound;
import net.minecraft.server.v1_7_R4.NBTTagList;

public class Glow_1_7_R4 implements Glow {
	public ItemStack addGlow(ItemStack item) {
		net.minecraft.server.v1_7_R4.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound nbt = nmsItem.getTag() == null ? new NBTTagCompound() : nmsItem.getTag();
		NBTTagList ench = new NBTTagList();
		nbt.set("ench", ench);
		nmsItem.setTag(nbt);
		return CraftItemStack.asBukkitCopy(nmsItem);
	}
}
