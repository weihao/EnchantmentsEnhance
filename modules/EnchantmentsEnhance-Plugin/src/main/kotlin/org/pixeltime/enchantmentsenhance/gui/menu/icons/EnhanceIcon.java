package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import com.lgou2w.ldk.bukkit.compatibility.DyeColors;
import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.DataManager;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class EnhanceIcon extends Clickable {
    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(XMaterial.YELLOW_WOOL.toBukkit()).setDyeColor(DyeColors.YELLOW.toBukkit()).setName(SettingsManager.lang.getString("menu.gui.enhance")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.ifSuccess")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.ifFail")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.ifDowngrade")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.ifDestroy")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.skip"))
                .toItemStack();
    }

    public ItemStack getItem(ItemStack item) {
        int level = Math.max(ItemManager.getItemEnchantLevel(item),
                ItemManager.getToolEnchantLevel(item));
        ItemBuilder ib = new ItemBuilder(XMaterial.YELLOW_WOOL.toBukkit()).setDyeColor(DyeColors.YELLOW.toBukkit()).setName(SettingsManager.lang.getString("menu.gui.enhance")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.ifSuccess"));
        if (DataManager.baseChance[level] != 100.0) {
            ib.addLoreLine(SettingsManager.lang.getString(
                    "menu.lore.ifFail"));
        }
        if (DataManager.downgradeChanceIfFail[level] > 0) {
            ib.addLoreLine(SettingsManager.lang.getString("menu.lore.ifDowngrade"));
        }
        if (DataManager.destroyChanceIfFail[level] > 0) {
            ib.addLoreLine(SettingsManager.lang.getString(
                    "menu.lore.ifDestroy")).toItemStack();
        }
        ib.addLoreLine(SettingsManager.lang.getString(
                "menu.lore.skip")).toItemStack();
        return ib.toItemStack();
    }

    public ItemStack getGlowingItem(ItemStack item) {
        return CompatibilityManager.glow.addGlow(getItem(item));
    }

    @Override
    public int getPosition() {
        return Util.getSlot(4, 5);
    }
}
