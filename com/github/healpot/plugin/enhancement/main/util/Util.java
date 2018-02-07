package com.github.healpot.plugin.enhancement.main.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.github.healpot.plugin.enhancement.main.Main;
import com.github.healpot.plugin.enhancement.main.Permissions;
import com.github.healpot.plugin.enhancement.main.SettingsManager;

public class Util {

    public static Material[] stoneTypes = new Material[] { Material.GHAST_TEAR,
        Material.GOLD_NUGGET, Material.SUGAR, Material.GLOWSTONE_DUST };


    public static int getSlot(int x, int y) {
        return (y * 9) - (9 - x) - 1;
    }


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


    public static void renameItem(ItemStack item, int enchantLevel) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
            (SettingsManager.lang.getString("Name." + Integer.toString(
                enchantLevel)))));
        item.setItemMeta(im);
    }


    public static void sendMessage(String msg, CommandSender sender) {
        String message = ChatColor.translateAlternateColorCodes('&', msg);
        sender.sendMessage(message);
    }


    public static String toColor(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }


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

        Util.sendMessage(help, player);
    }
}
