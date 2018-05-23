package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.manager.EnchantmentsManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Haste : Listener {
    @EventHandler
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "haste"))
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.inventory.itemInHand != null && player.inventory.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.FAST_DIGGING, 200, 0))
                }
                if (player.inventory.itemInHand != null && player.inventory.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.FAST_DIGGING, 200, 1))
                }
                if (player.inventory.itemInHand != null && player.inventory.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.FAST_DIGGING, 200, 2))
                }
                return
            }
            if (EnchantmentsManager.haste.contains(player.name) && player.inventory.itemInHand == null || !player.inventory.itemInHand.hasItemMeta() || !player.inventory.itemInHand.itemMeta.hasLore() || !player.inventory.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && !player.inventory.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && !player.inventory.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                player.removePotionEffect(PotionEffectType.FAST_DIGGING)
                EnchantmentsManager.haste.remove(player.name)
                return
            }
            if (player.inventory.itemInHand != null && player.inventory.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0))
                if (!EnchantmentsManager.haste.contains(player.name)) {
                    EnchantmentsManager.haste.add(player.name)
                }
            }
            if (player.inventory.itemInHand != null && player.inventory.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 1))
                if (!EnchantmentsManager.haste.contains(player.name)) {
                    EnchantmentsManager.haste.add(player.name)
                }
            }
            if (player.inventory.itemInHand != null && player.inventory.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 2))
                if (!EnchantmentsManager.haste.contains(player.name)) {
                    EnchantmentsManager.haste.add(player.name)
                }
            }
        } catch (ex: Exception) {
        }

    }
}
