package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Molten : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Gives infinite Fire Resistant", "获得无限的火焰保护")
    }

    override fun lang(): Array<String> {
        return arrayOf("炉融")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val level = getLevel(player)
        permaPotion(player, PotionEffectType.FIRE_RESISTANCE, SettingsManager.enchant.getInt("molten.$level.potion_lvl"))
    }
}
