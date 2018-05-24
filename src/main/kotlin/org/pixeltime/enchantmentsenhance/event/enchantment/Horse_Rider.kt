package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Horse
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Horse_Rider : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "horse_rider"))

    @EventHandler
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity !is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            try {
                val armorContents = player.inventory.armorContents + IM.getAccessorySlots(player)
                for (i in armorContents.indices) {
                    val itemStack = armorContents[i]
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.hasLore()) {
                        val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                        if (level > 0 && entityDamageByEntityEvent.entity is Horse) {
                            entityDamageByEntityEvent.isCancelled = true
                            entityDamageByEntityEvent.damage = 0.0
                            val horse = entityDamageByEntityEvent.entity as Horse
                            if (horse.isTamed) {
                                return
                            }
                            horse.isTamed = true
                            horse.owner = player
                            horse.inventory.saddle = ItemStack(Material.SADDLE)
                        }
                    }
                }
            } catch (ex: Exception) {
            }
        }
    }
}
