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
import org.bukkit.potion.PotionEffect
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Holy_Smite : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "holy_smite"))
        if (entityDamageByEntityEvent.entity is Player && entityDamageByEntityEvent.damager is Player) {
            try {
                val player = entityDamageByEntityEvent.entity as Player
                val player2 = entityDamageByEntityEvent.damager as Player
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player.world).getApplicableRegions(player.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                    return
                }
                val n = (Math.random() * 100.0).toInt()
                if (player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("holy_smite.level_I.chance")) {
                    player.world.strikeLightningEffect(player.location)
                    val iterator = player.activePotionEffects.iterator()
                    while (iterator.hasNext()) {
                        player.removePotionEffect((iterator.next() as PotionEffect).type)
                    }
                }
                if (player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("holy_smite.level_II.chance")) {
                    player.world.strikeLightningEffect(player.location)
                    val iterator2 = player.activePotionEffects.iterator()
                    while (iterator2.hasNext()) {
                        player.removePotionEffect((iterator2.next() as PotionEffect).type)
                    }
                }
                if (player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("holy_smite.level_III.chance")) {
                    player.world.strikeLightningEffect(player.location)
                    val iterator3 = player.activePotionEffects.iterator()
                    while (iterator3.hasNext()) {
                        player.removePotionEffect((iterator3.next() as PotionEffect).type)
                    }
                }
                if (player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " IV") && n < SettingsManager.enchant.getInt("holy_smite.level_IV.chance")) {
                    player.world.strikeLightningEffect(player.location)
                    val iterator4 = player.activePotionEffects.iterator()
                    while (iterator4.hasNext()) {
                        player.removePotionEffect((iterator4.next() as PotionEffect).type)
                    }
                }
                if (player2.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " V") && n < SettingsManager.enchant.getInt("holy_smite.level_V.chance")) {
                    player.world.strikeLightningEffect(player.location)
                    val iterator5 = player.activePotionEffects.iterator()
                    while (iterator5.hasNext()) {
                        player.removePotionEffect((iterator5.next() as PotionEffect).type)
                    }
                }
            } catch (ex: Exception) {
            }

        }
    }
}
