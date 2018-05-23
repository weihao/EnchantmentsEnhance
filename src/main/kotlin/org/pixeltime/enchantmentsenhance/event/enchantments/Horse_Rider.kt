package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Horse
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Horse_Rider : Listener {
    @EventHandler
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "horse_rider"))
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity !is Player) {
            try {
                val owner = entityDamageByEntityEvent.damager as Player
                val armorContents = owner.inventory.armorContents
                val length = armorContents.size
                var i = 0
                while (i < length) {
                    val itemStack = armorContents[i]
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && entityDamageByEntityEvent.entity is Horse) {
                        entityDamageByEntityEvent.isCancelled = true
                        entityDamageByEntityEvent.damage = 0.0
                        val horse = entityDamageByEntityEvent.entity as Horse
                        if (horse.isTamed) {
                            return
                        }
                        horse.isTamed = true
                        horse.owner = owner
                        horse.inventory.saddle = ItemStack(Material.SADDLE)
                    }
                    ++i
                }
            } catch (ex: Exception) {
            }

        }
    }
}
