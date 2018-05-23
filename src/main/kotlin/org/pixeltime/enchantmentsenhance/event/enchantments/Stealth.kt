package org.pixeltime.enchantmentsenhance.event.enchantment

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.ChatColor
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Stealth : Listener {
    @EventHandler
    fun onSneak(playerToggleSneakEvent: PlayerToggleSneakEvent) {
        val player = playerToggleSneakEvent.player
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "stealth"))
        if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.world).getApplicableRegions(player.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
            return
        }
        val n = (Math.random() * 100.0).toInt()
        val armorContents = player.inventory.armorContents
        try {
            val array: Array<ItemStack>
            val length = (array = armorContents).size
            var i = 0
            while (i < length) {
                val itemStack = array[i]
                if (itemStack != null) {
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("stealth.level_I.chance")) {
                        val int1 = SettingsManager.enchant.getInt("stealth.level_I.radius")
                        for (entity in player.getNearbyEntities(int1.toDouble(), int1.toDouble(), int1.toDouble())) {
                            if (entity is Player) {
                                entity.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("stealth.level_I.duration") * 20, 0))
                            }
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("stealth.level_II.chance")) {
                        val int2 = SettingsManager.enchant.getInt("stealth.level_II.radius")
                        for (entity2 in player.getNearbyEntities(int2.toDouble(), int2.toDouble(), int2.toDouble())) {
                            if (entity2 is Player) {
                                entity2.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("stealth.level_II.duration") * 20, 0))
                            }
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("stealth.level_III.chance")) {
                        val int3 = SettingsManager.enchant.getInt("stealth.level_III.radius")
                        for (entity3 in player.getNearbyEntities(int3.toDouble(), int3.toDouble(), int3.toDouble())) {
                            if (entity3 is Player) {
                                entity3.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("stealth.level_III.duration") * 20, 0))
                            }
                        }
                    }
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " IV") && n < SettingsManager.enchant.getInt("stealth.level_IV.chance")) {
                        val int4 = SettingsManager.enchant.getInt("stealth.level_IV.radius")
                        for (entity4 in player.getNearbyEntities(int4.toDouble(), int4.toDouble(), int4.toDouble())) {
                            if (entity4 is Player) {
                                entity4.addPotionEffect(PotionEffect(PotionEffectType.BLINDNESS, SettingsManager.enchant.getInt("stealth.level_IV.duration") * 20, 0))
                            }
                        }
                    }
                }
                ++i
            }
        } catch (ex: Exception) {
        }

    }
}
