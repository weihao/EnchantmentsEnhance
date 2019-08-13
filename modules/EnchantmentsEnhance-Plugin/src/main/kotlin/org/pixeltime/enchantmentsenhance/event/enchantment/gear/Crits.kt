package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import com.lgou2w.ldk.bukkit.compatibility.Sounds
import org.bukkit.entity.Damageable
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener


class Crits : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("You will deal double damage", "你会输出双倍伤害")
    }

    override fun lang(): Array<String> {
        return arrayOf("暴击")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            val player2 = entityDamageByEntityEvent.entity as Player
            val level = getLevel(player)
            if (level > 0) {
                val damage = entityDamageByEntityEvent.damage * 2.0
                val currentHealth = (player2 as Damageable).health
                if (currentHealth > damage) {
                    (player2 as Damageable).health = currentHealth - damage
                    player.location.world!!.playSound(player.location, Sounds.ENDERDRAGON_GROWL.toBukkit(), 0.1f, 0.1f)
                }
            }
        }
    }
}
