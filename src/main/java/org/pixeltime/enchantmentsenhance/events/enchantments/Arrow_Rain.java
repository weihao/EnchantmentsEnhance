package org.pixeltime.enchantmentsenhance.events.enchantments;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.ArrayList;

public class Arrow_Rain implements Listener {
    private ArrayList<Projectile> special;
    private ArrayList<Projectile> special2;
    private ArrayList<Projectile> special3;
    private ArrayList<Projectile> special4;
    private ArrayList<Projectile> special5;

    public Arrow_Rain() {
        this.special = new ArrayList<Projectile>();
        this.special2 = new ArrayList<Projectile>();
        this.special3 = new ArrayList<Projectile>();
        this.special4 = new ArrayList<Projectile>();
        this.special5 = new ArrayList<Projectile>();
    }

    @EventHandler
    public void onDamage(final ProjectileLaunchEvent projectileLaunchEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "arrow_rain"));
        if (projectileLaunchEvent.getEntity() instanceof Arrow) {
            final Arrow arrow = (Arrow) projectileLaunchEvent.getEntity();
            if (arrow.getShooter() instanceof Player) {
                final Player player = (Player) arrow.getShooter();
                try {
                    if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                        this.special.add(arrow);
                    }
                    if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " II")) {
                        this.special2.add(arrow);
                    }
                    if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " III")) {
                        this.special3.add(arrow);
                    }
                    if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " IV")) {
                        this.special4.add(arrow);
                    }
                    if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " V")) {
                        this.special5.add(arrow);
                    }
                } catch (Exception ex) {
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final int n = (int) (Math.random() * 100.0);
        if (entityDamageByEntityEvent.isCancelled()) {
            return;
        }
        if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.getEntity().getWorld()).getApplicableRegions(entityDamageByEntityEvent.getEntity().getLocation()).queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY) {
            return;
        }
        if (entityDamageByEntityEvent.getDamager() instanceof Projectile) {
            final Projectile projectile = (Projectile) entityDamageByEntityEvent.getDamager();
            if (this.special.contains(projectile) && n < SettingsManager.enchant.getInt("arrow_rain.level_I.chance")) {
                final Location add = entityDamageByEntityEvent.getEntity().getLocation().add(0.0, 20.0, 0.0);
                final ArrayList<Location> list = new ArrayList<Location>();
                for (int i = -2; i <= 2; ++i) {
                    for (int j = -2; j <= 2; ++j) {
                        list.add(add.clone().add((double) i, 0.0, (double) j));
                    }
                }
                for (final Location location : list) {
                    location.getWorld().spawnEntity(location, EntityType.ARROW);
                }
            }
            if (this.special2.contains(projectile) && n < SettingsManager.enchant.getInt("arrow_rain.level_II.chance")) {
                final Location add2 = entityDamageByEntityEvent.getEntity().getLocation().add(0.0, 20.0, 0.0);
                final ArrayList<Location> list2 = new ArrayList<Location>();
                for (int k = -3; k <= 2; ++k) {
                    for (int l = -3; l <= 2; ++l) {
                        list2.add(add2.clone().add((double) k, 0.0, (double) l));
                    }
                }
                for (final Location location2 : list2) {
                    location2.getWorld().spawnEntity(location2, EntityType.ARROW);
                }
            }
            if (this.special3.contains(projectile) && n < SettingsManager.enchant.getInt("arrow_rain.level_III.chance")) {
                final Location add3 = entityDamageByEntityEvent.getEntity().getLocation().add(0.0, 20.0, 0.0);
                final ArrayList<Location> list3 = new ArrayList<Location>();
                for (int n2 = -4; n2 <= 2; ++n2) {
                    for (int n3 = -4; n3 <= 2; ++n3) {
                        list3.add(add3.clone().add((double) n2, 0.0, (double) n3));
                    }
                }
                for (final Location location3 : list3) {
                    location3.getWorld().spawnEntity(location3, EntityType.ARROW);
                }
            }
            if (this.special4.contains(projectile) && n < SettingsManager.enchant.getInt("arrow_rain.level_IV.chance")) {
                final Location add4 = entityDamageByEntityEvent.getEntity().getLocation().add(0.0, 20.0, 0.0);
                final ArrayList<Location> list4 = new ArrayList<Location>();
                for (int n4 = -5; n4 <= 2; ++n4) {
                    for (int n5 = -5; n5 <= 2; ++n5) {
                        list4.add(add4.clone().add((double) n4, 0.0, (double) n5));
                    }
                }
                for (final Location location4 : list4) {
                    location4.getWorld().spawnEntity(location4, EntityType.ARROW);
                }
            }
            if (this.special5.contains(projectile) && n < SettingsManager.enchant.getInt("arrow_rain.level_V.chance")) {
                final Location add5 = entityDamageByEntityEvent.getEntity().getLocation().add(0.0, 20.0, 0.0);
                final ArrayList<Location> list5 = new ArrayList<Location>();
                for (int n6 = -6; n6 <= 2; ++n6) {
                    for (int n7 = -6; n7 <= 2; ++n7) {
                        list5.add(add5.clone().add((double) n6, 0.0, (double) n7));
                    }
                }
                for (final Location location5 : list5) {
                    location5.getWorld().spawnEntity(location5, EntityType.ARROW);
                }
            }
        }
    }
}
