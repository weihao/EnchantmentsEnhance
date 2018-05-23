package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Feather_Fall : Listener {
    @EventHandler
    fun onDamage(entityDamageEvent: EntityDamageEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "feather_fall"))
        if (entityDamageEvent.entity is Player) {
            val armorContents = (entityDamageEvent.entity as Player).inventory.armorContents
            val length = armorContents.size
            var i = 0
            while (i < length) {
                val itemStack = armorContents[i]
                if (itemStack != null && itemStack.hasItemMeta() && itemStack.itemMeta.hasLore() && entityDamageEvent.cause == EntityDamageEvent.DamageCause.FALL && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                    entityDamageEvent.isCancelled = true
                }
                ++i
            }
        }
    }
}
