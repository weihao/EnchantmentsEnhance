package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.api.API;
import org.pixeltime.enchantmentsenhance.interfaces.Clickable;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.List;

public class ValksIcon extends Clickable {
    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.BOOKSHELF).setName(SettingsManager.lang.getString("Valks.gui")).toItemStack();
    }

    public ItemStack getItem(Player player) {
        List<Integer> temp = API.getValks(player);
        if (temp == null || temp.size() == 0) {
            return getItem();
        }
        return CompatibilityManager.glow.addGlow(getItem());
    }

    @Override
    public int getPosition() {
        return Util.getSlot(8, 1);
    }
}
