package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.pixeltime.enchantmentsenhance.manager.EnchantmentsManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Swimmer implements Listener {
    @EventHandler
    public void onWalk(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "swimmer"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, SettingsManager.enchant.getInt("swimmer.level_I.potion_lvl") - 1));
                }
                if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, SettingsManager.enchant.getInt("swimmer.level_II.potion_lvl") - 1));
                }
                if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, SettingsManager.enchant.getInt("swimmer.level_III.potion_lvl") - 1));
                }
                return;
            }
            if ((EnchantmentsManager.swimmer.contains(player.getName()) && player.getInventory().getHelmet() == null) || !player.getInventory().getHelmet().hasItemMeta() || !player.getInventory().getHelmet().getItemMeta().hasLore() || (!player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && !player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && !player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III"))) {
                player.removePotionEffect(PotionEffectType.WATER_BREATHING);
                EnchantmentsManager.swimmer.remove(player.getName());
                return;
            }
            if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, SettingsManager.enchant.getInt("swimmer.level_I.potion_lvl") - 1));
                if (!EnchantmentsManager.swimmer.contains(player.getName())) {
                    EnchantmentsManager.swimmer.add(player.getName());
                }
            }
            if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, SettingsManager.enchant.getInt("swimmer.level_II.potion_lvl") - 1));
                if (!EnchantmentsManager.swimmer.contains(player.getName())) {
                    EnchantmentsManager.swimmer.add(player.getName());
                }
            }
            if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, SettingsManager.enchant.getInt("swimmer.level_III.potion_lvl") - 1));
                if (!EnchantmentsManager.swimmer.contains(player.getName())) {
                    EnchantmentsManager.swimmer.add(player.getName());
                }
            }
        } catch (Exception ex) {
        }
    }
}
