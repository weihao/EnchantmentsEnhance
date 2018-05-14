package org.pixeltime.enchantmentsenhance.events.enchantments;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.pixeltime.enchantmentsenhance.manager.EnchantmentsManager;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

public class Thief implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "thief"));
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
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("thief.level_I.chance")) {
                    final double n2 = SettingsManager.enchant.getInt("thief.level_I.money-percent") / 100.0 * EnchantmentsManager.econ.getBalance((OfflinePlayer) player2);
                    EnchantmentsManager.econ.withdrawPlayer((OfflinePlayer) player2, n2);
                    EnchantmentsManager.econ.depositPlayer((OfflinePlayer) player, n2);
                }
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("thief.level_II.chance")) {
                    final double n3 = SettingsManager.enchant.getInt("thief.level_II.money-percent") / 100.0 * EnchantmentsManager.econ.getBalance((OfflinePlayer) player2);
                    EnchantmentsManager.econ.withdrawPlayer((OfflinePlayer) player2, n3);
                    EnchantmentsManager.econ.depositPlayer((OfflinePlayer) player, n3);
                }
                if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("thief.level_III.chance")) {
                    final double n4 = SettingsManager.enchant.getInt("thief.level_III.money-percent") / 100.0 * EnchantmentsManager.econ.getBalance((OfflinePlayer) player2);
                    EnchantmentsManager.econ.withdrawPlayer((OfflinePlayer) player2, n4);
                    EnchantmentsManager.econ.depositPlayer((OfflinePlayer) player, n4);
                }
            } catch (Exception ex) {
            }
        }
    }
}
