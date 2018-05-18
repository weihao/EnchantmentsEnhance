package org.pixeltime.enchantmentsenhance.event.enchantment;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.pixeltime.enchantmentsenhance.manager.EnchantmentsManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Speed implements Listener {
    @EventHandler
    public void onWalk(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "speed"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, SettingsManager.enchant.getInt("speed.level_I.potion_lvl") - 1));
                }
                if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, SettingsManager.enchant.getInt("speed.level_II.potion_lvl") - 1));
                }
                if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, SettingsManager.enchant.getInt("speed.level_III.potion_lvl") - 1));
                }
                return;
            }
            if ((EnchantmentsManager.speed.contains(player.getName()) && player.getInventory().getBoots() == null) || !player.getInventory().getBoots().hasItemMeta() || !player.getInventory().getBoots().getItemMeta().hasLore() || (!player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && !player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && !player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III"))) {
                player.removePotionEffect(PotionEffectType.SPEED);
                EnchantmentsManager.speed.remove(player.getName());
                return;
            }
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SettingsManager.enchant.getInt("speed.level_I.potion_lvl") - 1));
                if (!EnchantmentsManager.speed.contains(player.getName())) {
                    EnchantmentsManager.speed.add(player.getName());
                }
            }
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SettingsManager.enchant.getInt("speed.level_II.potion_lvl") - 1));
                if (!EnchantmentsManager.speed.contains(player.getName())) {
                    EnchantmentsManager.speed.add(player.getName());
                }
            }
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SettingsManager.enchant.getInt("speed.level_III.potion_lvl") - 1));
                if (!EnchantmentsManager.speed.contains(player.getName())) {
                    EnchantmentsManager.speed.add(player.getName());
                }
            }
        } catch (Exception ex) {
        }
    }
}
