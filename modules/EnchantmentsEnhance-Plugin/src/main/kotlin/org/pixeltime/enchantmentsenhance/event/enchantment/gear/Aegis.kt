package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager


class Aegis : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf(
            "When blocking, player has a chance  to regain health", "当你用剑格挡时，你会恢复血量"
        )
    }

    override fun lang(): Array<String> {
        return arrayOf("庇护")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun block(playerInteractEvent: PlayerInteractEvent) {
        val player = playerInteractEvent.player
        if (playerInteractEvent.action != Action.RIGHT_CLICK_AIR) {
            return
        }
        try {
            val level = getLevel(player)
            if (level > 0 && (roll(level))) {
                player.health += SettingsManager.enchant.getDouble("aegis.$level.health")
            }
        } catch (ex: Exception) {
        }
    }
}
