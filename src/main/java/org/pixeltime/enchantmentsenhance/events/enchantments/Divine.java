package org.pixeltime.enchantmentsenhance.events.enchantments;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Divine implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "divine"));
        if (entityDamageByEntityEvent.getDamager() instanceof Player && entityDamageByEntityEvent.getEntity() instanceof Player) {
            try {
                final Player player = (Player) entityDamageByEntityEvent.getDamager();
                if (entityDamageByEntityEvent.isCancelled()) {
                    return;
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.getEntity().getWorld()).getApplicableRegions(entityDamageByEntityEvent.getEntity().getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
                    return;
                }
                final int n = (int) (Math.random() * 100.0);
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("divine.level_I.chance")) {
                    if (player.getHealth() + entityDamageByEntityEvent.getDamage() > 20.0) {
                        player.setHealth(20.0);
                    } else {
                        player.setHealth(player.getHealth() + entityDamageByEntityEvent.getDamage());
                    }
                }
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("divine.level_II.chance")) {
                    if (player.getHealth() + entityDamageByEntityEvent.getDamage() > 20.0) {
                        player.setHealth(20.0);
                    } else {
                        player.setHealth(player.getHealth() + entityDamageByEntityEvent.getDamage());
                    }
                }
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("divine.level_III.chance")) {
                    if (player.getHealth() + entityDamageByEntityEvent.getDamage() > 20.0) {
                        player.setHealth(20.0);
                    } else {
                        player.setHealth(player.getHealth() + entityDamageByEntityEvent.getDamage());
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
