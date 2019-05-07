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

package org.pixeltime.enchantmentsenhance.util;

import com.lgou2w.ldk.bukkit.compatibility.DyeColors;
import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

/**
 * Contains a varieties of utility.
 */
public class Util {
    public static final String UNIQUEID = ChatColor.translateAlternateColorCodes('&', "&r&c&r&a&r&4&r&e&r&a&r&8&r&2&r&a&r&5&r&8&r&c&r&4&r&7&r&e&r&b&r&e");


    public static ItemStack randomWool() {
        ItemStack[] WOOL = {
                new ItemBuilder(XMaterial.BLACK_WOOL.toBukkit()).setDyeColor(DyeColors.BLACK.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.BLUE_WOOL.toBukkit()).setDyeColor(DyeColors.BLUE.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.BROWN_WOOL.toBukkit()).setDyeColor(DyeColors.BROWN.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.CYAN_WOOL.toBukkit()).setDyeColor(DyeColors.CYAN.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.LIGHT_GRAY_WOOL.toBukkit()).setDyeColor(DyeColors.LIGHT_GRAY.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.GREEN_WOOL.toBukkit()).setDyeColor(DyeColors.GREEN.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.LIGHT_BLUE_WOOL.toBukkit()).setDyeColor(DyeColors.LIGHT_BLUE.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.GRAY_WOOL.toBukkit()).setDyeColor(DyeColors.GRAY.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.LIME_WOOL.toBukkit()).setDyeColor(DyeColors.LIME.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.MAGENTA_WOOL.toBukkit()).setDyeColor(DyeColors.MAGENTA.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.ORANGE_WOOL.toBukkit()).setDyeColor(DyeColors.ORANGE.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.PINK_WOOL.toBukkit()).setDyeColor(DyeColors.PINK.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.PURPLE_WOOL.toBukkit()).setDyeColor(DyeColors.PURPLE.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.RED_WOOL.toBukkit()).setDyeColor(DyeColors.RED.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.WHITE_WOOL.toBukkit()).setDyeColor(DyeColors.WHITE.toBukkit()).toItemStack(),
                new ItemBuilder(XMaterial.YELLOW_WOOL.toBukkit()).setDyeColor(DyeColors.YELLOW.toBukkit()).toItemStack()
        };
        Random random = new Random();
        return WOOL[random.nextInt(WOOL.length)];
    }

    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }

    public static ItemStack getMainHand(Player player) {
        try {
            ItemStack item = player.getInventory().getItemInMainHand();
            return item;
        } catch (NoSuchMethodError ex) {
            ItemStack item = player.getInventory().getItemInHand();
            return item;
        }
    }

    /**
     * Lists all the enhancement stone possible.
     */
    public static String pluginTag() {
        return ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("config.pluginTag"));
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
    public static void sendMessage(String msg, String player) {
        Player p = Bukkit.getPlayer(player);
        String message = ChatColor.translateAlternateColorCodes('&', msg);
        if (p != null) {
            p.sendMessage(pluginTag() + message);
        }
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
        String temp = str;
        try {
            temp = ChatColor.translateAlternateColorCodes('&', str);
        } catch (NullPointerException ex) {
            // ?
        }
        return temp;
    }

    /**
     * Translates color codes.
     *
     * @param str
     * @return
     */
    public static String[] toColor(String... str) {
        String[] result = new String[str.length];
        for (int i = 0; i < str.length; i++) {
            result[i] = toColor(str[i]);
        }
        return result;
    }

    /**
     * Translates color codes.
     *
     * @param str
     * @return
     */
    public static List<String> toColor(List<String> str) {
        List<String> result = new ArrayList<>();
        for (String s : str) {
            result.add(toColor(s));
        }
        return result;
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
     * Make a string colorful.
     *
     * @param string
     * @return
     */
    public static String rainbowlizeCode(String string) {
        int lastColor = 0;
        int currColor;
        String newString = "";
        String colors = "123456789abcde";
        for (int i = 0; i < string.length(); i++) {
            do {
                currColor = new Random().nextInt(colors.length() - 1) + 1;
            }
            while (currColor == lastColor);
            newString += "&r" + "&" + colors.charAt(currColor) + string.charAt(i);
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
    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    public static String intToRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + intToRoman(number-l);
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

    public static boolean invFull(Player p) {
        return p.getInventory().firstEmpty() == -1;
    }


}
