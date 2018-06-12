package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.interfaces.Clickable;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class GearIcon extends Clickable {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.DIAMOND_SWORD).setName("Gear").toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(1, 1);
    }
}