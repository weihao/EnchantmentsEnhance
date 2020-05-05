package org.pixeltime.enchantmentsenhance.event.enchantment.misc

import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener

class Wings : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("You can fly", "你可以自由飞行")
    }

    override fun lang(): Array<String> {
        return arrayOf("羽翼")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val level = getLevel(player)
        player.allowFlight = level > 0 || player.gameMode == GameMode.CREATIVE || player.gameMode == GameMode.SPECTATOR
    }
}
