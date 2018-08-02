package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;
import org.pixeltime.enchantmentsenhance.util.XMaterial;

public class RemoveIcon extends Clickable {

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(XMaterial.WHITE_WOOL.parseMaterial()).setDyeColor(DyeColor.GRAY).setName(SettingsManager.lang.getString("Menu.gui.remove")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.remove")).toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(8, 5);
    }
}
