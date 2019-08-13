package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager


class Frosty : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Has a chance of inflicting a slow effect on your victim.", "攻击别人有几率造成对方缓慢")
    }

    override fun lang(): Array<String> {
        return arrayOf("冰霜")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            val player2 = entityDamageByEntityEvent.entity as Player
            val level = getLevel(player)
            if (level > 0) {
                player2.addPotionEffect(PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("frosty.$level.duration") * 20, 10))
            }
        }
    }
}