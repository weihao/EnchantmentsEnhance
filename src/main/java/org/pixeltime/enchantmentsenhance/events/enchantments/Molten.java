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

public class Molten implements Listener {
    @EventHandler
    public void onWalk(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "molten"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, SettingsManager.enchant.getInt("molten.level_I.potion_lvl") - 1));
                }
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, SettingsManager.enchant.getInt("molten.level_II.potion_lvl") - 1));
                }
                if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, SettingsManager.enchant.getInt("nolten.level_III.potion_lvl") - 1));
                }
                return;
            }
            if ((EnchantmentsManager.molten.contains(player.getName()) && player.getInventory().getChestplate() == null) || !player.getInventory().getChestplate().hasItemMeta() || !player.getInventory().getChestplate().getItemMeta().hasLore() || (!player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && !player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III"))) {
                player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                EnchantmentsManager.molten.remove(player.getName());
                return;
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, SettingsManager.enchant.getInt("molten.level_I.potion_lvl") - 1));
                if (!EnchantmentsManager.molten.contains(player.getName())) {
                    EnchantmentsManager.molten.add(player.getName());
                }
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, SettingsManager.enchant.getInt("molten.level_II.potion_lvl") - 1));
                if (!EnchantmentsManager.molten.contains(player.getName())) {
                    EnchantmentsManager.molten.add(player.getName());
                }
            }
            if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, SettingsManager.enchant.getInt("molten.level_III.potion_lvl") - 1));
                if (!EnchantmentsManager.molten.contains(player.getName())) {
                    EnchantmentsManager.molten.add(player.getName());
                }
            }
        } catch (Exception ex) {
        }
    }
}
