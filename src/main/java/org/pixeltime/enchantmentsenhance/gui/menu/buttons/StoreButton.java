package org.pixeltime.enchantmentsenhance.gui.menu.buttons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.interfaces.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class StoreButton extends Clickable {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.BOOK_AND_QUILL).setName(SettingsManager.lang
                .getString("Menu.gui.store")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.store1")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.store2")).toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(9, 1);
    }
}
