package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.DataManager;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Materials;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ForceIcon extends Clickable {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Materials.WOOL.bukkitMaterial()).setDyeColor(DyeColor.RED).setName(SettingsManager.lang.getString("Menu.gui.force")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.force1")).toItemStack();
    }

    public ItemStack getItem(ItemStack item) {
        int enchantLevel = ItemManager.getItemEnchantLevel(item);
        int stoneId = Enhance.getStoneId(item, enchantLevel);
        int costToEnhance = DataManager.costToForceEnchant[enchantLevel + 1];
        return CompatibilityManager.glow.addGlow(new ItemBuilder(Materials.WOOL.bukkitMaterial()).setDyeColor(DyeColor.RED).setName(SettingsManager.lang.getString("Menu.gui.force")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.force1")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.force2").replaceAll("%COUNT%", Integer.toString(
                costToEnhance)).replaceAll("%ITEM%", SettingsManager.lang
                .getString("Item." + stoneId))).toItemStack());
    }

    @Override
    public int getPosition() {
        return Util.getSlot(6, 5);
    }
}
