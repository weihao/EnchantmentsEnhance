package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import com.lgou2w.ldk.bukkit.compatibility.DyeColors;
import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class CancelIcon extends Clickable {

    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(XMaterial.RED_WOOL.toBukkit())
                .setDyeColor(DyeColors.RED.toBukkit())
                .setName(SettingsManager.lang.getString("menu.gui.cancel"))
                .addLoreLine(SettingsManager.lang.getString("menu.lore.cancel"))
                .toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(4, 5);
    }
}
