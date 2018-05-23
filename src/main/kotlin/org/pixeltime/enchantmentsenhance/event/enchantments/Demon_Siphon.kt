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
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Demon_Siphon : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "demon_siphon"))
    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
          if (entityDamageByEntityEvent.damager is Player) {
            try {
                val player = entityDamageByEntityEvent.damager as Player
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.entity.world).getApplicableRegions(entityDamageByEntityEvent.entity.location).queryState(null, DefaultFlag.PVP) == StateFlag.State.DENY) {
                    return
                }
                if (entityDamageByEntityEvent.entity is Player) {
                    return
                }
                val level = KM.getLevel(translateAlternateColorCodes, player.itemInHand.itemMeta.lore)
                if ((level > 0) && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("demon_siphon.$level.chance")) {
                    if (player.health + SettingsManager.enchant.getInt("demon_siphon.$level.health") > 20.0) {
                        player.health = 20.0
                    } else {
                        player.health = player.health + SettingsManager.enchant.getInt("demon_siphon.$level.health")
                    }
                }
            } catch (ex: Exception) {
            }

        }
    }
}
