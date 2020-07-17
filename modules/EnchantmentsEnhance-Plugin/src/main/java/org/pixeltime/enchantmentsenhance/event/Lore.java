package org.pixeltime.enchantmentsenhance.event;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.Arrays;
import java.util.List;

public class Lore {
    /**
     * Adds lore to the enhanced item.
     *
     * @param is
     * @param p
     * @param str
     * @param tradeable
     */
    public static void addLore(
            ItemStack is,
            Player p,
            String str,
            boolean tradeable,
            boolean sendMessage) {

        ItemManager.setSoulbound(is, true);
        ItemManager.setTradeable(is, tradeable);

//        if ((is.hasItemMeta()) && (is.getItemMeta().hasLore())) {
//            List<String> loreList = is.getItemMeta().getLore();
//            if (loreList.contains(lore)) {
//                if (SettingsManager.config.getBoolean(
//                        "lore.sendBoundingMessage") && sendMessage) {
//                    Util.sendMessage(SettingsManager.lang.getString(
//                            "messages.already" + x), p);
//                }
//                return;
//            }
//            loreList.remove(Util.UNIQUEID + Util.toColor(
//                    SettingsManager.lang.getString("lore." + y + "Lore")));
//            loreList.add(lore);
//            im.setLore(loreList);
//            is.setItemMeta(im);
//            if (SettingsManager.config.getBoolean(
//                    "lore.sendBoundingMessage") && sendMessage) {
//                Util.sendMessage(SettingsManager.lang.getString("messages.made"
//                        + x), p);
//            }
//            return;
//        }
//
//        im.setLore(Arrays.asList(lore));
//        is.setItemMeta(im);
//        p.updateInventory();
//        if (SettingsManager.config.getBoolean(
//                "lore.sendBoundingMessage") && sendMessage) {
//            Util.sendMessage(SettingsManager.lang.getString("messages.made"
//                    + x), p);
//        }
    }


    /**
     * removes the lore of an enhanced item.
     *
     * @param is
     */
    public static void removeLore(ItemStack is) {
        ItemManager.setTradeable(is, false);
        ItemManager.setSoulbound(is, false);
//        ItemMeta im = is.getItemMeta();
//        String x = Util.UNIQUEID + Util.toColor(
//                SettingsManager.lang.getString("lore.tradeableLore"));
//        String y = Util.UNIQUEID + Util.toColor(
//                SettingsManager.lang.getString("lore.untradeableLore"));
//        if ((is.hasItemMeta()) && (is.getItemMeta().hasLore())) {
//            List<String> loreList = is.getItemMeta().getLore();
//            if ((loreList.contains(x)) || (loreList.contains(y))) {
//                loreList.remove(x);
//                loreList.remove(y);
//            }
//            im.setLore(loreList);
//            is.setItemMeta(im);
//        }
    }

    public static void addLore(ItemStack item, String string, boolean un) {

    }
}
