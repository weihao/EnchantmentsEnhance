package org.pixeltime.enchantmentsenhance.event.enchantment;

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

public class Curse implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "curse"));
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
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("curse.level_I.chance")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, SettingsManager.enchant.getInt("curse.level_I.duration") * 20, SettingsManager.enchant.getInt("curse.level_I.potion_lvl") - 1));
                }
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("curse.level_II.chance")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, SettingsManager.enchant.getInt("curse.level_II.duration") * 20, SettingsManager.enchant.getInt("curse.level_II.potion_lvl") - 1));
                }
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("curse.level_III.chance")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, SettingsManager.enchant.getInt("curse.level_III.duration") * 20, SettingsManager.enchant.getInt("curse.level_III.potion_lvl") - 1));
                }
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " IV") && n < SettingsManager.enchant.getInt("curse.level_IV.chance")) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, SettingsManager.enchant.getInt("curse.level_IV.duration") * 20, SettingsManager.enchant.getInt("curse.level_IV.potion_lvl") - 1));
                }
            } catch (Exception ex) {
            }
        }
    }
}
