package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.PlayerStatsManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.model.PlayerStat;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ItemIcon extends Clickable {

    public static String getOneStoneCountAsString(String player, int stoneId) {
        int count = player == null ? 0 : Main.getApi().getItem(player, stoneId);
        return (SettingsManager.lang.getString("item.listing").replaceAll(
                "%ITEM%", SettingsManager.lang.getString("item." + stoneId))
                .replaceAll("%COUNT%", String.valueOf(count)));
    }

    public static int getOneStoneCountAsInt(String player, int stoneId) {
        return Main.getApi().getItem(player, stoneId);
    }

    public static int getOneStoneCountAsCount(String player, int stoneId) {
        return (getOneStoneCountAsInt(player, stoneId) <= 0) ? 1 : getOneStoneCountAsInt(player, stoneId);
    }


    public ItemStack getItem() {
        return (new ItemBuilder(XMaterial.CHEST_MINECART.toBukkit())
                .setName(SettingsManager.lang.getString("item.gui"))
                .addLoreLine(SettingsManager.lang.getString("item.gui1"))
                .toItemStack());
    }

    @Override
    public ItemStack getItem(String player) {
        PlayerStat stat = PlayerStatsManager.getPlayerStats(player);
        if (stat != null && stat.getItems() != null) {
            for (int i : PlayerStatsManager.getPlayerStats(player).getItems()) {
                if (i > 0) {
                    return CompatibilityManager.glow.addGlow(getItem());
                }
            }
        }
        return getItem();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(7, 1);
    }

}
