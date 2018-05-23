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

class Health_Boost : Listener {
    @EventHandler
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "health_boost"))
        try {
            if (EnchantmentsManager.health.contains(player.name) && player.inventory.chestplate == null || !player.inventory.chestplate.hasItemMeta() || !player.inventory.chestplate.itemMeta.hasLore() || !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && !player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                player.removePotionEffect(PotionEffectType.HEALTH_BOOST)
                EnchantmentsManager.health.remove(player.name)
                return
            }
            if (player.inventory.chestplate != null && player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, SettingsManager.enchant.getInt("health_boost.level_I.potion_lvl") - 1))
                if (!EnchantmentsManager.health.contains(player.name)) {
                    EnchantmentsManager.health.add(player.name)
                }
            }
            if (player.inventory.chestplate != null && player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, SettingsManager.enchant.getInt("health_boost.level_II.potion_lvl") - 1))
                if (!EnchantmentsManager.health.contains(player.name)) {
                    EnchantmentsManager.health.add(player.name)
                }
            }
            if (player.inventory.chestplate != null && player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, SettingsManager.enchant.getInt("health_boost.level_III.potion_lvl") - 1))
                if (!EnchantmentsManager.health.contains(player.name)) {
                    EnchantmentsManager.health.add(player.name)
                }
            }
        } catch (ex: Exception) {
        }

    }
}
