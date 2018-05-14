package org.pixeltime.enchantmentsenhance.events.enchantments;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.ArrayList;

public class Zeus implements Listener {
    private ArrayList<Projectile> special;
    private ArrayList<Projectile> special2;
    private ArrayList<Projectile> special3;

    public Zeus() {
        this.special = new ArrayList<Projectile>();
        this.special2 = new ArrayList<Projectile>();
        this.special3 = new ArrayList<Projectile>();
    }

    @EventHandler
    public void onDamage(final ProjectileLaunchEvent projectileLaunchEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "zeus"));
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
                } catch (Exception ex) {
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final int n = (int) (Math.random() * 100.0);
        if (entityDamageByEntityEvent.getDamager() instanceof Projectile) {
            final Projectile projectile = (Projectile) entityDamageByEntityEvent.getDamager();
            if (entityDamageByEntityEvent.isCancelled()) {
                return;
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.getEntity().getWorld()).getApplicableRegions(entityDamageByEntityEvent.getEntity().getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
                return;
            }
            if (this.special.contains(projectile) && n < SettingsManager.enchant.getInt("zeus.level_I.chance")) {
                entityDamageByEntityEvent.getEntity().getWorld().strikeLightningEffect(entityDamageByEntityEvent.getEntity().getLocation());
            }
            if (this.special2.contains(projectile) && n < SettingsManager.enchant.getInt("zeus.level_II.chance")) {
                entityDamageByEntityEvent.getEntity().getWorld().strikeLightningEffect(entityDamageByEntityEvent.getEntity().getLocation());
            }
            if (this.special2.contains(projectile) && n < SettingsManager.enchant.getInt("zeus.level_III.chance")) {
                entityDamageByEntityEvent.getEntity().getWorld().strikeLightningEffect(entityDamageByEntityEvent.getEntity().getLocation());
            }
        }
    }
}
