package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener

class Blessed : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Restores the wearers health and hunger while walking and running", "走路时有几率补满饥饿以及生命")
    }

    override fun lang(): Array<String> {
        return arrayOf("祝福")
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onMove(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        try {
            val level = getLevel(player)
            if (level > 0 && roll(level)) {
                player.health = player.maxHealth
                player.foodLevel = 20
            }
        } catch (ex: Exception) {
        }
    }
}
