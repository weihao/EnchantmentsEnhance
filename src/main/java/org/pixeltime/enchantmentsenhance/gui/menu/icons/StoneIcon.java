package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.MM;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class StoneIcon extends Clickable {

    public ItemStack getItem(int stoneId, Player player) {
        return new ItemBuilder(MM.stoneTypes.get(stoneId), (BackpackIcon.getOneStoneCountAsCount(player, stoneId))).setName(SettingsManager.lang.getString("Item." + stoneId)).addLoreLine(BackpackIcon.getOneStoneCountAsString(player,
                stoneId)).toItemStack();
    }

    public ItemStack getItem(int stoneId) {
        return new ItemBuilder(MM.stoneTypes.get(stoneId), (1)).setName(SettingsManager.lang.getString("Item." + stoneId)).addLoreLine(BackpackIcon.getOneStoneCountAsString(null, stoneId)).toItemStack();
    }

    public ItemStack getItem(ItemStack item, Player player) {
        int stoneId = Enhance.getStoneId(item, ItemManager.getItemEnchantLevel(item));
        return getItem(stoneId, player);
    }

    @Override
    public ItemStack getItem() {
        return null;
    }

    @Override
    public int getPosition() {
        return Util.getSlot(2, 4);
    }
}
