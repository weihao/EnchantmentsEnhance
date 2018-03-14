package org.pixeltime.healpot.enhancement.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pixeltime.healpot.enhancement.manager.DataManager;
import org.pixeltime.healpot.enhancement.manager.Permissions;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;

/**
 * Contains a varieties of utility.
 * 
 * @author HealPot
 * @version Feb 9, 2018
 *
 */
public class Util {
    public static String pluginTag = ChatColor.translateAlternateColorCodes('&',
        SettingsManager.lang.getString("Config.pluginTag"));
    /**
     * Lists all the enhancement stone possible.
     */
    public static final Material[] stoneTypes = new Material[] {
        Material.GHAST_TEAR, Material.GOLD_NUGGET, Material.SUGAR,
        Material.GLOWSTONE_DUST };
    /**
     * Lists all the armor.
     */
    public static final Material[] armor = { Material.DIAMOND_HELMET,
        Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS,
        Material.DIAMOND_BOOTS, Material.IRON_HELMET, Material.IRON_CHESTPLATE,
        Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.GOLD_HELMET,
        Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS,
        Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE,
        Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS,
        Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE,
        Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS };
    /**
     * Lists all the weapon.
     */
    public static final Material[] weapon = { Material.DIAMOND_SWORD,
        Material.GOLD_SWORD, Material.STONE_SWORD, Material.WOOD_SWORD,
        Material.DIAMOND_AXE, Material.GOLD_AXE, Material.STONE_AXE,
        Material.WOOD_AXE, Material.IRON_SWORD, Material.IRON_AXE,
        Material.WOOD_AXE };
    /**
     * Lists all the sword.
     */
    public static final Material[] sword = { Material.DIAMOND_SWORD,
        Material.GOLD_SWORD, Material.WOOD_SWORD, Material.STONE_SWORD,
        Material.IRON_SWORD };
    /**
     * Lists all the axe.
     */
    public static final Material[] axe = { Material.DIAMOND_AXE,
        Material.IRON_AXE, Material.WOOD_AXE, Material.STONE_AXE,
        Material.GOLD_AXE };
    /**
     * Lists all the helmet.
     */
    public static final Material[] helmet = { Material.DIAMOND_HELMET,
        Material.GOLD_HELMET, Material.IRON_HELMET, Material.LEATHER_HELMET,
        Material.CHAINMAIL_HELMET };
    /**
     * Lists all the boot.
     */
    public static final Material[] boot = { Material.DIAMOND_BOOTS,
        Material.IRON_BOOTS, Material.GOLD_BOOTS, Material.LEATHER_BOOTS,
        Material.CHAINMAIL_BOOTS };
    /**
     * Lists all the chestplate.
     */
    public static final Material[] chestplate = { Material.DIAMOND_CHESTPLATE,
        Material.IRON_CHESTPLATE, Material.GOLD_CHESTPLATE,
        Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE };
    /**
     * Lists all the pickaxe.
     */
    public static final Material[] pick = { Material.DIAMOND_PICKAXE,
        Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.STONE_PICKAXE,
        Material.WOOD_PICKAXE };
    /**
     * Lists all the hoe.
     */
    public static final Material[] hoe = { Material.DIAMOND_HOE,
        Material.IRON_HOE, Material.GOLD_HOE, Material.STONE_HOE,
        Material.WOOD_HOE };


    /**
     * Calculate the inventory slot.
     * 
     * @param x
     *            in-game x-cord, starting from 1 to 9.
     * @param y
     *            in-game y-cord, starting from 1 to 9.
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
     * Renames an enhanced item.
     * 
     * @param item
     * @param enchantLevel
     */
    public static void renameItem(ItemStack item, int enchantLevel) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
            DataManager.name[enchantLevel]));
        item.setItemMeta(im);
    }


    /**
     * Sends a colored message to a CommandSender.
     * 
     * @param msg
     * @param sender
     */
    public static void sendMessage(String msg, CommandSender sender) {
        String message = ChatColor.translateAlternateColorCodes('&', msg);
        sender.sendMessage(pluginTag + message);
    }


    /**
     * Sends a colored message to a CommandSender.
     * 
     * @param msg
     * @param sender
     */
    public static void sendMessage(
        String msg,
        CommandSender sender,
        boolean addPlugintag) {
        String message = ChatColor.translateAlternateColorCodes('&', msg);
        sender.sendMessage(message);
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
     * Sends player a guide of all the commands that are available.
     * 
     * @param player
     */
    public static void printHelp(Player player) {
        String help = "&b&l&m          &d EnchantmentsEnhance&b&l&m          ";
        if (Permissions.commandHelp(player)) {
            help += "\n&6/enhance help &7- " + SettingsManager.lang.getString(
                "Help.help");
        }
        if (Permissions.commandEnhance(player)) {
            help += "\n&6/enhance menu &7- " + SettingsManager.lang.getString(
                "Help.menu");
            help += "\n&6/enhance inventory &7- " + SettingsManager.lang
                .getString("Help.inventory");
            help += "\n&6/enhance list &7- " + SettingsManager.lang.getString(
                "Help.list");
            help += "\n&6/enhance select &7- " + SettingsManager.lang.getString(
                "Help.select");
        }
        if (Permissions.commandLore(player)) {
            help += "\n&6/enhance lore &7- " + SettingsManager.lang.getString(
                "Help.lore");
        }
        if (Permissions.commandReload(player)) {
            help += "\n&6/enhance reload &7- " + SettingsManager.lang.getString(
                "Help.reload");
        }
        if (Permissions.commandVersion(player)) {
            help += "\n&6/enhance version &7- " + SettingsManager.lang
                .getString("Help.version");
        }
        if (Permissions.commandAdd(player)) {
            help += "\n&6/enhance add &7- " + SettingsManager.lang.getString(
                "Help.add");
        }

        Util.sendMessage(help, player, false);
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


    public static boolean isValid(ItemStack item, Material[] comparable) {
        for (int i = 0; i < comparable.length; i++) {
            if (comparable[i].equals(item.getType())) {
                return true;
            }
        }
        return false;
    }
}
