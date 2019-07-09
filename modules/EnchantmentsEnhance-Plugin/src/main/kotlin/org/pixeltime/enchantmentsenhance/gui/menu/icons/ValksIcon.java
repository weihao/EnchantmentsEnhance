package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.List;

public class ValksIcon extends Clickable {
    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(Material.BOOKSHELF).setName(SettingsManager.lang.getString("valks.gui")).toItemStack();
    }

    public ItemStack getItem(Player player) {
        List<Integer> temp = PlayerStat.getPlayerStats(player.getName()).getValks();
        if (temp == null || temp.size() == 0) {
            return getItem(player.getName());
        }
        return CompatibilityManager.glow.addGlow(getItem(player.getName()));
    }

    @Override
    public int getPosition() {
        return Util.getSlot(8, 1);
    }
}
