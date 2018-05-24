package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Petrify : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onHit(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "petrify"))
        if (entityDamageByEntityEvent.entity is Player && entityDamageByEntityEvent.damager is Player) {
            val player = entityDamageByEntityEvent.entity as Player
            val player2 = entityDamageByEntityEvent.damager as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            try {
                val armorContents = player2.inventory.armorContents + IM.getAccessorySlots(player2)
                for (i in armorContents.indices) {
                    val itemStack = armorContents[i]
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.hasLore()) {
                        val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                        if (level > 0 && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("petrify.$level.chance")) {
                            player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, SettingsManager.enchant.getInt("petrify.$level.duration") * 20, SettingsManager.enchant.getInt("petrify.$level.potion_lvl") - 1))
                        }
                    }
                }
            } catch (ex: Exception) {
            }

        }
    }
}
