package org.pixeltime.enchantmentsenhance.event.enchantment;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.ArrayList;

public class Web_Trap implements Listener {
    private ArrayList<Block> temp;

    public Web_Trap() {
        this.temp = new ArrayList<Block>();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "web_trap"));
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
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I") && n < SettingsManager.enchant.getInt("web_trap.level_I.chance")) {
                    if (SettingsManager.enchant.getBoolean("web_trap.check-wg-flag") && WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).queryState(null, new StateFlag[]{DefaultFlag.BUILD}) == StateFlag.State.DENY) {
                        return;
                    }
                    final Location location = player.getLocation();
                    final Block block = location.getBlock();
                    this.temp.add(block);
                    location.getBlock().setType(Material.WEB);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getMain(), new BukkitRunnable() {
                        public void run() {
                            block.setType(Material.AIR);
                            Web_Trap.this.temp.clear();
                        }
                    }, (long) (SettingsManager.enchant.getInt("web_trap.level_I.duration") * 20));
                }
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II") && n < SettingsManager.enchant.getInt("web_trap.level_II.chance")) {
                    if (SettingsManager.enchant.getBoolean("web_trap.check-wg-flag") && WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).queryState(null, new StateFlag[]{DefaultFlag.BUILD}) == StateFlag.State.DENY) {
                        return;
                    }
                    final Location location2 = player.getLocation();
                    this.temp.add(location2.getBlock());
                    location2.getBlock().setType(Material.WEB);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getMain(), new BukkitRunnable() {
                        public void run() {
                            location2.getBlock().setType(Material.AIR);
                            Web_Trap.this.temp.clear();
                        }
                    }, (long) (SettingsManager.enchant.getInt("web_trap.level_II.duration") * 20));
                }
                if (player2.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III") && n < SettingsManager.enchant.getInt("web_trap.level_III.chance")) {
                    if (SettingsManager.enchant.getBoolean("web_trap.check-wg-flag") && WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).queryState(null, new StateFlag[]{DefaultFlag.BUILD}) == StateFlag.State.DENY) {
                        return;
                    }
                    final Location location3 = player.getLocation();
                    this.temp.add(location3.getBlock());
                    location3.getBlock().setType(Material.WEB);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getMain(), new BukkitRunnable() {
                        public void run() {
                            location3.getBlock().setType(Material.AIR);
                            Web_Trap.this.temp.clear();
                        }
                    }, (long) (SettingsManager.enchant.getInt("web_trap.level_III.duration") * 20));
                }
            } catch (Exception ex) {
            }
        }
    }
}
