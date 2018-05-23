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

class Jump : Listener {
    @EventHandler
    fun onWalk(playerMoveEvent: PlayerMoveEvent) {
        val player = playerMoveEvent.player
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "jump"))
        try {
            if (SettingsManager.enchant.getBoolean("periodic-potions")) {
                if (player.inventory.boots != null && player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.JUMP, 200, SettingsManager.enchant.getInt("jump.level_I.potion_lvl") - 1))
                }
                if (player.inventory.boots != null && player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.JUMP, 200, SettingsManager.enchant.getInt("jump.level_II.potion_lvl") - 1))
                }
                if (player.inventory.boots != null && player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                    player.addPotionEffect(PotionEffect(PotionEffectType.JUMP, 200, SettingsManager.enchant.getInt("jump.level_III.potion_lvl") - 1))
                }
                return
            }
            if (EnchantmentsManager.jump.contains(player.name) && player.inventory.boots == null || !player.inventory.boots.hasItemMeta() || !player.inventory.boots.itemMeta.hasLore() || !player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && !player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && !player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                player.removePotionEffect(PotionEffectType.JUMP)
                EnchantmentsManager.jump.remove(player.name)
                return
            }
            if (player.inventory.boots != null && player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SettingsManager.enchant.getInt("jump.level_I.potion_lvl") - 1))
                if (!EnchantmentsManager.jump.contains(player.name)) {
                    EnchantmentsManager.jump.add(player.name)
                }
            }
            if (player.inventory.boots != null && player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SettingsManager.enchant.getInt("jump.level_II.potion_lvl") - 1))
                if (!EnchantmentsManager.jump.contains(player.name)) {
                    EnchantmentsManager.jump.add(player.name)
                }
            }
            if (player.inventory.boots != null && player.inventory.boots.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                player.addPotionEffect(PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, SettingsManager.enchant.getInt("jump.level_III.potion_lvl") - 1))
                if (!EnchantmentsManager.jump.contains(player.name)) {
                    EnchantmentsManager.jump.add(player.name)
                }
            }
        } catch (ex: Exception) {
        }

    }
}
