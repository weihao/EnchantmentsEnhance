package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Rekt : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.entity is Player) {
            return
        }
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "rekt"))
        if (entityDamageByEntityEvent.damager is Player) {
            try {
                val player = entityDamageByEntityEvent.damager as Player
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                if (player.itemInHand.itemMeta.hasLore() && player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                    entityDamageByEntityEvent.damage = entityDamageByEntityEvent.damage * 2.0
                }
            } catch (ex: Exception) {
            }

        }
    }
}
