package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener

class Feather : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("No fall damage when you wear any armour part having this enchantment", "免疫掉落伤害")
    }

    override fun lang(): Array<String> {
        return arrayOf("羽落")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageEvent: EntityDamageEvent) {
        if (entityDamageEvent.entity is Player) {
            val player = entityDamageEvent.entity as Player
            if (entityDamageEvent.cause == EntityDamageEvent.DamageCause.FALL) {
                val level = getLevel(player)
                if (level > 0) {
                    entityDamageEvent.isCancelled = true
                }
            }
        }
    }
}