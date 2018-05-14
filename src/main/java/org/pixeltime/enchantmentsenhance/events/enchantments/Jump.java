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

public class Jump implements Listener {
    @EventHandler
    public void onWalk(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "jump"));
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, SettingsManager.enchant.getInt("jump.level_I.potion_lvl") - 1));
                }
                if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, SettingsManager.enchant.getInt("jump.level_II.potion_lvl") - 1));
                }
                if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, SettingsManager.enchant.getInt("jump.level_III.potion_lvl") - 1));
                }
                return;
            }
            if ((EnchantmentsManager.jump.contains(player.getName()) && player.getInventory().getBoots() == null) || !player.getInventory().getBoots().hasItemMeta() || !player.getInventory().getBoots().getItemMeta().hasLore() || (!player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && !player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && !player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III"))) {
                player.removePotionEffect(PotionEffectType.JUMP);
                EnchantmentsManager.jump.remove(player.getName());
                return;
            }
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SettingsManager.enchant.getInt("jump.level_I.potion_lvl") - 1));
                if (!EnchantmentsManager.jump.contains(player.getName())) {
                    EnchantmentsManager.jump.add(player.getName());
                }
            }
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SettingsManager.enchant.getInt("jump.level_II.potion_lvl") - 1));
                if (!EnchantmentsManager.jump.contains(player.getName())) {
                    EnchantmentsManager.jump.add(player.getName());
                }
            }
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SettingsManager.enchant.getInt("jump.level_III.potion_lvl") - 1));
                if (!EnchantmentsManager.jump.contains(player.getName())) {
                    EnchantmentsManager.jump.add(player.getName());
                }
            }
        } catch (Exception ex) {
        }
    }
}
