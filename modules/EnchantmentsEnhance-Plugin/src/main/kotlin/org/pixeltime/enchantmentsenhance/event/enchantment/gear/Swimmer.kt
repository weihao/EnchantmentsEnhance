package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Swimmer : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Gives you infinite water breathing", "你获得无限的水下呼吸")
    }

    override fun lang(): Array<String> {
        return arrayOf("亲水")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val level = getLevel(player)
        permaPotion(
            player,
            PotionEffectType.WATER_BREATHING,
            SettingsManager.enchant.getInt("swimmer.$level.potion_lvl")
        )
    }
}
