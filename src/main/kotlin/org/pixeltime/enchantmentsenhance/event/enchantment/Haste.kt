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

class Haste : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "haste"))

    @EventHandler
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val armorContents = player.inventory.armorContents + IM.getAccessorySlots(player)
        try {
            for (itemStack in armorContents) {
                if (itemStack.hasItemMeta() && itemStack.itemMeta.hasLore()) {
                    val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                    if (level > 0) {
                        player.addPotionEffect(PotionEffect(PotionEffectType.FAST_DIGGING, 200, SettingsManager.enchant.getInt("enchantment.haste.$level.amplifier")))
                    }
                }
            }
        } catch (ex: Exception) {
        }

    }
}
