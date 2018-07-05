package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.event.blacksmith.Backpack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class BackpackIcon extends Clickable {

    public static String getOneStoneCountAsString(Player player, int stoneId) {
        int count = player == null ? 0 : Backpack.getPlayer(player)[stoneId];
        return (SettingsManager.lang.getString("Item.listing").replaceAll(
                "%ITEM%", SettingsManager.lang.getString("Item." + stoneId))
                .replaceAll("%COUNT%", String.valueOf(count)));
    }

    public static int getOneStoneCountAsInt(Player player, int stoneId) {
        return Backpack.getPlayer(player)[stoneId];
    }

    public static int getOneStoneCountAsCount(Player player, int stoneId) {
        return (Backpack.getPlayer(player)[stoneId] <= 0) ? 1 : Backpack.getPlayer(player)[stoneId];
    }

    @Override
    public ItemStack getItem() {
        return CompatibilityManager.glow.addGlow(new ItemBuilder(Material.STORAGE_MINECART).setName(SettingsManager.lang.getString("Item.gui")).addLoreLine(SettingsManager.lang.getString("Item.gui1")).toItemStack());
    }

    public ItemStack getItem(Player player) {
        for (int i : Backpack.getPlayer(player)) {
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
