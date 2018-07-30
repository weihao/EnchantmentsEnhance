package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class RemoveIcon extends Clickable {

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.WOOL).setDyeColor(DyeColor.SILVER).setName(SettingsManager.lang.getString("Menu.gui.remove")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.remove")).toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(8, 5);
    }
}
