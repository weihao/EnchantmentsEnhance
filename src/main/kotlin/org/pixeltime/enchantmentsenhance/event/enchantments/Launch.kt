package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.util.Vector
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Launch : Listener {
    @EventHandler
    fun onEntityDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "launch"))
        if (entityDamageByEntityEvent.entity is Player && entityDamageByEntityEvent.damager is Player) {
            val player = entityDamageByEntityEvent.entity as Player
            val player2 = entityDamageByEntityEvent.damager as Player
            val n = (Math.random() * 100.0).toInt()
            if (player2.itemInHand != null && player.itemInHand.hasItemMeta() && player.itemInHand.itemMeta.hasLore()) {
                if (player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("launch.level_I.chance")) {
                    player.velocity = Vector(0, SettingsManager.enchant.getInt("launch.level_I.height"), 0)
                }
                if (player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("launch.level_II.chance")) {
                    player.velocity = Vector(0, SettingsManager.enchant.getInt("launch.level_II.height"), 0)
                }
                if (player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("launch.level_III.chance")) {
                    player.velocity = Vector(0, SettingsManager.enchant.getInt("launch.level_III.height"), 0)
                }
                if (player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " IV") && n < SettingsManager.enchant.getInt("launch.level_IV.chance")) {
                    player.velocity = Vector(0, SettingsManager.enchant.getInt("launch.level_IV.height"), 0)
                }
                if (player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " V") && n < SettingsManager.enchant.getInt("launch.level_V.chance")) {
                    player.velocity = Vector(0, SettingsManager.enchant.getInt("launch.level_V.height"), 0)
                }
            }
        }
    }
}
