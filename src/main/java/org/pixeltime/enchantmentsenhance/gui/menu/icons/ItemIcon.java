package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.api.API;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ItemIcon extends Clickable {

    public static String getOneStoneCountAsString(String player, int stoneId) {
        int count = player == null ? 0 : API.getItem(player, stoneId);
        return (SettingsManager.lang.getString("Item.listing").replaceAll(
                "%ITEM%", SettingsManager.lang.getString("Item." + stoneId))
                .replaceAll("%COUNT%", String.valueOf(count)));
    }

    public static int getOneStoneCountAsInt(String player, int stoneId) {
        return API.getItem(player, stoneId);
    }

    public static int getOneStoneCountAsCount(String player, int stoneId) {
        return (getOneStoneCountAsInt(player, stoneId) <= 0) ? 1 : getOneStoneCountAsInt(player, stoneId);
    }

    @Override
    public ItemStack getItem() {
        return CompatibilityManager.glow.addGlow(new ItemBuilder(Material.STORAGE_MINECART).setName(SettingsManager.lang.getString("Item.gui")).addLoreLine(SettingsManager.lang.getString("Item.gui1")).toItemStack());
    }

    public ItemStack getItem(String player) {
        for (int i : PlayerStat.getPlayerStats(player).getItems()) {
            if (i > 0) {
                return CompatibilityManager.glow.addGlow(getItem());
            }
        }
        return CompatibilityManager.glow.addGlow(getItem());
    }

    @Override
    public int getPosition() {
        return Util.getSlot(7, 1);
    }

}
