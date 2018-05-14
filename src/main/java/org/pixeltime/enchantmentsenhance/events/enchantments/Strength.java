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

public class Strength implements Listener {
    @EventHandler
    public void onWalk(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "strength"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, SettingsManager.enchant.getInt("strength.level_I.potion_lvl") - 1));
                    if (!EnchantmentsManager.strength.contains(player.getName())) {
                        EnchantmentsManager.strength.add(player.getName());
                    }
                }
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, SettingsManager.enchant.getInt("strength.level_II.potion_lvl") - 1));
                    if (!EnchantmentsManager.strength.contains(player.getName())) {
                        EnchantmentsManager.strength.add(player.getName());
                    }
                }
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, SettingsManager.enchant.getInt("strength.level_III.potion_lvl") - 1));
                    if (!EnchantmentsManager.strength.contains(player.getName())) {
                        EnchantmentsManager.strength.add(player.getName());
                    }
                }
                return;
            }
            if ((EnchantmentsManager.strength.contains(player.getName()) && player.getInventory().getChestplate() == null) || !player.getInventory().getChestplate().hasItemMeta() || !player.getInventory().getChestplate().getItemMeta().hasLore() || (!player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III"))) {
                player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                EnchantmentsManager.strength.remove(player.getName());
                return;
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, SettingsManager.enchant.getInt("strength.level_I.potion_lvl") - 1));
                if (!EnchantmentsManager.strength.contains(player.getName())) {
                    EnchantmentsManager.strength.add(player.getName());
                }
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, SettingsManager.enchant.getInt("strength.level_II.potion_lvl") - 1));
                if (!EnchantmentsManager.strength.contains(player.getName())) {
                    EnchantmentsManager.strength.add(player.getName());
                }
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, SettingsManager.enchant.getInt("strength.level_III.potion_lvl") - 1));
                if (!EnchantmentsManager.strength.contains(player.getName())) {
                    EnchantmentsManager.strength.add(player.getName());
                }
            }
        } catch (Exception ex) {
        }
    }
}
