package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.util.Vector
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Launch : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "launch"))
    @EventHandler
    fun onEntityDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.entity is Player && entityDamageByEntityEvent.damager is Player) {
            val player = entityDamageByEntityEvent.entity as Player
            val player2 = entityDamageByEntityEvent.damager as Player

            try {
                val armorContents = player2.inventory.armorContents + IM.getAccessorySlots(player2)
                for (i in armorContents.indices) {
                    val itemStack = armorContents[i]
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.hasLore()) {
                        val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                        if (level > 0 && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("launch.$level.chance")) {
                            player.velocity = Vector(0, SettingsManager.enchant.getInt("launch.$level.height"), 0)
                        }
                    }
                }
            } catch (ex: Exception) {

            }
        }
    }
}
