package org.pixeltime.enchantmentsenhance.manager.modular;

import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.interfaces.GlowItem;
import org.pixeltime.enchantmentsenhance.util.reflection.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class GlowItem_NBT implements GlowItem {
    public ItemStack addGlow(ItemStack item) {
        boolean enchanted = item.getItemMeta().hasEnchants();
        String name = item.getItemMeta().getDisplayName();
        List<String> lore = item.getItemMeta().getLore();

        try {
            Class<?> ItemStack = Reflection.getCraftClass("ItemStack");
            Class<?> NBTTagCompound = Reflection.getCraftClass(
                    "NBTTagCompound");
            Class<?> NBTTagList = Reflection.getCraftClass("NBTTagList");
            Class<?> CraftItemStack = Reflection.getBukkitClass(
                    "inventory.CraftItemStack");
            Class<?> NBTTagString = Reflection.getCraftClass("NBTTagString");
            Class<?> NBTBase = Reflection.getCraftClass("NBTBase");

            Method asNMSCopy = CraftItemStack.getDeclaredMethod("asNMSCopy",
                    org.bukkit.inventory.ItemStack.class);
            Method asCraftMirror = CraftItemStack.getDeclaredMethod(
                    "asCraftMirror", ItemStack);
            Method hasTag = ItemStack.getDeclaredMethod("hasTag");
            Method setTag = ItemStack.getDeclaredMethod("setTag",
                    NBTTagCompound);
            Method getTag = ItemStack.getDeclaredMethod("getTag");
            Method set = NBTTagCompound.getDeclaredMethod("set", String.class,
                    NBTBase);
            Method add = NBTTagList.getDeclaredMethod("add", NBTBase);

            asNMSCopy.setAccessible(true);
            asCraftMirror.setAccessible(true);
            hasTag.setAccessible(true);
            setTag.setAccessible(true);
            getTag.setAccessible(true);
            set.setAccessible(true);

            Constructor<?> NBTTagCompoundConstructor = NBTTagCompound
                    .getConstructor();
            Constructor<?> NBTTagListConstructor = NBTTagList.getConstructor();
            Constructor<?> NBTTagStringConstructor = NBTTagString
                    .getConstructor(String.class);

            NBTTagCompoundConstructor.setAccessible(true);
            NBTTagListConstructor.setAccessible(true);

            Object nmsStack = asNMSCopy.invoke(null, item);
            Object tag = null;

            if ((Boolean) hasTag.invoke(nmsStack))
                tag = getTag.invoke(nmsStack);
            else
                tag = NBTTagCompoundConstructor.newInstance();

            if (enchanted) {
                Object ench = NBTTagListConstructor.newInstance();
                set.invoke(tag, "ench", ench);
            }

            Object display = NBTTagCompoundConstructor.newInstance();
            set.invoke(display, "Name", NBTTagStringConstructor.newInstance(
                    name));

            Object loreObj = NBTTagListConstructor.newInstance();
            for (String str : lore) {
                add.invoke(loreObj, NBTTagStringConstructor.newInstance(str));
            }
            set.invoke(display, "Lore", loreObj);

            set.invoke(tag, "display", display);

            setTag.invoke(nmsStack, tag);

            return (org.bukkit.inventory.ItemStack) asCraftMirror.invoke(null,
                    nmsStack);
        } catch (Exception e) {
            e.printStackTrace();
            return item;
        }
    }
}
