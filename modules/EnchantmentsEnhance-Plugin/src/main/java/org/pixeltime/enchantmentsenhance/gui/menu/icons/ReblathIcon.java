package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ReblathIcon extends Clickable {

    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(Material.ANVIL)
                .setName(SettingsManager.lang.getString("menu.gui.reblath"))
                .addLoreLine(SettingsManager.lang.getString("reblath.info1"))
                .addLoreLine(SettingsManager.lang.getString("reblath.info2"))
                .toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(1, 4);
    }

    public double getChance() {
        return 17.5;
    }
}
