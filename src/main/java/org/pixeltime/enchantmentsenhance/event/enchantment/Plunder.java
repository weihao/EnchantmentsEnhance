package org.pixeltime.enchantmentsenhance.event.enchantment;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Plunder implements Listener {
    @EventHandler
    public void onDeath(final EntityDeathEvent entityDeathEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "plunder"));
        if (entityDeathEvent.getEntity().getKiller() instanceof Player && !(entityDeathEvent.getEntity() instanceof Player)) {
            final Player killer = entityDeathEvent.getEntity().getKiller();
            if (killer.getItemInHand() != null && killer.getItemInHand().hasItemMeta() && killer.getItemInHand().getItemMeta().hasLore() && killer.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                entityDeathEvent.setDroppedExp(20);
            }
        }
    }
}
