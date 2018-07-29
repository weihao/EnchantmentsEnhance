package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.api.API;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class StatsIcon extends Clickable {

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.WOOL).setDyeColor(DyeColor.LIGHT_BLUE)
                .setName(SettingsManager.lang.getString("Menu.gui.stats"))
                .addLoreLine(SettingsManager.lang.getString("Menu.lore.stats1"))
                .addLoreLine(SettingsManager.lang.getString("Menu.lore.stats2"))
                .toItemStack();
    }

    public ItemStack getItem(String playerName) {
        if (playerName != null && MainMenu.itemOnEnhancingSlot.get(playerName) != null) {
            return CompatibilityManager.glow.addGlow(new ItemBuilder(Material.WOOL).setDyeColor(DyeColor.LIGHT_BLUE).setName(SettingsManager.lang.getString("Menu.gui.stats")).addLoreLine(SettingsManager.lang.getString("Enhance.currentFailstack")
                    + API.getFailstack(playerName)).addLoreLine(Enhance.getChance(MainMenu.itemOnEnhancingSlot.get(playerName), playerName)).addLoreLine(SettingsManager.lang.getString(
                    "Menu.lore.stats1")).addLoreLine(SettingsManager.lang.getString(
                    "Menu.lore.stats2")).toItemStack());
        }
        if (playerName != null && API.hasFailstack(playerName)) {
            return CompatibilityManager.glow.addGlow(new ItemBuilder(Material.WOOL)
                    .setDyeColor(DyeColor.LIGHT_BLUE)
                    .setName(SettingsManager.lang.getString("Menu.gui.stats"))
                    .addLoreLine(SettingsManager.lang.getString("Enhance.currentFailstack")
                            + API.getFailstack(playerName))
                    .addLoreLine(SettingsManager.lang.getString("Menu.lore.stats1"))
                    .addLoreLine(SettingsManager.lang.getString("Menu.lore.stats2"))
                    .toItemStack());
        }
        return getItem();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(5, 2);
    }
}
