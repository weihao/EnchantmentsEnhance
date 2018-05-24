package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.MM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

private val translateAlternateColorCodes: String = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "aegis"))

class Aegis : Listener {
    @EventHandler
    fun block(playerInteractEvent: PlayerInteractEvent) {
        val player = playerInteractEvent.player
        if (playerInteractEvent.action != Action.RIGHT_CLICK_AIR) {
            return
        }
        try {
            if (MM.sword.contains(player.itemInHand.type)) {
                val armorContents = player.inventory.armorContents + IM.getAccessorySlots(player)
                for (i in armorContents.indices) {
                    val itemStack = armorContents[i]
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.itemMeta.hasLore()) {
                        val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                        if (level > 0 && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("aegis.$level.chance")) {
                            player.health += SettingsManager.enchant.getDouble("aegis.$level.health")
                        }
                    }
                }
            }
        } catch (ex: Exception) {
        }

    }
}
