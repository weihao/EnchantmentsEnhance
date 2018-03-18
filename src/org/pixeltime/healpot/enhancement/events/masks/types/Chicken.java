package org.pixeltime.healpot.enhancement.events.masks.types;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class Chicken {
    @EventHandler
    public void onPlayerFall(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player name = (Player)e.getEntity();
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                e.setCancelled(true);
            }
        }
    }
}
