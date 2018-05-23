package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.entity.Wolf
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Tamer : Listener {
    @EventHandler
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "tamer"))
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity !is Player) {
            try {
                val owner = entityDamageByEntityEvent.damager as Player
                val armorContents = owner.inventory.armorContents
                val length = armorContents.size
                var i = 0
                while (i < length) {
                    val itemStack = armorContents[i]
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && entityDamageByEntityEvent.entity is Wolf) {
                        entityDamageByEntityEvent.isCancelled = true
                        entityDamageByEntityEvent.damage = 0.0
                        val wolf = entityDamageByEntityEvent.entity as Wolf
                        if (wolf.isTamed) {
                            return
                        }
                        wolf.isTamed = true
                        wolf.owner = owner
                    }
                    ++i
                }
            } catch (ex: Exception) {
            }

        }
    }
}
