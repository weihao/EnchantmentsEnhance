package org.pixeltime.healpot.enhancement.manager.modular;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import com.mcmoonlake.api.MoonLakeAPI;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import com.mcmoonlake.api.MoonLakeAPI;
import com.mcmoonlake.api.nbt.NBTCompound;
import com.mcmoonlake.api.nbt.NBTList;

public class Glow_1_8_R3 implements Glow {
    public ItemStack addGlow(ItemStack item) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack
            .asNMSCopy(item);
        NBTTagCompound nbt = nmsItem.getTag() == null
            ? new NBTTagCompound()
            : nmsItem.getTag();
        NBTTagList ench = new NBTTagList();
        nbt.set("ench", ench);
        nmsItem.setTag(nbt);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }
}
