package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Phoenix : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Gives you infinite regeneration", "你获得恢复效果")
    }

    override fun lang(): Array<String> {
        return arrayOf("凤凰")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val level = getLevel(player)
        permaPotion(
            player,
            PotionEffectType.REGENERATION,
            SettingsManager.enchant.getInt("${this.javaClass.simpleName.toLowerCase()}.$level.potion_lvl")
        )
    }
}
