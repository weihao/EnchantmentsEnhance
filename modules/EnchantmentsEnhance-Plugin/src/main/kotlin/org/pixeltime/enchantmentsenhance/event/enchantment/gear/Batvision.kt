package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener

class Batvision : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Gives you infinite night vision", "无限的夜视")
    }

    override fun lang(): Array<String> {
        return arrayOf("天眼")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val level = getLevel(player)
        permaPotion(player, PotionEffectType.NIGHT_VISION, level)
    }
}