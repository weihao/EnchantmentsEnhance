/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
        ItemMeta im = is.getItemMeta();
        String lore = Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', str);
        String x, y;
        if (tradeable) {
            x = "tradeable";
            y = "untradeable";
        } else {
            x = "untradeable";
            y = "tradeable";
        }

        if ((is.hasItemMeta()) && (is.getItemMeta().hasLore())) {
            List<String> loreList = is.getItemMeta().getLore();
            if (loreList.contains(lore)) {
                if (SettingsManager.config.getBoolean(
                        "lore.sendBoundingMessage") && sendMessage) {
                    Util.sendMessage(SettingsManager.lang.getString(
                            "messages.already" + x), p);
                }
                return;
            }
            loreList.remove(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                    SettingsManager.lang.getString("lore." + y + "Lore")));
            loreList.add(lore);
            im.setLore(loreList);
            is.setItemMeta(im);
            if (SettingsManager.config.getBoolean(
                    "lore.sendBoundingMessage") && sendMessage) {
                Util.sendMessage(SettingsManager.lang.getString("messages.made"
                        + x), p);
            }
            return;
        }

        im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
        p.updateInventory();
        if (SettingsManager.config.getBoolean(
                "lore.sendBoundingMessage") && sendMessage) {
            Util.sendMessage(SettingsManager.lang.getString("messages.made"
                    + x), p);
        }
    }


    public static void addLore(ItemStack is, String str, boolean tradeable) {
        String lore = Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', str);
        ItemMeta im = is.getItemMeta();

        String y;
        if (tradeable) {
            y = "untradeable";
        } else {
            y = "tradeable";
        }

        if ((is.hasItemMeta()) && (is.getItemMeta().hasLore())) {
            List<String> loreList = is.getItemMeta().getLore();
            if (loreList.contains(lore)) {
                return;
            }
            loreList.remove(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                    SettingsManager.lang.getString("lore." + y + "Lore")));
            loreList.add(lore);
            im.setLore(loreList);
            is.setItemMeta(im);
            return;
        }
        im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
    }


    /**
     * removes the lore of an enhanced item.
     *
     * @param is
     * @param p
     */
    public static void removeLore(ItemStack is, Player p) {
        ItemMeta im = is.getItemMeta();

        String x = Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("lore.tradeableLore"));
        String y = Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("lore.untradeableLore"));
        if ((is.hasItemMeta()) && (is.getItemMeta().hasLore())) {
            List<String> loreList = is.getItemMeta().getLore();
            if ((loreList.contains(x)) || (loreList.contains(y))) {
                loreList.remove(x);
                loreList.remove(y);
            }
            im.setLore(loreList);
            is.setItemMeta(im);
            Util.sendMessage(SettingsManager.lang.getString(
                    "messages.madeunbound"), p);
            return;
        }
        Util.sendMessage(SettingsManager.lang.getString(
                "messages.alreadyunbound"), p);
    }


    /**
     * removes the lore of an enhanced item.
     *
     * @param is
     */
    public static void removeLore(ItemStack is) {
        ItemMeta im = is.getItemMeta();
        String x = Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("lore.tradeableLore"));
        String y = Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("lore.untradeableLore"));
        if ((is.hasItemMeta()) && (is.getItemMeta().hasLore())) {
            List<String> loreList = is.getItemMeta().getLore();
            if ((loreList.contains(x)) || (loreList.contains(y))) {
                loreList.remove(x);
                loreList.remove(y);
            }
            im.setLore(loreList);
            is.setItemMeta(im);
        }
    }
}
