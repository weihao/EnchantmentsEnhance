package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener

class Eyepatch : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Immune to blindness", "无视失明效果")
    }

    override fun lang(): Array<String> {
        return arrayOf("天眼")
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onMove(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        if (player.hasPotionEffect(PotionEffectType.BLINDNESS)) {
            try {
                val level = getLevel(player)
                if (level > 0) {
                    player.removePotionEffect(PotionEffectType.BLINDNESS)
                }
            } catch (ex: Exception) {
            }
        }
    }
}
