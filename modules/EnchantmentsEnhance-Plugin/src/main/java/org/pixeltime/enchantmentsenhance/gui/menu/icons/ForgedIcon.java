package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ForgedIcon extends Clickable {
    @Override
    public ItemStack getItem(String playerName) {
        return null;
    }

    public ItemStack getItem(Player player, ItemStack item, Clickable clicked) {
        int enchantLevel = 0;
        if (clicked.equals(MainMenu.gear)) {
            enchantLevel = ItemManager.getItemEnchantLevel(item);
        } else if (clicked.equals(MainMenu.tool)) {
            enchantLevel = ItemManager.getToolEnchantLevel(item);
        }
        return ItemManager.forgeItemWithoutReplacement(player, item, enchantLevel + 1, true, clicked);
    }

    @Override
    public int getPosition() {
        return Util.getSlot(8, 3);
    }
}
