package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class AccessoryIcon extends Clickable {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.DIAMOND).setName("Accessory").toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(3, 1);
    }
}