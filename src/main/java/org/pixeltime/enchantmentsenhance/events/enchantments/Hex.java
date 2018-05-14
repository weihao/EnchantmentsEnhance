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

public class Hex implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "hex"));
        if (entityDamageByEntityEvent.getDamager() instanceof Player && entityDamageByEntityEvent.getEntity() instanceof Player) {
            try {
                final Player player = (Player) entityDamageByEntityEvent.getDamager();
                final Player player2 = (Player) entityDamageByEntityEvent.getEntity();
                if (entityDamageByEntityEvent.isCancelled()) {
                    return;
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player2.getWorld()).getApplicableRegions(player2.getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
                    return;
                }
                final int n = (int) (Math.random() * 100.0);
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("hex.level_I.chance")) {
                    player2.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("hex.level_I.duration") * 20, 0));
                }
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("hex.level_II.chance")) {
                    player2.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("hex.level_II.duration") * 20, 0));
                }
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("hex.level_III.chance")) {
                    player2.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("hex.level_III.duration") * 20, 0));
                }
            } catch (Exception ex) {
            }
        }
    }
}
