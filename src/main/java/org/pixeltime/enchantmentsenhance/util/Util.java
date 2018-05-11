package org.pixeltime.enchantmentsenhance.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.Random;

/**
 * Contains a varieties of utility.
 *
 * @author HealPot
 * @version Feb 9, 2018
 */
public class Util {
    /**
     * Lists all the enhancement stone possible.
     */
    public static final Material[] stoneTypes = new Material[]{
            Material.GHAST_TEAR, Material.GOLD_NUGGET, Material.SUGAR,
            Material.GLOWSTONE_DUST};
    /**
     * Lists all the armor.
     */
    public static final Material[] armor = {Material.DIAMOND_HELMET,
            Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS, Material.IRON_HELMET, Material.IRON_CHESTPLATE,
            Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.GOLD_HELMET,
            Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS,
            Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE,
            Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS,
            Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE,
            Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS};
    /**
     * Lists all the weapon.
     */
    public static final Material[] weapon = {Material.DIAMOND_SWORD,
            Material.GOLD_SWORD, Material.STONE_SWORD, Material.WOOD_SWORD,
            Material.DIAMOND_AXE, Material.GOLD_AXE, Material.STONE_AXE,
            Material.WOOD_AXE, Material.IRON_SWORD, Material.IRON_AXE,
            Material.WOOD_AXE};
    /**
     * Lists all the sword.
     */
    public static final Material[] sword = {Material.DIAMOND_SWORD,
            Material.GOLD_SWORD, Material.WOOD_SWORD, Material.STONE_SWORD,
            Material.IRON_SWORD};
    /**
     * Lists all the axe.
     */
    public static final Material[] axe = {Material.DIAMOND_AXE,
            Material.IRON_AXE, Material.WOOD_AXE, Material.STONE_AXE,
            Material.GOLD_AXE};
    /**
     * Lists all the helmet.
     */
    public static final Material[] helmet = {Material.DIAMOND_HELMET,
            Material.GOLD_HELMET, Material.IRON_HELMET, Material.LEATHER_HELMET,
            Material.CHAINMAIL_HELMET};
    /**
     * Lists all the boot.
     */
    public static final Material[] boot = {Material.DIAMOND_BOOTS,
            Material.IRON_BOOTS, Material.GOLD_BOOTS, Material.LEATHER_BOOTS,
            Material.CHAINMAIL_BOOTS};
    /**
     * Lists all the chestplate.
     */
    public static final Material[] chestplate = {Material.DIAMOND_CHESTPLATE,
            Material.IRON_CHESTPLATE, Material.GOLD_CHESTPLATE,
            Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE};
    /**
     * Lists all the pickaxe.
     */
    public static final Material[] pick = {Material.DIAMOND_PICKAXE,
            Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.STONE_PICKAXE,
            Material.WOOD_PICKAXE};
    /**
     * Lists all the hoe.
     */
    public static final Material[] hoe = {Material.DIAMOND_HOE,
            Material.IRON_HOE, Material.GOLD_HOE, Material.STONE_HOE,
            Material.WOOD_HOE};
    public static String pluginTag = ChatColor.translateAlternateColorCodes('&',
            SettingsManager.lang.getString("Config.pluginTag"));

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
     * @param sender
     */
    public static void sendMessage(String msg, CommandSender player) {
        String message = ChatColor.translateAlternateColorCodes('&', msg);
        player.sendMessage(pluginTag + message);
    }


    /**
     * Sends a colored message to a player.
     *
     * @param msg
     * @param sender
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
}
