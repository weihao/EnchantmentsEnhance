package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Speed : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Gives you infinite speed boost", "你获得速度效果")
    }

    override fun lang(): Array<String> {
        return arrayOf("神速")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val level = getLevel(player)
        permaPotion(player, PotionEffectType.SPEED, SettingsManager.enchant.getInt("speed.$level.potion_lvl"))
    }
}
