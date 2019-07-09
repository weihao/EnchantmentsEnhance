package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Invisible : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Gives you infinite invisibility", "获得隐身")
    }

    override fun lang(): Array<String> {
        return arrayOf("隐身")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val level = getLevel(player)
        permaPotion(player, PotionEffectType.INVISIBILITY, SettingsManager.enchant.getInt("invisible.$level.potion_lvl"))
    }
}
