package org.pixeltime.enchantmentsenhance.event.enchantment;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Stealth implements Listener {
    @EventHandler
    public void onSneak(final PlayerToggleSneakEvent playerToggleSneakEvent) {
        final Player player = playerToggleSneakEvent.getPlayer();
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "stealth"));
        if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
            return;
        }
        final int n = (int) (Math.random() * 100.0);
        final ItemStack[] armorContents = player.getInventory().getArmorContents();
        try {
            ItemStack[] array;
            for (int length = (array = armorContents).length, i = 0; i < length; ++i) {
                final ItemStack itemStack = array[i];
                if (itemStack != null) {
                    if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("stealth.level_I.chance")) {
                        final int int1 = SettingsManager.enchant.getInt("stealth.level_I.radius");
                        for (final Entity entity : player.getNearbyEntities((double) int1, (double) int1, (double) int1)) {
                            if (entity instanceof Player) {
                                ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("stealth.level_I.duration") * 20, 0));
                            }
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("stealth.level_II.chance")) {
                        final int int2 = SettingsManager.enchant.getInt("stealth.level_II.radius");
                        for (final Entity entity2 : player.getNearbyEntities((double) int2, (double) int2, (double) int2)) {
                            if (entity2 instanceof Player) {
                                ((Player) entity2).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("stealth.level_II.duration") * 20, 0));
                            }
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("stealth.level_III.chance")) {
                        final int int3 = SettingsManager.enchant.getInt("stealth.level_III.radius");
                        for (final Entity entity3 : player.getNearbyEntities((double) int3, (double) int3, (double) int3)) {
                            if (entity3 instanceof Player) {
                                ((Player) entity3).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("stealth.level_III.duration") * 20, 0));
                            }
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " IV") && n < SettingsManager.enchant.getInt("stealth.level_IV.chance")) {
                        final int int4 = SettingsManager.enchant.getInt("stealth.level_IV.radius");
                        for (final Entity entity4 : player.getNearbyEntities((double) int4, (double) int4, (double) int4)) {
                            if (entity4 instanceof Player) {
                                ((Player) entity4).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("stealth.level_IV.duration") * 20, 0));
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
    }
}
