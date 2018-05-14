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
import org.bukkit.potion.PotionEffect;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.Iterator;

public class Purge implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "purge"));
        if (entityDamageByEntityEvent.getDamager() instanceof Player && entityDamageByEntityEvent.getEntity() instanceof Player) {
            try {
                final Player player = (Player) entityDamageByEntityEvent.getDamager();
                final Player player2 = (Player) entityDamageByEntityEvent.getEntity();
                final int n = (int) (Math.random() * 100.0);
                if (entityDamageByEntityEvent.isCancelled()) {
                    return;
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player2.getWorld()).getApplicableRegions(player2.getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
                    return;
                }
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("purge.level_I.chance")) {
                    player2.getWorld().strikeLightningEffect(player2.getLocation());
                    final Iterator iterator = player2.getActivePotionEffects().iterator();
                    while (iterator.hasNext()) {
                        player2.removePotionEffect(((PotionEffect) iterator.next()).getType());
                        player2.damage(2.0);
                    }
                }
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("purge.level_II.chance")) {
                    player2.getWorld().strikeLightningEffect(player2.getLocation());
                    final Iterator iterator2 = player2.getActivePotionEffects().iterator();
                    while (iterator2.hasNext()) {
                        player2.removePotionEffect(((PotionEffect) iterator2.next()).getType());
                        player2.damage(2.0);
                    }
                }
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("purge.level_III.chance")) {
                    player2.getWorld().strikeLightningEffect(player2.getLocation());
                    final Iterator iterator3 = player2.getActivePotionEffects().iterator();
                    while (iterator3.hasNext()) {
                        player2.removePotionEffect(((PotionEffect) iterator3.next()).getType());
                        player2.damage(3.0);
                    }
                }
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " IV") && n < SettingsManager.enchant.getInt("purge.level_IV.chance")) {
                    player2.getWorld().strikeLightningEffect(player2.getLocation());
                    final Iterator iterator4 = player2.getActivePotionEffects().iterator();
                    while (iterator4.hasNext()) {
                        player2.removePotionEffect(((PotionEffect) iterator4.next()).getType());
                        player2.damage(4.0);
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
