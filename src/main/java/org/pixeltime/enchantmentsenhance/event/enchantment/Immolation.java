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
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Immolation implements Listener {
    @EventHandler
    public void onSneak(final PlayerToggleSneakEvent playerToggleSneakEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "immolation"));
        final Player player = playerToggleSneakEvent.getPlayer();
        final int n = (int) (Math.random() * 100.0);
        if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
            return;
        }
        try {
            if (player.getInventory().getChestplate().getItemMeta().hasLore() && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("immolation.level_I.chance")) {
                for (final Entity entity : player.getNearbyEntities(SettingsManager.enchant.getDouble("immolation.level_I.radius"), SettingsManager.enchant.getDouble("immolation.level_I.radius"), SettingsManager.enchant.getDouble("immolation.level_I.radius"))) {
                    if (entity instanceof Player) {
                        entity.setFireTicks(SettingsManager.enchant.getInt("immolation.level_I.duration") * 20);
                    }
                }
            }
            if (player.getInventory().getChestplate().getItemMeta().hasLore() && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("immolation.level_II.chance")) {
                for (final Entity entity2 : player.getNearbyEntities(SettingsManager.enchant.getDouble("immolation.level_II.radius"), SettingsManager.enchant.getDouble("immolation.level_II.radius"), SettingsManager.enchant.getDouble("immolation.level_II.radius"))) {
                    if (entity2 instanceof Player) {
                        entity2.setFireTicks(SettingsManager.enchant.getInt("immolation.level_II.duration") * 20);
                    }
                }
            }
            if (player.getInventory().getChestplate().getItemMeta().hasLore() && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("immolation.level_III.chance")) {
                for (final Entity entity3 : player.getNearbyEntities(SettingsManager.enchant.getDouble("immolation.level_III.radius"), SettingsManager.enchant.getDouble("immolation.level_III.radius"), SettingsManager.enchant.getDouble("immolation.level_III.radius"))) {
                    if (entity3 instanceof Player) {
                        entity3.setFireTicks(SettingsManager.enchant.getInt("immolation.level_III.duration") * 20);
                    }
                }
            }
            if (player.getInventory().getChestplate().getItemMeta().hasLore() && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " IV") && n < SettingsManager.enchant.getInt("immolation.level_IV.chance")) {
                for (final Entity entity4 : player.getNearbyEntities(SettingsManager.enchant.getDouble("immolation.level_IV.radius"), SettingsManager.enchant.getDouble("immolation.level_IV.radius"), SettingsManager.enchant.getDouble("immolation.level_IV.radius"))) {
                    if (entity4 instanceof Player) {
                        entity4.setFireTicks(SettingsManager.enchant.getInt("immolation.level_IV.duration") * 20);
                    }
                }
            }
            if (player.getInventory().getChestplate().getItemMeta().hasLore() && player.getInventory().getChestplate().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " V") && n < SettingsManager.enchant.getInt("immolation.level_V.chance")) {
                for (final Entity entity5 : player.getNearbyEntities(SettingsManager.enchant.getDouble("immolation.level_V.radius"), SettingsManager.enchant.getDouble("immolation.level_V.radius"), SettingsManager.enchant.getDouble("immolation.level_V.radius"))) {
                    if (entity5 instanceof Player) {
                        entity5.setFireTicks(SettingsManager.enchant.getInt("immolation.level_V.duration") * 20);
                    }
                }
            }
        } catch (Exception ex) {
        }
    }
}
