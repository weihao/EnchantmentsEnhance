package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Jump : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Gives you infinite jump boost", "获得跳跃")
    }

    override fun lang(): Array<String> {
        return arrayOf("跳跃")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val level = getLevel(player)
        permaPotion(player, PotionEffectType.JUMP, SettingsManager.enchant.getInt("jump.$level.potion_lvl"))
    }
}
