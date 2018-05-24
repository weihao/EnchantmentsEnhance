package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Eyepatch : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "eyepatch"))

    @EventHandler
    fun onMove(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        if (player.hasPotionEffect(PotionEffectType.BLINDNESS)) {
            try {
                val armorContents = player.inventory.armorContents + IM.getAccessorySlots(player)
                for (i in armorContents.indices) {
                    val itemStack = armorContents[i]
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.hasLore()) {
                        val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                        if (level > 0) {
                            player.removePotionEffect(PotionEffectType.BLINDNESS)
                        }
                    }
                }
            } catch (ex: Exception) {
            }
        }
    }
}
