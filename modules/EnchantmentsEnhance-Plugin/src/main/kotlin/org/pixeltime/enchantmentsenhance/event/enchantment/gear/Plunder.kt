package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDeathEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.util.Util

class Plunder : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("More XP from entities", "击杀生物更多经验")
    }

    override fun lang(): Array<String> {
        return arrayOf("掠夺")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDeath(entityDeathEvent: EntityDeathEvent) {

        if (entityDeathEvent.entity.killer is Player && entityDeathEvent.entity !is Player) {
            val killer = entityDeathEvent.entity.killer
            if (killer!!.itemInHand != null &&
                (Util.getMainHand(killer)).hasItemMeta() &&
                Util.getMainHand(killer).itemMeta!!.hasLore() &&
                killer.inventory.itemInMainHand.itemMeta!!.lore!!.contains(this.name() + " I")
            ) {
                entityDeathEvent.droppedExp = 20
            }
        }
    }
}
