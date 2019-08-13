package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener

class Riftslayer : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("You deal up to 3x more damage to mobs", "你对生物造成三倍伤害")
    }

    override fun lang(): Array<String> {
        return arrayOf("溅射")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {

        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity !is Player) {

            val player = entityDamageByEntityEvent.damager as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            try {
                val level = getLevel(player)
                if (level > 0) {
                    entityDamageByEntityEvent.damage = entityDamageByEntityEvent.damage * 3.0
                }
            } catch (ex: Exception) {
            }

        }
    }
}
