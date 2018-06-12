package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Damageable
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Sounds


class Crits : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantments." + "crits"))
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            val player2 = entityDamageByEntityEvent.entity as Player
            val level = IM.getHighestLevel(player, translateAlternateColorCodes)
            if (level > 0) {
                val damage = entityDamageByEntityEvent.damage * 2.0
                val currentHealth = (player2 as Damageable).health
                if (currentHealth > damage) {
                    (player2 as Damageable).health = currentHealth - damage
                    player.location.world.playSound(player.location, Sounds.ENDERDRAGON_GROWL.bukkitSound(), 0.1f, 0.1f)
                }
            }
        }
    }
}