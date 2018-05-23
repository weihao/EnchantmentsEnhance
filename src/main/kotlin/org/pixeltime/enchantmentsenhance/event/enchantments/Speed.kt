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

class Speed : Listener {
    @EventHandler
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "speed"))
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.inventory.boots != null && player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 200, SettingsManager.enchant.getInt("speed.level_I.potion_lvl") - 1))
                }
                if (player.inventory.boots != null && player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 200, SettingsManager.enchant.getInt("speed.level_II.potion_lvl") - 1))
                }
                if (player.inventory.boots != null && player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 200, SettingsManager.enchant.getInt("speed.level_III.potion_lvl") - 1))
                }
                return
            }
            if (EnchantmentsManager.speed.contains(player.name) && player.inventory.boots == null || !player.inventory.boots.hasItemMeta() || !player.inventory.boots.itemMeta.hasLore() || !player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && !player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && !player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                player.removePotionEffect(PotionEffectType.SPEED)
                EnchantmentsManager.speed.remove(player.name)
                return
            }
            if (player.inventory.boots != null && player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SettingsManager.enchant.getInt("speed.level_I.potion_lvl") - 1))
                if (!EnchantmentsManager.speed.contains(player.name)) {
                    EnchantmentsManager.speed.add(player.name)
                }
            }
            if (player.inventory.boots != null && player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SettingsManager.enchant.getInt("speed.level_II.potion_lvl") - 1))
                if (!EnchantmentsManager.speed.contains(player.name)) {
                    EnchantmentsManager.speed.add(player.name)
                }
            }
            if (player.inventory.boots != null && player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, SettingsManager.enchant.getInt("speed.level_III.potion_lvl") - 1))
                if (!EnchantmentsManager.speed.contains(player.name)) {
                    EnchantmentsManager.speed.add(player.name)
                }
            }
        } catch (ex: Exception) {
        }

    }
}
