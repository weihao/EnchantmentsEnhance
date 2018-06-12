package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.interfaces.Clickable;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ToolIcon extends Clickable {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.DIAMOND_PICKAXE).setName("Tools").toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(2, 1);
    }
}