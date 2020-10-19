package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Strength : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Gives infinite Strength", "获得无限的力量")
    }

    override fun lang(): Array<String> {
        return arrayOf("力量")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val level = getLevel(player)
        permaPotion(
            player,
            PotionEffectType.INCREASE_DAMAGE,
            SettingsManager.enchant.getInt("strength.$level.potion_lvl")
        )
    }
}
