package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Reborn : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("When you kill a player, you will get Absorption and Regeneration potion effect for limited time", "当你杀人时，你会在短时间内获得伤害吸收和生命恢复")
    }

    override fun lang(): Array<String> {
        return arrayOf("重生")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDeath(playerDeathEvent: PlayerDeathEvent) {

        val entity = playerDeathEvent.entity
        if (entity.killer is Player) {
            val player = entity.killer!!
            try {
                val level = getLevel(player)
                if (level > 0) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.ABSORPTION, SettingsManager.enchant.getInt("reborn.$level.absorption.duration") * 20, SettingsManager.enchant.getInt("reborn.$level.absorption.potion_lvl") - 1))
                    player.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, SettingsManager.enchant.getInt("reborn.$level.regeneration.duration") * 20, SettingsManager.enchant.getInt("reborn.$level.regeneration.potion_lvl") - 1))
                }
            } catch (ex: Exception) {
            }

        }
    }
}
