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

public class Medic implements Listener {
    private ArrayList<Projectile> special;

    public Medic() {
        this.special = new ArrayList<Projectile>();
    }

    @EventHandler
    public void onDamage(final ProjectileLaunchEvent projectileLaunchEvent) {
        final String translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "medic"));
        if (projectileLaunchEvent.getEntity() instanceof Arrow) {
            final Arrow arrow = (Arrow) projectileLaunchEvent.getEntity();
            if (arrow.getShooter() instanceof Player) {
                final Player player = (Player) arrow.getShooter();
                try {
                    if (player.getItemInHand().getItemMeta().getLore().contains(String.valueOf(translateAlternateColorCodes) + " I")) {
                        this.special.add(arrow);
                    }
                } catch (Exception ex) {
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDamage(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getDamager() instanceof Projectile) {
            final Projectile projectile = (Projectile) entityDamageByEntityEvent.getDamager();
            if (this.special.contains(projectile) && entityDamageByEntityEvent.getEntity() instanceof Player) {
                final Player player = (Player) entityDamageByEntityEvent.getEntity();
                if (projectile.getShooter() instanceof Player) {
                    final Player player2 = (Player) projectile.getShooter();
                    if (entityDamageByEntityEvent.isCancelled()) {
                        return;
                    }
                    if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).queryState(null, new StateFlag[]{DefaultFlag.PVP}) == StateFlag.State.DENY) {
                        return;
                    }
                    if (player.getName().equalsIgnoreCase(player2.getName())) {
                        return;
                    }
                    player.setHealth(20.0);
                }
            }
        }
    }
}
