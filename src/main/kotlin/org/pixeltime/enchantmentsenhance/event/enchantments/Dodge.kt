package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Dodge : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "dodge"))
    @EventHandler
    fun onDamage(entityDamageEvent: EntityDamageEvent) {
        if (entityDamageEvent.entity is Player) {
            val player = entityDamageEvent.entity as Player
            val n = (Math.random() * 100.0).toInt()
            val armorContents = player.inventory.armorContents + IM.getAccessorySlots(player)
            try {
                for (i in armorContents.indices) {
                    val itemStack = armorContents[i]
                    if (itemStack != null && itemStack.hasItemMeta()) {
                        val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                        if (level > 0 && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("dodge.$level.chance")) {
                            entityDamageEvent.damage = 0.0
                        }
                    }
                }
            } catch (ex: Exception) {
            }

        }
    }
}
