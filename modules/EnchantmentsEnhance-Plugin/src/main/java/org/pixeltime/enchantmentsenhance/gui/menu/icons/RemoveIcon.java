package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import com.lgou2w.ldk.bukkit.compatibility.DyeColors;
import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class RemoveIcon extends Clickable {

    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(XMaterial.LIGHT_GRAY_WOOL.toBukkit()).setDyeColor(DyeColors.LIGHT_GRAY.toBukkit()).setName(SettingsManager.lang.getString("menu.gui.remove")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.remove")).toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(8, 5);
    }
}
