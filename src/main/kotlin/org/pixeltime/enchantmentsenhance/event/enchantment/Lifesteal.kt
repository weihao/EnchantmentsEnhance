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
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Lifesteal : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "lifesteal"))

    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            val player = entityDamageByEntityEvent.damager as Player
            if (entityDamageByEntityEvent.isCancelled) {
                return
            }
            if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.entity.world).getApplicableRegions(entityDamageByEntityEvent.entity.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                return
            }

            try {
                val armorContents = player.inventory.armorContents + IM.getAccessorySlots(player)
                for (i in armorContents.indices) {
                    val itemStack = armorContents[i]
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.hasLore()) {
                        val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                        if (level > 0 && (Math.random() * 100.0).toInt() < SettingsManager.enchant.getInt("lifesteal.$level.chance")) {
                            player.health = player.health + SettingsManager.enchant.getInt("lifesteal.$level.health")
                        }
                    }
                }
            } catch (ex: Exception) {
            }
        }
    }
}

