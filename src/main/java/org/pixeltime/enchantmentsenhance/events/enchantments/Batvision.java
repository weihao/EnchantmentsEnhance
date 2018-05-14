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

public class Batvision implements Listener {
    @EventHandler
    public void onWalk(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "batvision"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 0));
                    } else {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 300, 0));
                    }
                }
            } else {
                if ((EnchantmentsManager.bat.contains(player.getName()) && player.getInventory().getHelmet() == null) || !player.getInventory().getHelmet().hasItemMeta() || !player.getInventory().getHelmet().getItemMeta().hasLore() || !player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    EnchantmentsManager.bat.remove(player.getName());
                    return;
                }
                if (player.getInventory().getHelmet() == null || !player.getInventory().getHelmet().hasItemMeta() || !player.getInventory().getHelmet().getItemMeta().hasLore()) {
                    return;
                }
                if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
                    if (!EnchantmentsManager.bat.contains(player.getName())) {
                        EnchantmentsManager.bat.add(player.getName());
                    }
                }
            }
        } catch (Exception ex) {
        }
    }
}
