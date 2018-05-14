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

public class Holy_Smite implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "holy_smite"));
        if (entityDamageByEntityEvent.getEntity() instanceof Player && entityDamageByEntityEvent.getDamager() instanceof Player) {
            try {
                final Player player = (Player) entityDamageByEntityEvent.getEntity();
                final Player player2 = (Player) entityDamageByEntityEvent.getDamager();
                if (entityDamageByEntityEvent.isCancelled()) {
                    return;
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
                    return;
                }
                final int n = (int) (Math.random() * 100.0);
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("holy_smite.level_I.chance")) {
                    player.getWorld().strikeLightningEffect(player.getLocation());
                    final Iterator iterator = player.getActivePotionEffects().iterator();
                    while (iterator.hasNext()) {
                        player.removePotionEffect(((PotionEffect) iterator.next()).getType());
                    }
                }
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("holy_smite.level_II.chance")) {
                    player.getWorld().strikeLightningEffect(player.getLocation());
                    final Iterator iterator2 = player.getActivePotionEffects().iterator();
                    while (iterator2.hasNext()) {
                        player.removePotionEffect(((PotionEffect) iterator2.next()).getType());
                    }
                }
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("holy_smite.level_III.chance")) {
                    player.getWorld().strikeLightningEffect(player.getLocation());
                    final Iterator iterator3 = player.getActivePotionEffects().iterator();
                    while (iterator3.hasNext()) {
                        player.removePotionEffect(((PotionEffect) iterator3.next()).getType());
                    }
                }
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " IV") && n < SettingsManager.enchant.getInt("holy_smite.level_IV.chance")) {
                    player.getWorld().strikeLightningEffect(player.getLocation());
                    final Iterator iterator4 = player.getActivePotionEffects().iterator();
                    while (iterator4.hasNext()) {
                        player.removePotionEffect(((PotionEffect) iterator4.next()).getType());
                    }
                }
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " V") && n < SettingsManager.enchant.getInt("holy_smite.level_V.chance")) {
                    player.getWorld().strikeLightningEffect(player.getLocation());
                    final Iterator iterator5 = player.getActivePotionEffects().iterator();
                    while (iterator5.hasNext()) {
                        player.removePotionEffect(((PotionEffect) iterator5.next()).getType());
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
