package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.PlayerStatsManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class GrindIcon extends Clickable {


    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(Material.ANVIL)
                .setName(SettingsManager.lang.getString("menu.gui.grind"))
                .addLoreLine(SettingsManager.lang.getString("grind.info1")
                        .replace("%amount%", Integer.toString(PlayerStatsManager.getPlayerStats(playerName).getGrind())))
                .addLoreLine(SettingsManager.lang.getString("grind.info2"))
                .toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(2, 4);
    }
}
