package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.util.Vector
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Launch : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("A chance to launch the enemy up in the sky!", "有机率把敌人弹上天")
    }

    override fun lang(): Array<String> {
        return arrayOf("弹射")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onEntityDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.entity is Player && entityDamageByEntityEvent.damager is Player) {
            val player = entityDamageByEntityEvent.entity as Player
            val player2 = entityDamageByEntityEvent.damager as Player

            try {
                val level = getLevel(player2)
                if (level > 0 && (roll(level))) {
                    player.velocity = Vector(0, SettingsManager.enchant.getInt("launch.$level.height"), 0)
                }
            } catch (ex: Exception) {
            }
        }
    }
}
