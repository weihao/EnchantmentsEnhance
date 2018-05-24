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

class Reinforced : Listener {
    @EventHandler
    fun onPalyerWalk(playerMoveEvent: PlayerMoveEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "platemail"))
        val player = playerMoveEvent.player
        try {
            val armorContents = player.inventory.armorContents + IM.getAccessorySlots(player)
            for (i in armorContents.indices) {
                val itemStack = armorContents[i]
                if (itemStack.hasItemMeta() && itemStack.itemMeta.hasLore()) {
                    val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                    if (level > 0) {
                        player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, SettingsManager.enchant.getInt("reinforced.$level.duration") * 20, SettingsManager.enchant.getInt("reinforced.$level.potion_lvl") - 1))
                    }
                }
            }
        } catch (ex: Exception) {
        }
    }
}
