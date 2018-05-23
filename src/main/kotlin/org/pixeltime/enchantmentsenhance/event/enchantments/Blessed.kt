package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Blessed : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "blessed"))

    @EventHandler
    fun onMove(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        try {
            val armorContents = player.inventory.armorContents + IM.getAccessorySlots(player)
            for (i in armorContents.indices) {
                val itemStack = armorContents[i]
                if (itemStack != null && itemStack.hasItemMeta())
                {
                    val level = KM.getLevel(translateAlternateColorCodes, player.itemInHand.itemMeta.lore)
                    val n = (Math.random() * 100.0).toInt()
                    if (level > 0  && n < SettingsManager.enchant.getInt("blessed.$level.chance")) {
                        player.health = player.maxHealth
                        player.foodLevel = 20
                    }
                }
            }
        } catch (ex: Exception) {
        }

    }
}
