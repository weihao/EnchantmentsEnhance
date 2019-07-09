package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener

class Shield : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Gives infinite health boost.", "获得无限的伤害吸收")
    }

    override fun lang(): Array<String> {
        return arrayOf("护盾")
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val level = getLevel(player)
        permaPotion(player, PotionEffectType.ABSORPTION, level)
    }
}
