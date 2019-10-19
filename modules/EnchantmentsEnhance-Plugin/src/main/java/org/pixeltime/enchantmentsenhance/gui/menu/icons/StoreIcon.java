package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class StoreIcon extends Clickable {
    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(XMaterial.BOOK.toBukkit()).setName(SettingsManager.lang
                .getString("menu.gui.store")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.store1")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.store2")).toItemStack();
    }

    @Override
    public ItemStack getGlowingItem(String playerName) {
        return CompatibilityManager.glow.addGlow(new ItemBuilder(XMaterial.WRITABLE_BOOK.toBukkit()).setName(SettingsManager.lang
                .getString("menu.gui.store")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.store1")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.store2")).toItemStack());
    }

    @Override
    public int getPosition() {
        return Util.getSlot(9, 1);
    }
}
