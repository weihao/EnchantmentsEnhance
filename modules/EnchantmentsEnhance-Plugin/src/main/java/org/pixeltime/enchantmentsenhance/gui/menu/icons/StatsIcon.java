package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import com.lgou2w.ldk.bukkit.compatibility.DyeColors;
import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.EnhanceManager;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class StatsIcon extends Clickable {


    public ItemStack getItem() {
        return new ItemBuilder(XMaterial.LIGHT_BLUE_WOOL.toBukkit())
                .setDyeColor(DyeColors.LIGHT_BLUE.toBukkit())
                .setName(SettingsManager.lang.getString("menu.gui.stats"))
                .addLoreLine(SettingsManager.lang.getString("menu.lore.stats1"))
                .addLoreLine(SettingsManager.lang.getString("menu.lore.stats2"))
                .toItemStack();
    }

    public ItemStack getItem(String playerName, Clickable clicked) {
        if (playerName != null && MainMenu.itemOnEnhancingSlot.get(playerName) != null) {
            return CompatibilityManager.glow
                    .addGlow(new ItemBuilder(XMaterial.LIGHT_BLUE_WOOL.toBukkit())
                            .setDyeColor(DyeColors.LIGHT_BLUE.toBukkit())
                            .setName(SettingsManager.lang.getString("menu.gui.stats"))
                            .addLoreLine(SettingsManager.lang.getString("enhance.currentFailstack")
                                    + Main.getApi().getFailstack(playerName))
                            .addLoreLine(EnhanceManager.getChance(MainMenu.itemOnEnhancingSlot.get(playerName), playerName, clicked))
                            .addLoreLine(SettingsManager.lang.getString(
                                    "menu.lore.stats1")).addLoreLine(SettingsManager.lang.getString(
                                    "menu.lore.stats2")).toItemStack());
        }
        if (playerName != null && Main.getApi().hasFailstack(playerName)) {
            return CompatibilityManager.glow.addGlow(new ItemBuilder(XMaterial.LIGHT_BLUE_WOOL.toBukkit())
                    .setDyeColor(DyeColors.LIGHT_BLUE.toBukkit())
                    .setName(SettingsManager.lang.getString("menu.gui.stats"))
                    .addLoreLine(SettingsManager.lang.getString("enhance.currentFailstack")
                            + Main.getApi().getFailstack(playerName))
                    .addLoreLine(SettingsManager.lang.getString("menu.lore.stats1"))
                    .addLoreLine(SettingsManager.lang.getString("menu.lore.stats2"))
                    .toItemStack());
        }
        return getItem();
    }

    @Override
    public ItemStack getItem(String player) {
        return getItem();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(5, 2);
    }
}
