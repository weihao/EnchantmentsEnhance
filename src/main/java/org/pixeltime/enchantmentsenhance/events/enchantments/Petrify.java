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
import org.bukkit.potion.PotionEffectType;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Petrify implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onHit(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "petrify"));
        if (entityDamageByEntityEvent.getEntity() instanceof Player && entityDamageByEntityEvent.getDamager() instanceof Player) {
            try {
                final Player player = (Player) entityDamageByEntityEvent.getEntity();
                final Player player2 = (Player) entityDamageByEntityEvent.getDamager();
                final int n = (int) (Math.random() * 100.0);
                if (entityDamageByEntityEvent.isCancelled()) {
                    return;
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
                    return;
                }
                if (player2.getItemInHand().getItemMeta().hasLore() && player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("petrify.level_I.chance")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("petrify.level_I.duration") * 20, SettingsManager.enchant.getInt("petrify.level_I.potion_lvl") - 1));
                }
                if (player2.getItemInHand().getItemMeta().hasLore() && player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("petrify.level_II.chance")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("petrify.level_II.duration") * 20, SettingsManager.enchant.getInt("petrify.level_II.potion_lvl") - 1));
                }
                if (player2.getItemInHand().getItemMeta().hasLore() && player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("petrify.level_III.chance")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("petrify.level_III.duration") * 20, SettingsManager.enchant.getInt("petrify.level_III.potion_lvl") - 1));
                }
            } catch (Exception ex) {
            }
        }
    }
}
