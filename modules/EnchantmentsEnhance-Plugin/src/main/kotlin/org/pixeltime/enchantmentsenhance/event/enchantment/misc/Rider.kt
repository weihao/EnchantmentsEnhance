package org.pixeltime.enchantmentsenhance.event.enchantment.misc

import org.bukkit.Material
import org.bukkit.entity.Horse
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener

class Rider : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Tames a horse with saddle when hit while wearing armour with this enchant.", "你可以用鞍来驯服马")
    }

    override fun lang(): Array<String> {
        return arrayOf("骑御")
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity !is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            try {
                val level = getLevel(player)
                if (level > 0 && entityDamageByEntityEvent.entity is Horse) {
                    entityDamageByEntityEvent.isCancelled = true
                    entityDamageByEntityEvent.damage = 0.0
                    val horse = entityDamageByEntityEvent.entity as Horse
                    if (horse.isTamed) {
                        return
                    }
                    horse.isTamed = true
                    horse.owner = player
                    horse.inventory.saddle = ItemStack(Material.SADDLE)

                }
            } catch (ex: Exception) {
            }
        }
    }
}
