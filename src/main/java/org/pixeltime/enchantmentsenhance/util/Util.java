/*
 *     Copyright (C) 2017-Present HealPotion
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

package org.pixeltime.enchantmentsenhance.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.Hashtable;
import java.util.Random;

/**
 * Contains a varieties of utility.
 *
 * @author HealPotion
 * @version Feb 9, 2018
 */
public class Util {
    /**
     * Lists all the enhancement stone possible.
     */
    public static String pluginTag() {
        return ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("Config.pluginTag"));
    }

    /**
     * Calculate the inventory slot.
     *
     * @param x in-game x-cord, starting from 1 to 9.
     * @param y in-game y-cord, starting from 1 to 9.
     * @return returns the inventory slot.
     */
    public static int getSlot(int x, int y) {
        return (y * 9) - (9 - x) - 1;
    }


    /**
     * Checks if an item is a valid plugin generated item.
     *
     * @param item
     * @param displayName
     * @return
     */
    public static boolean isPluginItem(ItemStack item, String displayName) {
        if (item != null && item.hasItemMeta() && item.getItemMeta()
                .hasDisplayName()) {
            String itemName = item.getItemMeta().getDisplayName();
            String stripped = ChatColor.stripColor(itemName);
            if (!itemName.equals(stripped) && stripped.equals(ChatColor
                    .stripColor(displayName))) {
                return true;
            }
        }
        return false;
    }


    /**
     * Sends a colored message to a player.
     *
     * @param msg
     * @param player
     */
    public static void sendMessage(String msg, CommandSender player) {
        String message = ChatColor.translateAlternateColorCodes('&', msg);
        player.sendMessage(pluginTag() + message);
    }


    /**
     * Sends a colored message to a player.
     *
     * @param msg
     * @param player
     */
    public static void sendMessage(
            String msg,
            CommandSender player,
            boolean addPlugintag) {
        String message = ChatColor.translateAlternateColorCodes('&', msg);
        player.sendMessage(message);
    }


    /**
     * Translates color codes.
     *
     * @param str
     * @return
     */
    public static String toColor(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }


    /**
     * Gets a player as a Player entity.
     *
     * @param str
     * @return
     */
    public static Player getPlayer(String str) {
        return Bukkit.getServer().getPlayer(str);
    }


    /**
     * Gets a player's username as a string.
     *
     * @param player
     * @return
     */
    public static String getPlayerUsername(Player player) {
        return player.getName();
    }


    /**
     * Removes all the alphabet in a string.
     *
     * @param str
     * @return
     */
    public static int extractNumber(String str) {
        return Integer.parseInt(str.replaceAll("[^0-9]", ""));
    }


    /**
     * Make a string colorful.
     *
     * @param string
     * @return
     */
    public static String rainbowlize(String string) {
        int lastColor = 0;
        int currColor;
        String newString = "";
        String colors = "123456789abcde";
        for (int i = 0; i < string.length(); i++) {
            do {
                currColor = new Random().nextInt(colors.length() - 1) + 1;
            }
            while (currColor == lastColor);
            newString += ChatColor.RESET.toString() + ChatColor.getByChar(colors
                    .charAt(currColor)) + "" + string.charAt(i);
        }
        return newString;
    }


    /**
     * Creates a GUI button made of a wool.
     *
     * @param dc
     * @param name
     * @return
     */
    public static ItemStack createButton(DyeColor dc, String name) {
        ItemStack i = new Wool(dc).toItemStack(1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        i.setItemMeta(im);
        return i;
    }

    public static int romanToInt(String num) {
        Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();
        ht.put('I', 1);
        ht.put('X', 10);
        ht.put('V', 5);
        int intNum = 0;
        int prev = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            try {
                int temp = ht.get(num.charAt(i));
                if (temp < prev)
                    intNum -= temp;
                else
                    intNum += temp;
                prev = temp;
            } catch
                    (NullPointerException ex) {
                return Integer.parseInt(num);
            }

        }
        return intNum;
    }

    public static String intToRoman(int input) {
        if (input > 10) {
            return Integer.toString(input);
        }
        String s = "";
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }


    /**
     * Removes any underscores and capitalizes first letter of each word in the
     * string.
     *
     * @param s
     * @return
     */
    public static String format(String s) {
        if (!s.contains("_")) {
            return capitalize(s);
        }
        String[] j = s.split("_");

        String c = "";

        for (String f : j) {
            f = capitalize(f);
            c += c.equalsIgnoreCase("") ? f : " " + f;
        }
        return c;
    }

    /**
     * Capitalizes first letter of each word in the string.
     *
     * @param text
     * @return
     */
    public static String capitalize(String text) {
        String firstLetter = text.substring(0, 1).toUpperCase();
        String next = text.substring(1).toLowerCase();
        String capitalized = firstLetter + next;
        return capitalized;
    }
}
