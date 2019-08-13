package org.pixeltime.enchantmentsenhance.listener

import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent

class FireworkListener : Listener {
    @EventHandler
    fun onDamage(e: EntityDamageByEntityEvent) {
        if (e.cause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION && e.damager.type == EntityType.FIREWORK) {
            e.isCancelled = true
        }
    }
}
