package org.pixeltime.enchantmentsenhance.event.enchantment;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Blessed implements Listener {
    @EventHandler
    public void onMove(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "blessed"));
        final int n = (int) (Math.random() * 100.0);
        try {
            final ItemStack[] armorContents = playerMoveEvent.getPlayer().getInventory().getArmorContents();
            ItemStack[] array;
            for (int length = (array = armorContents).length, i = 0; i < length; ++i) {
                final ItemStack itemStack = array[i];
                if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("blessed.level_I.chance")) {
                    player.setHealth(player.getMaxHealth());
                    player.setFoodLevel(20);
                }
            }
            ItemStack[] array2;
            for (int length2 = (array2 = armorContents).length, j = 0; j < length2; ++j) {
                final ItemStack itemStack2 = array2[j];
                if (itemStack2 != null && itemStack2.hasItemMeta() && itemStack2.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("blessed.level_II.chance")) {
                    player.setFoodLevel(20);
                    player.setHealth(player.getMaxHealth());
                }
            }
            ItemStack[] array3;
            for (int length3 = (array3 = armorContents).length, k = 0; k < length3; ++k) {
                final ItemStack itemStack3 = array3[k];
                if (itemStack3 != null && itemStack3.hasItemMeta() && itemStack3.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("blessed.level_III.chance")) {
                    player.setFoodLevel(20);
                    player.setHealth(player.getMaxHealth());
                }
            }
        } catch (Exception ex) {
        }
    }
}
