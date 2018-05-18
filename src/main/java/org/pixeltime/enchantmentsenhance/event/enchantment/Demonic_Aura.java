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
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Demonic_Aura implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "demonic_aura"));
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
                final ItemStack[] armorContents = player2.getInventory().getArmorContents();
                for (int length = armorContents.length, i = 0; i < length; ++i) {
                    final ItemStack itemStack = armorContents[i];
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && (int) (Math.random() * 100.0) < SettingsManager.enchant.getInt("demonic_aura.level_I.chance")) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, SettingsManager.enchant.getInt("demonic_aura.level_I.duration") * 20, 0));
                    }
                }
                for (int length2 = armorContents.length, j = 0; j < length2; ++j) {
                    final ItemStack itemStack2 = armorContents[j];
                    if (itemStack2 != null && itemStack2.hasItemMeta() && itemStack2.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && (int) (Math.random() * 100.0) < SettingsManager.enchant.getInt("demonic_aura.level_II.chance")) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, SettingsManager.enchant.getInt("demonic_aura.level_II.duration") * 20, 0));
                    }
                }
                for (int length3 = armorContents.length, k = 0; k < length3; ++k) {
                    final ItemStack itemStack3 = armorContents[k];
                    if (itemStack3 != null && itemStack3.hasItemMeta() && itemStack3.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && (int) (Math.random() * 100.0) < SettingsManager.enchant.getInt("demonic_aura.level_III.chance")) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, SettingsManager.enchant.getInt("demonic_aura.level_III.duration") * 20, 0));
                    }
                }
                for (int length4 = armorContents.length, l = 0; l < length4; ++l) {
                    final ItemStack itemStack4 = armorContents[l];
                    if (itemStack4 != null && itemStack4.hasItemMeta() && itemStack4.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " IV") && (int) (Math.random() * 100.0) < SettingsManager.enchant.getInt("demonic_aura.level_IV.chance")) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, SettingsManager.enchant.getInt("demonic_aura.level_IV.duration") * 20, 0));
                    }
                }
                for (int length5 = armorContents.length, n = 0; n < length5; ++n) {
                    final ItemStack itemStack5 = armorContents[n];
                    if (itemStack5 != null && itemStack5.hasItemMeta() && itemStack5.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " V") && (int) (Math.random() * 100.0) < SettingsManager.enchant.getInt("demonic_aura.level_V.chance")) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, SettingsManager.enchant.getInt("demonic_aura.level_V.duration") * 20, 0));
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
