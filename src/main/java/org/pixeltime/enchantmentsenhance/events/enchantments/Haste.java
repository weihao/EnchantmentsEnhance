package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.pixeltime.enchantmentsenhance.manager.EnchantmentsManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Haste implements Listener {
    @EventHandler
    public void onWalk(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "haste"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 0));
                }
                if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1));
                }
                if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 2));
                }
                return;
            }
            if ((EnchantmentsManager.haste.contains(player.getName()) && player.getInventory().getItemInHand() == null) || !player.getInventory().getItemInHand().hasItemMeta() || !player.getInventory().getItemInHand().getItemMeta().hasLore() || (!player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && !player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && !player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III"))) {
                player.removePotionEffect(PotionEffectType.FAST_DIGGING);
                EnchantmentsManager.haste.remove(player.getName());
                return;
            }
            if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
                if (!EnchantmentsManager.haste.contains(player.getName())) {
                    EnchantmentsManager.haste.add(player.getName());
                }
            }
            if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1));
                if (!EnchantmentsManager.haste.contains(player.getName())) {
                    EnchantmentsManager.haste.add(player.getName());
                }
            }
            if (player.getInventory().getItemInHand() != null && player.getInventory().getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 2));
                if (!EnchantmentsManager.haste.contains(player.getName())) {
                    EnchantmentsManager.haste.add(player.getName());
                }
            }
        } catch (Exception ex) {
        }
    }
}
