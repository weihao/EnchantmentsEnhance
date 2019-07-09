package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Rekt : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("You give 2x damage to mobs", "你对生物造成两倍伤害")
    }

    override fun lang(): Array<String> {
        return arrayOf("碾压")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.entity is Player) {
            return
        }

        if (entityDamageByEntityEvent.damager is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }

            try {
                val level = getLevel(player)
                if (level > 0) {
                    entityDamageByEntityEvent.damage = entityDamageByEntityEvent.damage * SettingsManager.enchant.getDouble("rekt.$level.multiplier")
                }
            } catch (ex: Exception) {
            }

        }
    }
}
