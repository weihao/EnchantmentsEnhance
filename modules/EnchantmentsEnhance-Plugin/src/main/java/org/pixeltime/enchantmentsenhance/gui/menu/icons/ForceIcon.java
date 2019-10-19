package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import com.lgou2w.ldk.bukkit.compatibility.DyeColors;
import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.event.Enhance;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.ConfigManager;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ForceIcon extends Clickable {
    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(XMaterial.RED_WOOL.toBukkit())
                .setDyeColor(DyeColors.RED.toBukkit())
                .setName(SettingsManager.lang.getString("menu.gui.force"))
                .addLoreLine(SettingsManager.lang.getString(
                        "menu.lore.force1")).toItemStack();
    }

    public ItemStack getItem(ItemStack item, Clickable clicked) {
        int enchantLevel;
        if (clicked.equals(MainMenu.gear)) {
            enchantLevel = ItemManager.getItemEnchantLevel(item);
        } else if (clicked.equals(MainMenu.tool)) {
            enchantLevel = ItemManager.getToolEnchantLevel(item);
        } else {
            return null;
        }
        int stoneId = Enhance.getStoneId(item, enchantLevel, clicked);
        int costToEnhance = ConfigManager.costToForceEnchant[enchantLevel + 1];
        return new ItemBuilder(XMaterial.RED_WOOL.toBukkit())
                .setDyeColor(DyeColors.RED.toBukkit())
                .setName(SettingsManager.lang.getString("menu.gui.force"))
                .addLoreLine(SettingsManager.lang.getString("menu.lore.force1"))
                .addLoreLine(SettingsManager.lang.getString("menu.lore.force2")
                        .replaceAll("%COUNT%", Integer.toString(costToEnhance))
                        .replaceAll("%ITEM%", SettingsManager.lang.getString("item." + stoneId))).toItemStack();
    }

    public ItemStack getGlowingItem(ItemStack item, Clickable clicked) {
        return CompatibilityManager.glow.addGlow(getItem(item, clicked));
    }

    @Override
    public int getPosition() {
        return Util.getSlot(6, 5);
    }
}
