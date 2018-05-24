package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Feather_Fall : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "feather_fall"))
    @EventHandler
    fun onDamage(entityDamageEvent: EntityDamageEvent) {
        if (entityDamageEvent.entity is Player) {
            val player = entityDamageEvent.entity as Player
            val armorContents = player.inventory.armorContents + IM.getAccessorySlots(player)
            for (itemStack in armorContents)
                if (itemStack != null && itemStack.hasItemMeta() && itemStack.itemMeta.hasLore() && entityDamageEvent.cause == EntityDamageEvent.DamageCause.FALL) {
                    val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                    if (level > 0) {
                        entityDamageEvent.isCancelled = true
                    }
                }
        }
    }
}