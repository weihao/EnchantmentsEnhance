package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Aegis implements Listener {
    @EventHandler
    public void block(final PlayerInteractEvent playerInteractEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "aegis"));
        final Player player = playerInteractEvent.getPlayer();
        final int n = (int) (Math.random() * 100.0);
        final ItemStack[] armorContents = player.getInventory().getArmorContents();
        if (playerInteractEvent.getAction() != Action.RIGHT_CLICK_AIR) {
            return;
        }
        try {
            if (player.getItemInHand().getType() == Material.DIAMOND_SWORD || player.getItemInHand().getType() == Material.IRON_SWORD || player.getItemInHand().getType() == Material.GOLD_SWORD || player.getItemInHand().getType() == Material.STONE_SWORD || player.getItemInHand().getType() == Material.WOOD_SWORD) {
                for (int length = armorContents.length, i = 0; i < length; ++i) {
                    final ItemStack itemStack = armorContents[i];
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("aegis.level_I.chance")) {
                        player.setHealth(player.getHealth() + 1.0);
                    }
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("aegis.level_II.chance")) {
                        player.setHealth(player.getHealth() + 1.0);
                    }
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("aegis.level_III.chance")) {
                        player.setHealth(player.getHealth() + 2.0);
                    }
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " IV") && n < SettingsManager.enchant.getInt("aegis.level_IV.chance")) {
                        player.setHealth(player.getHealth() + 2.0);
                    }
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() && itemStack.getItemMeta().getLore().contains("§7Aegis V") && n < SettingsManager.enchant.getInt("aegis.level_V.chance")) {
                        player.setHealth(player.getHealth() + 3.0);
                    }
                }
            }
        } catch (Exception ex) {
            // Unknown error.
        }
    }
}
