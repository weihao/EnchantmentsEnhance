package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Materials;
import org.pixeltime.enchantmentsenhance.util.Util;

public class StoreIcon extends Clickable {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Materials.BOOK_AND_QUILL.bukkitMaterial()).setName(SettingsManager.lang
                .getString("Menu.gui.store")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.store1")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.store2")).toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(9, 1);
    }
}
