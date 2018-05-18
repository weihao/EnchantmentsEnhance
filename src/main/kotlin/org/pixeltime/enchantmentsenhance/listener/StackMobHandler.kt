package org.pixeltime.enchantmentsenhance.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.pixeltime.enchantmentsenhance.manager.DropManager
import org.pixeltime.stackmob.events.StackKilledEvent

class StackMobHandler : Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onDeath(e: StackKilledEvent) {
        if (getResult(e.numberKilled, DropManager.killingChance)) {
            DropManager.randomDrop(e.player, DropManager.killingLootTable)
        }
    }
    companion object {
        @JvmStatic
        fun bernoulli(repeat: Int, chance: Double): Double {
            return Math.pow((1 - chance), repeat.toDouble())
        }

        @JvmStatic
        fun getResult(repeat: Int, chance: Double): Boolean {
            if (repeat == 0) {
                return false;
            }
            return (1 - bernoulli(repeat, chance) > Math.random())
        }
    }
}