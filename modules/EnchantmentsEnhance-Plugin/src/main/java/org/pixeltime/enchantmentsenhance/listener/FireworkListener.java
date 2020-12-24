package org.pixeltime.enchantmentsenhance.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class FireworkListener implements Listener {
    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION && e.getDamager().getType() == EntityType.FIREWORK) {
            e.setCancelled(true);
        }
    }
}
