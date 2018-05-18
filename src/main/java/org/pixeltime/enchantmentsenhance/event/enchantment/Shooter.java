package org.pixeltime.enchantmentsenhance.event.enchantment;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Shooter implements Listener {
    private HashMap<Projectile, String> temp;
    private ArrayList<UUID> cooldown;

    public Shooter() {
        this.temp = new HashMap<Projectile, String>();
        this.cooldown = new ArrayList<UUID>();
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent playerInteractEvent) {
        if (playerInteractEvent.getAction() != Action.RIGHT_CLICK_AIR && playerInteractEvent.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "shooter"));
        final Player player = playerInteractEvent.getPlayer();
        if (this.cooldown.contains(player.getUniqueId())) {
            return;
        }
        if (player.getItemInHand() != null && player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore() && player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
            if (SettingsManager.enchant.getString("shooter.level_I.projectile").equalsIgnoreCase("snowball")) {
                final Snowball throwSnowball = player.throwSnowball();
                throwSnowball.setVelocity(player.getEyeLocation().getDirection().multiply(4));
                this.temp.put(throwSnowball, player.getName());
                if (SettingsManager.enchant.getBoolean("shooter.level_I.enable-cooldown")) {
                    final int n = SettingsManager.enchant.getInt("shooter.level_I.cooldown") * 20;
                    this.cooldown.add(player.getUniqueId());
                    new BukkitRunnable() {
                        public void run() {
                            Shooter.this.cooldown.remove(player.getUniqueId());
                        }
                    }.runTaskLater(Main.getMain(), (long) n);
                }
            } else if (SettingsManager.enchant.getString("shooter.level_I.projectile").equalsIgnoreCase("fireball")) {
                final Fireball fireball = (Fireball) player.launchProjectile((Class) Fireball.class);
                fireball.setIsIncendiary(false);
                fireball.setYield(0.0f);
                fireball.setVelocity(player.getEyeLocation().getDirection().multiply(4));
                this.temp.put(fireball, player.getName());
                if (SettingsManager.enchant.getBoolean("shooter.level_I.enable-cooldown")) {
                    final int n2 = SettingsManager.enchant.getInt("shooter.level_I.cooldown") * 20;
                    this.cooldown.add(player.getUniqueId());
                    new BukkitRunnable() {
                        public void run() {
                            Shooter.this.cooldown.remove(player.getUniqueId());
                        }
                    }.runTaskLater(Main.getMain(), (long) n2);
                }
            } else {
                if (!SettingsManager.enchant.getString("shooter.level_I.projectile").equalsIgnoreCase("egg")) {
                    return;
                }
                final Egg egg = (Egg) player.launchProjectile((Class) Egg.class);
                egg.setVelocity(player.getEyeLocation().getDirection().multiply(4));
                this.temp.put(egg, player.getName());
                if (SettingsManager.enchant.getBoolean("shooter.level_I.enable-cooldown")) {
                    final int n3 = SettingsManager.enchant.getInt("shooter.level_I.cooldown") * 20;
                    this.cooldown.add(player.getUniqueId());
                    new BukkitRunnable() {
                        public void run() {
                            Shooter.this.cooldown.remove(player.getUniqueId());
                        }
                    }.runTaskLater(Main.getMain(), (long) n3);
                }
            }
        }
    }

    @EventHandler
    public void onHit(final ProjectileHitEvent projectileHitEvent) {
        final Projectile entity = projectileHitEvent.getEntity();
        if (this.temp.containsKey(entity)) {
            final Player player = Bukkit.getPlayer(this.temp.get(entity));
            if (player == null) {
                return;
            }
            final BlockIterator blockIterator = new BlockIterator(projectileHitEvent.getEntity().getWorld(), projectileHitEvent.getEntity().getLocation().toVector(), projectileHitEvent.getEntity().getVelocity().normalize(), 0.0, 4);
            Block next = null;
            while (blockIterator.hasNext()) {
                next = blockIterator.next();
                if (next.getTypeId() != 0) {
                    break;
                }
            }
            final BlockBreakEvent blockBreakEvent = new BlockBreakEvent(next, player);
            Bukkit.getPluginManager().callEvent(blockBreakEvent);
            if (!blockBreakEvent.isCancelled()) {
                next.breakNaturally();
                this.temp.remove(entity);
            }
        }
    }
}
