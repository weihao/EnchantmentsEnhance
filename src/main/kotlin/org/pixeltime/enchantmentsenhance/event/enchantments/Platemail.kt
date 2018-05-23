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

class Platemail : Listener {
    @EventHandler
    fun onPalyerWalk(playerMoveEvent: PlayerMoveEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "platemail"))
        val translateAlternateColorCodes2 = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "reinforced"))
        val player = playerMoveEvent.player
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.inventory.chestplate != null && player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 100, 1))
                    if (!player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " I") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " II") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " III") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " IV") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " V")) {
                        player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0))
                    }
                }
                return
            }
            if (EnchantmentsManager.platemail.contains(player.name) && player.inventory.chestplate == null || !player.inventory.chestplate.hasItemMeta() || !player.inventory.chestplate.itemMeta.hasLore() || !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                player.removePotionEffect(PotionEffectType.SLOW)
                if (!player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " I") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " II") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " III") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " IV") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " V")) {
                    player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE)
                }
                EnchantmentsManager.platemail.remove(player.name)
                return
            }
            if (player.inventory.chestplate != null && player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1))
                if (!player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " I") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " II") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " III") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " IV") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes2.toString() + " V")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0))
                    if (!EnchantmentsManager.platemail.contains(player.name)) {
                        EnchantmentsManager.platemail.add(player.name)
                    }
                }
            }
        } catch (ex: Exception) {
        }

    }
}
