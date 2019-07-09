package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Vitality : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Gives you extra hearts", "获取额外生命")
    }

    override fun lang(): Array<String> {
        return arrayOf("元气")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onPalyerWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val level = getLevel(player)
        permaPotion(player, PotionEffectType.HEALTH_BOOST, SettingsManager.enchant.getInt("vitality.$level.potion_lvl"))
    }
}
