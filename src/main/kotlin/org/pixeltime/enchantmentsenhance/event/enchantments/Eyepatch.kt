package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Eyepatch : Listener {
    @EventHandler
    fun onMove(playerMoveEvent: PlayerMoveEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "eyepatch"))
        val player = playerMoveEvent.player
        try {
            val armorContents = player.inventory.armorContents
            val length = armorContents.size
            var i = 0
            while (i < length) {
                val itemStack = armorContents[i]
                if (itemStack.hasItemMeta() && itemStack.itemMeta.hasLore() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && player.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                    player.removePotionEffect(PotionEffectType.BLINDNESS)
                }
                ++i
            }
        } catch (ex: Exception) {
        }

    }
}
