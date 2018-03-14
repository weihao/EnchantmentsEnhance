package org.pixeltime.healpot.enhancement.manager.modular;

import java.lang.reflect.Method;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import de.tr7zw.itemnbtapi.NBTReflectionUtil;

public class Glow_1_8_R3 implements Glow {
    @SuppressWarnings("unchecked")
    public ItemStack addGlow(ItemStack item) {
        Object nmsItem = NBTReflectionUtil.getNMSItemStack(item);
        Object nbt;
        Method method;
        try {
            method = NBTReflectionUtil.getNMSItemStack().getMethod("getTag",
                null);
            if (method.invoke(nmsItem, null) == null) {
                nbt = NBTReflectionUtil.getNBTTagCompound().newInstance();
            }
            else {
                nbt = method.invoke(nmsItem, null);
            }
            Object ench = NBTReflectionUtil.getNBTTagList().newInstance();
            NBTReflectionUtil.getNBTTagCompound().getMethod("set", String.class,
                NBTReflectionUtil.getNBTBase()).invoke(nbt, "ench", ench);
            NBTReflectionUtil.setNBTTag(nbt, nmsItem);
            Object glowingItem = NBTReflectionUtil.getCraftItemStack()
                .getMethod("asBukkitCopy", NBTReflectionUtil.getNMSItemStack())
                .invoke(null, nmsItem);
            return (ItemStack)glowingItem;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
