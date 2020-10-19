package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.EnhanceManager;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.MaterialManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class StoneIcon extends Clickable {

    public ItemStack getItem(int stoneId, Player player) {
        return new ItemBuilder(MaterialManager.stoneTypes.get(stoneId),
                (ItemIcon.getOneStoneCountAsCount(player.getName(), stoneId) > 64
                        ? 64 : ItemIcon.getOneStoneCountAsCount(player.getName(), stoneId)))
                .setName(SettingsManager.lang.getString("item." + stoneId))
                .addLoreLine(ItemIcon.getOneStoneCountAsString(player.getName(), stoneId))
                .addLoreLine(SettingsManager.lang.getString("menu.leftInfo"))
                .addLoreLine(SettingsManager.lang.getString("menu.rightInfo"))
                .toItemStack();
    }

    public ItemStack getItem(int stoneId) {
        return new ItemBuilder(MaterialManager.stoneTypes.get(stoneId), (1)).setName(SettingsManager.lang.getString("item." + stoneId)).addLoreLine(ItemIcon.getOneStoneCountAsString(null, stoneId)).toItemStack();
    }

    public ItemStack getItem(ItemStack item, Player player, Clickable clicked) {
        int stoneId;
        if (clicked.equals(MainMenu.gear)) {
            stoneId = EnhanceManager.getStoneId(item, ItemManager.getItemEnchantLevel(item) + 1, clicked);
        } else if (clicked.equals(MainMenu.tool)) {
            stoneId = EnhanceManager.getStoneId(item, ItemManager.getToolEnchantLevel(item) + 1, clicked);
        } else {
            return null;
        }

        return getItem(stoneId, player);
    }

    @Override
    public ItemStack getItem(String playerName) {
        return null;
    }

    @Override
    public int getPosition() {
        return Util.getSlot(2, 4);
    }
}
