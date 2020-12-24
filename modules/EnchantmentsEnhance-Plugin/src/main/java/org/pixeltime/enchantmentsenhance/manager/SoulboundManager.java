package org.pixeltime.enchantmentsenhance.manager;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.util.Util;

public class SoulboundManager {

    public static void addLore(
            ItemStack is,
            boolean tradeable
    ) {
        ItemManager.setSoulbound(is, true);
        ItemManager.setTradeable(is, tradeable);
    }

    /**
     * Adds lore to the enhanced item.
     *
     * @param is
     * @param p
     * @param tradeable
     */
    public static void addLore(
            ItemStack is,
            boolean tradeable,
            Player p
    ) {
        addLore(is, tradeable);
        if (SettingsManager.config.getBoolean(
                "lore.sendBoundingMessage")) {
            Util.sendMessage(SettingsManager.lang.getString("messages.made" + (tradeable ? "tradeable" : "untradeable")), p);
        }
    }


    /**
     * removes the lore of an enhanced item.
     *
     * @param is
     */
    public static void removeLore(ItemStack is) {
        ItemManager.setTradeable(is, false);
        ItemManager.setSoulbound(is, false);
    }

    /**
     * removes the lore of an enhanced item.
     *
     * @param is
     * @param p
     */
    public static void removeLore(ItemStack is, Player p) {
        ItemManager.setTradeable(is, false);
        ItemManager.setSoulbound(is, false);
        Util.sendMessage(SettingsManager.lang.getString(
                "messages.madeunbound"), p);
    }
}
