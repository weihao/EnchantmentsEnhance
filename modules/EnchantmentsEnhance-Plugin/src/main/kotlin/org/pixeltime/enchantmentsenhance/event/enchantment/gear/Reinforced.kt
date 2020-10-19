package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Reinforced : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("You get damage resistance", "你会获得伤害减免")
    }

    override fun lang(): Array<String> {
        return arrayOf("钢筋")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onPalyerWalk(playerMoveEvent: PlayerMoveEvent) {

        val player = playerMoveEvent.player
        val level = getLevel(player)
        permaPotion(
            player,
            PotionEffectType.DAMAGE_RESISTANCE,
            SettingsManager.enchant.getInt("reinforced.$level.potion_lvl")
        )
    }
}
