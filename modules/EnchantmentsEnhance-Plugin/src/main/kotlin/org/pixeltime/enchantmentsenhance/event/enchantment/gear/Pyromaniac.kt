package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener

class Pyromaniac : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Fire ticks have a chance to heal you instead of doing damage", "你被火烧着时有几率回血而不是受到伤害")
    }

    override fun lang(): Array<String> {
        return arrayOf("火焰")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageEvent: EntityDamageEvent) {
        if (entityDamageEvent.entity is Player) {

            val player = entityDamageEvent.entity as Player
            if (entityDamageEvent.cause == EntityDamageEvent.DamageCause.FIRE || entityDamageEvent.cause == EntityDamageEvent.DamageCause.FIRE_TICK) {
                try {
                    val level = getLevel(player)
                    if (level > 0 && (roll(level))) {
                        player.health = 20.0
                        player.foodLevel = 20
                    }
                } catch (ex: Exception) {
                }
            }
        }
    }
}
