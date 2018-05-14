package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Reborn implements Listener {
    @EventHandler
    public void onDeath(final PlayerDeathEvent playerDeathEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "reborn"));
        final Player entity = playerDeathEvent.getEntity();
        if (entity.getKiller() instanceof Player) {
            final Player killer = entity.getKiller();
            try {
                if (killer.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, SettingsManager.enchant.getInt("reborn.level_I.absorption.duration") * 20, SettingsManager.enchant.getInt("reborn.level_I.absorption.potion_lvl") - 1));
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, SettingsManager.enchant.getInt("reborn.level_I.regeneration.duration") * 20, SettingsManager.enchant.getInt("reborn.level_I.regeneration.potion_lvl") - 1));
                }
                if (killer.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, SettingsManager.enchant.getInt("reborn.level_II.absorption.duration") * 20, SettingsManager.enchant.getInt("reborn.level_II.absorption.potion_lvl") - 1));
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, SettingsManager.enchant.getInt("reborn.level_II.regeneration.duration") * 20, SettingsManager.enchant.getInt("reborn.level_II.regeneration.potion_lvl") - 1));
                }
                if (killer.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, SettingsManager.enchant.getInt("reborn.level_III.absorption.duration") * 20, SettingsManager.enchant.getInt("reborn.level_III.absorption.potion_lvl") - 1));
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, SettingsManager.enchant.getInt("reborn.level_III.regeneration.duration") * 20, SettingsManager.enchant.getInt("reborn.level_III.regeneration.potion_lvl") - 1));
                }
            } catch (Exception ex) {
            }
        }
    }
}
