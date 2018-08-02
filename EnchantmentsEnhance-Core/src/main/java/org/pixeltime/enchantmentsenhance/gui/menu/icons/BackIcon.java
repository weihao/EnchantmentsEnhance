package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class BackIcon extends Clickable {

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.ARROW)
                .setName(SettingsManager.lang.getString("Menu.gui.back"))
                .addLoreLine(SettingsManager.lang.getString("Menu.lore.back"))
                .toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(1, 6);
    }
}
