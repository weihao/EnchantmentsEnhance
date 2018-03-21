package org.pixeltime.healpot.enhancement.events.blackspirit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.Util;

public class Lore {
    /**
     * Adds lore to the enhanced item.
     * 
     * @param is
     * @param p
     * @param lore
     * @param tradeable
     */
    public static void addLore(
        ItemStack is,
        Player p,
        String lore,
        boolean tradeable) {
        ItemMeta im = is.getItemMeta();
        List<String> loreList = new ArrayList<String>();

        String x = null;
        String y = null;
        if (tradeable) {
            x = "Tradeable";
            y = "Untradeable";
        }
        else {
            x = "Untradeable";
            y = "Tradeable";
        }

        if ((is.hasItemMeta()) && (is.getItemMeta().hasLore())) {
            int loreSize = is.getItemMeta().getLore().size();
            for (int i = 0; i < loreSize; i++) {
                loreList.add(ChatColor.translateAlternateColorCodes('&',
                    (String)is.getItemMeta().getLore().get(i)));
            }
            if (loreList.contains(lore)) {
                if (SettingsManager.config.getBoolean(
                    "lore.sendBoundingMessage")) {
                    Util.sendMessage(SettingsManager.lang.getString(
                        "Messages.Already" + x), p);
                }
                return;
            }
            if (loreList.contains(ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("Lore." + y + "Lore")))) {
                loreList.remove(ChatColor.translateAlternateColorCodes('&',
                    SettingsManager.lang.getString("Lore." + y + "Lore")));
            }
            loreList.add(lore);
            im.setLore(loreList);
            is.setItemMeta(im);
            if (SettingsManager.config.getBoolean("lore.sendBoundingMessage")) {
                Util.sendMessage(SettingsManager.lang.getString("Messages.Made"
                    + x), p);
            }
            return;
        }
        im.setLore(Arrays.asList(new String[] { lore }));
        is.setItemMeta(im);
        p.updateInventory();
        if (SettingsManager.config.getBoolean("lore.sendBoundingMessage")) {
            Util.sendMessage(SettingsManager.lang.getString("Messages.Made"
                + x), p);
        }
    }


    public static void addLore(ItemStack is, String lore, boolean tradeable) {
        ItemMeta im = is.getItemMeta();
        List<String> loreList = new ArrayList<String>();

        String y = null;
        if (tradeable) {
            y = "Untradeable";
        }
        else {
            y = "Tradeable";
        }

        if ((is.hasItemMeta()) && (is.getItemMeta().hasLore())) {
            int loreSize = is.getItemMeta().getLore().size();
            for (int i = 0; i < loreSize; i++) {
                loreList.add(ChatColor.translateAlternateColorCodes('&',
                    (String)is.getItemMeta().getLore().get(i)));
            }
            if (loreList.contains(lore)) {
                return;
            }
            if (loreList.contains(ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("Lore." + y + "Lore")))) {
                loreList.remove(ChatColor.translateAlternateColorCodes('&',
                    SettingsManager.lang.getString("Lore." + y + "Lore")));
            }
            loreList.add(lore);
            im.setLore(loreList);
            is.setItemMeta(im);
            return;
        }
        im.setLore(Arrays.asList(new String[] { lore }));
        is.setItemMeta(im);
    }


    /**
     * removes the lore of an enhanced item.
     * 
     * @param is
     * @param p
     * @param lang
     */
    public static void removeLore(
        ItemStack is,
        Player p,
        FileConfiguration lang) {
        ItemMeta im = is.getItemMeta();
        List<String> loreList = new ArrayList<String>();
        String x = ChatColor.translateAlternateColorCodes('&', lang.getString(
            "Lore.TradeableLore"));
        String y = ChatColor.translateAlternateColorCodes('&', lang.getString(
            "Lore.UntradeableLore"));
        if ((is.hasItemMeta()) && (is.getItemMeta().hasLore())) {
            int loreSize = is.getItemMeta().getLore().size();
            for (int i = 0; i < loreSize; i++) {
                loreList.add(ChatColor.translateAlternateColorCodes('&',
                    (String)is.getItemMeta().getLore().get(i)));
            }
            if ((loreList.contains(x)) || (loreList.contains(y))) {
                loreList.remove(x);
                loreList.remove(y);
            }
            im.setLore(loreList);
            is.setItemMeta(im);
            Util.sendMessage(lang.getString("Messages.MadeUnbound"), p);
            return;
        }
        Util.sendMessage(lang.getString("Messages.AlreadyUnbound"), p);
    }
}
