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
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Immolation : Listener {
    @EventHandler
    fun onSneak(playerToggleSneakEvent: PlayerToggleSneakEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "immolation"))
        val player = playerToggleSneakEvent.player
        val n = (Math.random() * 100.0).toInt()
        if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.world).getApplicableRegions(player.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
            return
        }
        try {
            if (player.inventory.chestplate.itemMeta.hasLore() && player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("immolation.level_I.chance")) {
                for (entity in player.getNearbyEntities(SettingsManager.enchant.getDouble("immolation.level_I.radius"), SettingsManager.enchant.getDouble("immolation.level_I.radius"), SettingsManager.enchant.getDouble("immolation.level_I.radius"))) {
                    if (entity is Player) {
                        entity.setFireTicks(SettingsManager.enchant.getInt("immolation.level_I.duration") * 20)
                    }
                }
            }
            if (player.inventory.chestplate.itemMeta.hasLore() && player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("immolation.level_II.chance")) {
                for (entity2 in player.getNearbyEntities(SettingsManager.enchant.getDouble("immolation.level_II.radius"), SettingsManager.enchant.getDouble("immolation.level_II.radius"), SettingsManager.enchant.getDouble("immolation.level_II.radius"))) {
                    if (entity2 is Player) {
                        entity2.setFireTicks(SettingsManager.enchant.getInt("immolation.level_II.duration") * 20)
                    }
                }
            }
            if (player.inventory.chestplate.itemMeta.hasLore() && player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("immolation.level_III.chance")) {
                for (entity3 in player.getNearbyEntities(SettingsManager.enchant.getDouble("immolation.level_III.radius"), SettingsManager.enchant.getDouble("immolation.level_III.radius"), SettingsManager.enchant.getDouble("immolation.level_III.radius"))) {
                    if (entity3 is Player) {
                        entity3.setFireTicks(SettingsManager.enchant.getInt("immolation.level_III.duration") * 20)
                    }
                }
            }
            if (player.inventory.chestplate.itemMeta.hasLore() && player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " IV") && n < SettingsManager.enchant.getInt("immolation.level_IV.chance")) {
                for (entity4 in player.getNearbyEntities(SettingsManager.enchant.getDouble("immolation.level_IV.radius"), SettingsManager.enchant.getDouble("immolation.level_IV.radius"), SettingsManager.enchant.getDouble("immolation.level_IV.radius"))) {
                    if (entity4 is Player) {
                        entity4.setFireTicks(SettingsManager.enchant.getInt("immolation.level_IV.duration") * 20)
                    }
                }
            }
            if (player.inventory.chestplate.itemMeta.hasLore() && player.inventory.chestplate.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " V") && n < SettingsManager.enchant.getInt("immolation.level_V.chance")) {
                for (entity5 in player.getNearbyEntities(SettingsManager.enchant.getDouble("immolation.level_V.radius"), SettingsManager.enchant.getDouble("immolation.level_V.radius"), SettingsManager.enchant.getDouble("immolation.level_V.radius"))) {
                    if (entity5 is Player) {
                        entity5.setFireTicks(SettingsManager.enchant.getInt("immolation.level_V.duration") * 20)
                    }
                }
            }
        } catch (ex: Exception) {
        }

    }
}
