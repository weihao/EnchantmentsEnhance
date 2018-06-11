package org.pixeltime.enchantmentsenhance.gui.menu.buttons;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.interfaces.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ForceButton extends Clickable {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.WOOL).setDyeColor(DyeColor.RED).setName(SettingsManager.lang.getString("Menu.gui.force")).addLoreLine(Util.toColor(SettingsManager.lang.getString(
                "Menu.lore.force1"))).toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(6, 5);
    }
}
