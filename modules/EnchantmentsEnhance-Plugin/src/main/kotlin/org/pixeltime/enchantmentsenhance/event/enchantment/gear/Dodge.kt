package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener

class Dodge : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Chance of evading damage", "有几率躲避伤害")
    }

    override fun lang(): Array<String> {
        return arrayOf("闪避")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageEvent: EntityDamageEvent) {
        if (entityDamageEvent.entity is Player) {
            val player = entityDamageEvent.entity as Player
            val level = getLevel(player)
            if (level > 0 && (roll(level))) {
                entityDamageEvent.damage = 0.0
            }
        }
    }
}
