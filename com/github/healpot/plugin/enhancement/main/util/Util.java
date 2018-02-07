package com.github.healpot.plugin.enhancement.main.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
}
