package org.pixeltime.enchantmentsenhance.gui.menu.buttons;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Failstack;
import org.pixeltime.enchantmentsenhance.gui.menu.Menu;
import org.pixeltime.enchantmentsenhance.interfaces.Clickable;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
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

    public ItemStack getItem(String playerName) {
        if (playerName != null && Menu.itemOnEnhancingSlot.get(playerName) != null) {
            return CompatibilityManager.glow.addGlow(new ItemBuilder(Material.WOOL).setDyeColor(DyeColor.LIGHT_BLUE).setName(SettingsManager.lang.getString("Menu.gui.stats")).addLoreLine(SettingsManager.lang.getString("Enhance.currentFailstack")
                    + Failstack.getLevel(playerName)).addLoreLine(Enhance.getChance(Menu.itemOnEnhancingSlot.get(playerName), playerName)).addLoreLine(SettingsManager.lang.getString(
                    "Menu.lore.stats1")).addLoreLine(SettingsManager.lang.getString(
                    "Menu.lore.stats2")).toItemStack());
        }
        return getItem();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(5, 2);
    }
}
