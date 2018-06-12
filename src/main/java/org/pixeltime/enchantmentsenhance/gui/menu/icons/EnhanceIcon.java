package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.interfaces.Clickable;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.DataManager;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class EnhanceIcon extends Clickable {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.WOOL).setDyeColor(DyeColor.YELLOW).setName(SettingsManager.lang.getString("Menu.gui.enhance")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.ifSuccess")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.ifFail")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.ifDowngrade")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.ifDestroy")).toItemStack();
    }

    public ItemStack getItem(ItemStack item) {
        int level = ItemManager.getItemEnchantLevel(item);
        ItemBuilder ib = new ItemBuilder(Material.WOOL).setDyeColor(DyeColor.YELLOW).setName(SettingsManager.lang.getString("Menu.gui.enhance")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.ifSuccess"));
        if (DataManager.baseChance[level] != 100) {
            ib.addLoreLine(SettingsManager.lang.getString(
                    "Menu.lore.ifFail"));
        }
        if (DataManager.downgradeIfFail[level]) {
            ib.addLoreLine(SettingsManager.lang.getString("Menu.lore.ifDowngrade"));
        }
        if (DataManager.destroyIfFail[level]) {
            ib.addLoreLine(SettingsManager.lang.getString(
                    "Menu.lore.ifDestroy")).toItemStack();
        }
        return CompatibilityManager.glow.addGlow(ib.toItemStack());
    }

    @Override
    public int getPosition() {
        return Util.getSlot(4, 5);
    }
}
