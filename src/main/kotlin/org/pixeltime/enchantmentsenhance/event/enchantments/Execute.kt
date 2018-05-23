package org.pixeltime.enchantmentsenhance.event.enchantment

import com.sk89q.worldguard.bukkit.WGBukkit
import com.sk89q.worldguard.protection.flags.DefaultFlag
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Execute : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "execute"))
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            val player2 = entityDamageByEntityEvent.entity as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player2.world).getApplicableRegions(player2.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                return
            }
            val n = (Math.random() * 100.0).toInt()
            try {
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("execute.level_I.chance") && player.isSneaking) {
                    entityDamageByEntityEvent.damage = entityDamageByEntityEvent.damage * 2.0
                }
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("execute.level_II.chance") && player.isSneaking) {
                    entityDamageByEntityEvent.damage = entityDamageByEntityEvent.damage * 2.0
                }
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("execute.level_III.chance") && player.isSneaking) {
                    entityDamageByEntityEvent.damage = entityDamageByEntityEvent.damage * 2.0
                }
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " IV") && n < SettingsManager.enchant.getInt("execute.level_IV.chance") && player.isSneaking) {
                    entityDamageByEntityEvent.damage = entityDamageByEntityEvent.damage * 2.0
                }
                if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " V") && n < SettingsManager.enchant.getInt("execute.level_V.chance") && player.isSneaking) {
                    entityDamageByEntityEvent.damage = entityDamageByEntityEvent.damage * 2.0
                }
            } catch (ex: Exception) {
            }

        }
    }
}
