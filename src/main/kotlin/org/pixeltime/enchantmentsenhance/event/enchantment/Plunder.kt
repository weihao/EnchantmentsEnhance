package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Plunder : Listener {
    @EventHandler
    fun onDeath(entityDeathEvent: EntityDeathEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "plunder"))
        if (entityDeathEvent.entity.killer is Player && entityDeathEvent.entity !is Player) {
            val killer = entityDeathEvent.entity.killer
            if (killer.itemInHand != null && killer.itemInHand.hasItemMeta() && killer.itemInHand.itemMeta.hasLore() && killer.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                entityDeathEvent.droppedExp = 20
            }
        }
    }
}
