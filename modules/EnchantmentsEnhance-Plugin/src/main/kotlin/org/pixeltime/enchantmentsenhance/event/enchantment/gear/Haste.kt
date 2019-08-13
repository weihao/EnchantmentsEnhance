package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Haste : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Gives you Haste Potion Effect", "获得急迫")
    }

    override fun lang(): Array<String> {
        return arrayOf("急迫")
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val level = getLevel(player)
        permaPotion(player, PotionEffectType.FAST_DIGGING, SettingsManager.enchant.getInt("haste.$level.potion_lvl"))
    }
}
