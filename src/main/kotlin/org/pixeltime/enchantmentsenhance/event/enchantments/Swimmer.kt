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

class Swimmer : Listener {
    @EventHandler
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "swimmer"))
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.inventory.helmet != null && player.inventory.helmet.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.WATER_BREATHING, 200, SettingsManager.enchant.getInt("swimmer.level_I.potion_lvl") - 1))
                }
                if (player.inventory.helmet != null && player.inventory.helmet.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.WATER_BREATHING, 200, SettingsManager.enchant.getInt("swimmer.level_II.potion_lvl") - 1))
                }
                if (player.inventory.helmet != null && player.inventory.helmet.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.WATER_BREATHING, 200, SettingsManager.enchant.getInt("swimmer.level_III.potion_lvl") - 1))
                }
                return
            }
            if (EnchantmentsManager.swimmer.contains(player.name) && player.inventory.helmet == null || !player.inventory.helmet.hasItemMeta() || !player.inventory.helmet.itemMeta.hasLore() || !player.inventory.helmet.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && !player.inventory.helmet.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && !player.inventory.helmet.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                player.removePotionEffect(PotionEffectType.WATER_BREATHING)
                EnchantmentsManager.swimmer.remove(player.name)
                return
            }
            if (player.inventory.helmet != null && player.inventory.helmet.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, SettingsManager.enchant.getInt("swimmer.level_I.potion_lvl") - 1))
                if (!EnchantmentsManager.swimmer.contains(player.name)) {
                    EnchantmentsManager.swimmer.add(player.name)
                }
            }
            if (player.inventory.helmet != null && player.inventory.helmet.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, SettingsManager.enchant.getInt("swimmer.level_II.potion_lvl") - 1))
                if (!EnchantmentsManager.swimmer.contains(player.name)) {
                    EnchantmentsManager.swimmer.add(player.name)
                }
            }
            if (player.inventory.helmet != null && player.inventory.helmet.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, SettingsManager.enchant.getInt("swimmer.level_III.potion_lvl") - 1))
                if (!EnchantmentsManager.swimmer.contains(player.name)) {
                    EnchantmentsManager.swimmer.add(player.name)
                }
            }
        } catch (ex: Exception) {
        }

    }
}
