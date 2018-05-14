package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Suicide implements Listener {
    @EventHandler
    public void onDeath(final PlayerDeathEvent playerDeathEvent) {
        final Player entity = playerDeathEvent.getEntity();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "suicide"));
        final int n = (int) (Math.random() * 100.0);
        if (entity.getInventory().getChestplate() != null && entity.getInventory().getChestplate().hasItemMeta() && entity.getInventory().getChestplate().getItemMeta().hasLore() && entity.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("suicide.level_I.chance")) {
            entity.getWorld().createExplosion(entity.getLocation(), (float) SettingsManager.enchant.getInt("suicide.level_I.power"));
        }
    }
}
