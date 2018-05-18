package org.pixeltime.enchantmentsenhance.event.enchantment;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.util.ArrayList;

public class Tnt_Shooter implements Listener {
    private ArrayList<Entity> entity;

    public Tnt_Shooter() {
        this.entity = new ArrayList<Entity>();
    }

    @EventHandler
    public void onShoot(final EntityShootBowEvent entityShootBowEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "tnt_shooter"));
        if (entityShootBowEvent.getEntity() instanceof Player) {
            final Player player = (Player) entityShootBowEvent.getEntity();
            try {
                if (entityShootBowEvent.getBow().getItemMeta().hasLore() && entityShootBowEvent.getBow().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                    final TNTPrimed projectile = (TNTPrimed) entityShootBowEvent.getProjectile().getWorld().spawn(entityShootBowEvent.getProjectile().getLocation(), (Class) TNTPrimed.class);
                    projectile.setVelocity(player.getEyeLocation().getDirection().multiply(entityShootBowEvent.getForce()));
                    entityShootBowEvent.setProjectile(projectile);
                    if (!SettingsManager.enchant.getBoolean("tnt_shooter.level_I.explosion")) {
                        this.entity.add(projectile);
                    }
                }
            } catch (Exception ex) {
            }
        }
    }

    @EventHandler
    public void onExplode(final EntityExplodeEvent entityExplodeEvent) {
        if (entityExplodeEvent.getEntity() instanceof TNTPrimed) {
            final TNTPrimed tntPrimed = (TNTPrimed) entityExplodeEvent.getEntity();
            if (this.entity.contains(tntPrimed)) {
                entityExplodeEvent.blockList().clear();
                this.entity.remove(tntPrimed);
            }
        }
    }
}
