package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ToolIcon extends Clickable {
    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(Material.DIAMOND_PICKAXE)
                .setName(SettingsManager.lang.getString("icon.tool1"))
                .addLoreLine(SettingsManager.lang.getString("icon.tool2"))
                .toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(2, 1);
    }
}