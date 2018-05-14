package org.pixeltime.enchantmentsenhance.events.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Rekt implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getEntity() instanceof Player) {
            return;
        }
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "rekt"));
        if (entityDamageByEntityEvent.getDamager() instanceof Player) {
            try {
                final Player player = (Player) entityDamageByEntityEvent.getDamager();
                if (entityDamageByEntityEvent.isCancelled()) {
                    return;
                }
                if (player.getItemInHand().getItemMeta().hasLore() && player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    entityDamageByEntityEvent.setDamage(entityDamageByEntityEvent.getDamage() * 2.0);
                }
            } catch (Exception ex) {
            }
        }
    }
}
