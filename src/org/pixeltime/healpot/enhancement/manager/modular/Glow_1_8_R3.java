package org.pixeltime.healpot.enhancement.manager.modular;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.healpot.enhancement.util.ReflectionUtils;

public class Glow_1_8_R3 implements Glow{
    public ItemStack addGlow(
        ItemStack item) {
        boolean enchanted = item.getItemMeta().hasEnchants();
        String name = item.getItemMeta().getDisplayName();
        List<String> lore = item.getItemMeta().getLore();
        
        try {
            Class<?> ItemStack = ReflectionUtils.getCraftClass("ItemStack");
            Class<?> NBTTagCompound = ReflectionUtils.getCraftClass(
                "NBTTagCompound");
            Class<?> NBTTagList = ReflectionUtils.getCraftClass("NBTTagList");
            Class<?> CraftItemStack = ReflectionUtils.getBukkitClass(
                "inventory.CraftItemStack");
            Class<?> NBTTagString = ReflectionUtils.getCraftClass(
                "NBTTagString");
            Class<?> NBTBase = ReflectionUtils.getCraftClass("NBTBase");

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

            if ((Boolean)hasTag.invoke(nmsStack))
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
            Bukkit.getLogger().log(Level.INFO, nmsStack.toString());
            Bukkit.getLogger().log(Level.INFO, ((org.bukkit.inventory.ItemStack)asCraftMirror.invoke(null,
                nmsStack)).getEnchantments().toString());
            Bukkit.getLogger().log(Level.INFO, (tag.toString()));
            Bukkit.getLogger().log(Level.INFO, (NBTBase.toString()));
            
            return (org.bukkit.inventory.ItemStack)asCraftMirror.invoke(null,
                nmsStack);
        }
        catch (Exception e) {
            e.printStackTrace();
            return item;
        }
    }
}
