package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Petrify : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Has a chance to give slowness effect to your enemy", "攻击别人有几率造成他缓慢")
    }

    override fun lang(): Array<String> {
        return arrayOf("石化")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onHit(entityDamageByEntityEvent: EntityDamageByEntityEvent) {

        if (entityDamageByEntityEvent.entity is Player && entityDamageByEntityEvent.damager is Player) {
            val player = entityDamageByEntityEvent.entity as Player
            val victim = entityDamageByEntityEvent.damager as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            try {
                val level = getLevel(victim)
                if (level > 0 && (roll(level))) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("petrify.$level.duration") * 20, SettingsManager.enchant.getInt("petrify.$level.potion_lvl") - 1))

                }
            } catch (ex: Exception) {
            }

        }
    }
}
