package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Angry_Birds implements Listener {
    @EventHandler
    public void onEntityDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "angry_birds"));
        if (entityDamageByEntityEvent.getEntity() instanceof Player && entityDamageByEntityEvent.getDamager() instanceof Player) {
            final Player player = (Player) entityDamageByEntityEvent.getEntity();
            final Player player2 = (Player) entityDamageByEntityEvent.getDamager();
            final int n = (int) (Math.random() * 100.0);
            if (player2.getItemInHand() != null && player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore()) {
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("angry_birds.level_I.chance")) {
                    player.setVelocity(new Vector(0, SettingsManager.enchant.getInt("angry_birds.level_I.height"), 0));
                }
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("angry_birds.level_II.chance")) {
                    player.setVelocity(new Vector(0, SettingsManager.enchant.getInt("angry_birds.level_II.height"), 0));
                }
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("angry_birds.level_III.chance")) {
                    player.setVelocity(new Vector(0, SettingsManager.enchant.getInt("angry_birds.level_III.height"), 0));
                }
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " IV") && n < SettingsManager.enchant.getInt("angry_birds.level_IV.chance")) {
                    player.setVelocity(new Vector(0, SettingsManager.enchant.getInt("angry_birds.level_IV.height"), 0));
                }
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " V") && n < SettingsManager.enchant.getInt("angry_birds.level_V.chance")) {
                    player.setVelocity(new Vector(0, SettingsManager.enchant.getInt("angry_birds.level_V.height"), 0));
                }
            }
        }
    }
}
