package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.manager.EnchantmentsManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.events.ArmorEquipEvent

class Reinforced : Listener {
    @EventHandler
    fun onEquip(armorEquipEvent: ArmorEquipEvent) {
        val player = armorEquipEvent.player
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "reinforced"))
        try {
            if (EnchantmentsManager.chestplates.contains(armorEquipEvent.newArmorPiece.type)) {
                val newArmorPiece = armorEquipEvent.newArmorPiece
                if (newArmorPiece.itemMeta.hasLore()) {
                    if (newArmorPiece.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && player.health <= SettingsManager.enchant.getInt("reinforced.level_I.required-health")) {
                        player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, SettingsManager.enchant.getInt("reinforced.level_I.duration") * 20, SettingsManager.enchant.getInt("reinforced.level_I.potion_lvl") - 1))
                    }
                    if (newArmorPiece.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && player.health <= SettingsManager.enchant.getInt("reinforced.level_II.required-health")) {
                        player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, SettingsManager.enchant.getInt("reinforced.level_II.duration") * 20, SettingsManager.enchant.getInt("reinforced.level_II.potion_lvl") - 1))
                    }
                    if (newArmorPiece.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && player.health <= SettingsManager.enchant.getInt("reinforced.level_III.required-health")) {
                        player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, SettingsManager.enchant.getInt("reinforced.level_III.duration") * 20, SettingsManager.enchant.getInt("reinforced.level_III.potion_lvl") - 1))
                    }
                    if (newArmorPiece.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " IV") && player.health <= SettingsManager.enchant.getInt("reinforced.level_IV.required-health")) {
                        player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, SettingsManager.enchant.getInt("reinforced.level_IV.duration") * 20, SettingsManager.enchant.getInt("reinforced.level_IV.potion_lvl") - 1))
                    }
                    if (newArmorPiece.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " V") && player.health <= SettingsManager.enchant.getInt("reinforced.level_V.required-health")) {
                        player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, SettingsManager.enchant.getInt("reinforced.level_V.duration") * 20, SettingsManager.enchant.getInt("reinforced.level_V.potion_lvl") - 1))
                    }
                }
            }
        } catch (ex: Exception) {
        }

    }
}
