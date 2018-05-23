package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Batvision : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "batvision"))

    @EventHandler
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val armorContents = player.inventory.armorContents + IM.getAccessorySlots(player)
        for (i in armorContents.indices) {
            try {
                val level = KM.getLevel(translateAlternateColorCodes, player.itemInHand.itemMeta.lore)
                if (level > 0) {
                    if (player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION)
                        player.addPotionEffect(PotionEffect(PotionEffectType.NIGHT_VISION, 300, 0))
                    } else {
                        player.addPotionEffect(PotionEffect(PotionEffectType.NIGHT_VISION, 300, 0))
                    }
                }
            } catch (ex: Exception) {
            }
        }
    }
}
