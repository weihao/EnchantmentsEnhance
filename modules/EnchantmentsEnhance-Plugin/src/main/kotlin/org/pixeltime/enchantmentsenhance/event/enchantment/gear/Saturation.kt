package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Saturation : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Gives you infinite saturation", "给你饱和")
    }

    override fun lang(): Array<String> {
        return arrayOf("饱和")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onPalyerWalk(playerMoveEvent: PlayerMoveEvent) {

        val player = playerMoveEvent.player
        val level = getLevel(player)
        permaPotion(player, PotionEffectType.SATURATION, SettingsManager.enchant.getInt("saturation.$level.potion_lvl"))
    }
}
