package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.Material
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.PlayerDeathEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener


class Suicide : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("A chance to create an explosion when the player dies", "死后爆炸")
    }

    override fun lang(): Array<String> {
        return arrayOf("帝陨")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDeath(playerDeathEvent: PlayerDeathEvent) {
        val player = playerDeathEvent.entity


        try {
            val level = getLevel(player)
            if (level > 0 && (roll(level))) {
                for (i in 1..5) {
                    val tnt = player.world.spawn(
                        player.getTargetBlock(HashSet<Material>(), 50).location.add(0.0, 1.0, 0.0),
                        TNTPrimed::class.java
                    )
                    (tnt as TNTPrimed).fuseTicks = 10
                }
            }
        } catch (ex: Exception) {
        }
    }
}
