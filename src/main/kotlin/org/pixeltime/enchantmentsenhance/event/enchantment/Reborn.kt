package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Reborn : Listener {
    @EventHandler
    fun onDeath(playerDeathEvent: PlayerDeathEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "reborn"))
        val entity = playerDeathEvent.entity
        if (entity.killer is Player) {
            val killer = entity.killer
            try {
                if (killer.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                    killer.addPotionEffect(PotionEffect(PotionEffectType.ABSORPTION, SettingsManager.enchant.getInt("reborn.level_I.absorption.duration") * 20, SettingsManager.enchant.getInt("reborn.level_I.absorption.potion_lvl") - 1))
                    killer.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, SettingsManager.enchant.getInt("reborn.level_I.regeneration.duration") * 20, SettingsManager.enchant.getInt("reborn.level_I.regeneration.potion_lvl") - 1))
                }
                if (killer.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II")) {
                    killer.addPotionEffect(PotionEffect(PotionEffectType.ABSORPTION, SettingsManager.enchant.getInt("reborn.level_II.absorption.duration") * 20, SettingsManager.enchant.getInt("reborn.level_II.absorption.potion_lvl") - 1))
                    killer.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, SettingsManager.enchant.getInt("reborn.level_II.regeneration.duration") * 20, SettingsManager.enchant.getInt("reborn.level_II.regeneration.potion_lvl") - 1))
                }
                if (killer.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III")) {
                    killer.addPotionEffect(PotionEffect(PotionEffectType.ABSORPTION, SettingsManager.enchant.getInt("reborn.level_III.absorption.duration") * 20, SettingsManager.enchant.getInt("reborn.level_III.absorption.potion_lvl") - 1))
                    killer.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, SettingsManager.enchant.getInt("reborn.level_III.regeneration.duration") * 20, SettingsManager.enchant.getInt("reborn.level_III.regeneration.potion_lvl") - 1))
                }
            } catch (ex: Exception) {
            }

        }
    }
}
