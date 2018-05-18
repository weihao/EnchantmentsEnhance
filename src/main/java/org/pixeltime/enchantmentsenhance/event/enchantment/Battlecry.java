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
import org.bukkit.potion.PotionEffectType;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Battlecry implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getDamager() instanceof Player && entityDamageByEntityEvent.getEntity() instanceof Player) {
            final Player player = (Player) entityDamageByEntityEvent.getDamager();
            final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "battlecry"));
            if (entityDamageByEntityEvent.isCancelled()) {
                return;
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.getEntity().getWorld()).getApplicableRegions(entityDamageByEntityEvent.getEntity().getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
                return;
            }
            final int n = (int) (Math.random() * 100.0);
            try {
                Label_0291:
                {
                    if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                        if ((n >= SettingsManager.enchant.getInt("battlecry.level_I.chance") || !player.hasPotionEffect(PotionEffectType.POISON)) && !player.hasPotionEffect(PotionEffectType.CONFUSION) && !player.hasPotionEffect(PotionEffectType.WITHER) && !player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
                            if (!player.hasPotionEffect(PotionEffectType.SLOW)) {
                                break Label_0291;
                            }
                        }
                        try {
                            player.removePotionEffect(PotionEffectType.BLINDNESS);
                            player.removePotionEffect(PotionEffectType.POISON);
                            player.removePotionEffect(PotionEffectType.CONFUSION);
                            player.removePotionEffect(PotionEffectType.WEAKNESS);
                            player.removePotionEffect(PotionEffectType.SLOW);
                        } catch (Exception ex) {
                        }
                    }
                }
                Label_0455:
                {
                    if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                        if ((n >= SettingsManager.enchant.getInt("battlecry.level_II.chance") || !player.hasPotionEffect(PotionEffectType.POISON)) && !player.hasPotionEffect(PotionEffectType.CONFUSION) && !player.hasPotionEffect(PotionEffectType.WITHER) && !player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
                            if (!player.hasPotionEffect(PotionEffectType.SLOW)) {
                                break Label_0455;
                            }
                        }
                        try {
                            player.removePotionEffect(PotionEffectType.BLINDNESS);
                            player.removePotionEffect(PotionEffectType.POISON);
                            player.removePotionEffect(PotionEffectType.CONFUSION);
                            player.removePotionEffect(PotionEffectType.WEAKNESS);
                            player.removePotionEffect(PotionEffectType.SLOW);
                        } catch (Exception ex2) {
                        }
                    }
                }
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                    if ((n >= SettingsManager.enchant.getInt("battlecry.level_III.chance") || !player.hasPotionEffect(PotionEffectType.POISON)) && !player.hasPotionEffect(PotionEffectType.CONFUSION) && !player.hasPotionEffect(PotionEffectType.WITHER) && !player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
                        if (!player.hasPotionEffect(PotionEffectType.SLOW)) {
                            return;
                        }
                    }
                    try {
                        player.removePotionEffect(PotionEffectType.BLINDNESS);
                        player.removePotionEffect(PotionEffectType.POISON);
                        player.removePotionEffect(PotionEffectType.CONFUSION);
                        player.removePotionEffect(PotionEffectType.WEAKNESS);
                        player.removePotionEffect(PotionEffectType.SLOW);
                    } catch (Exception ex3) {
                    }
                }
            } catch (Exception ex4) {
            }
        }
    }
}
