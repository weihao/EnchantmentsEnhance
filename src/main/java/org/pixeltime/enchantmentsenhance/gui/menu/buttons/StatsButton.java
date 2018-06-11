package org.pixeltime.enchantmentsenhance.gui.menu.buttons;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Failstack;
import org.pixeltime.enchantmentsenhance.interfaces.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class StatsButton extends Clickable {

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.WOOL).setDyeColor(DyeColor.LIGHT_BLUE).setName(SettingsManager.lang.getString("Menu.gui.stats")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.stats1")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.stats2")).toItemStack();
    }

    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.WOOL).setDyeColor(DyeColor.LIGHT_BLUE).setName(SettingsManager.lang.getString("Menu.gui.stats")).addLoreLine(SettingsManager.lang.getString("Enhance.currentFailstack")
                + Failstack.getLevel(player)).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.stats1")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.stats2")).toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(5, 2);
    }
}
