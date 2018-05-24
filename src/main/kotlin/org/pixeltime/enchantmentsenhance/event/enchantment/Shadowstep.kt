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
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Shadowstep : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.entity is Player && entityDamageByEntityEvent.damager is Player) {
            val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "shadowstep"))
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
                val armorContents = player.inventory.armorContents
                val length = armorContents.size
                var i = 0
                while (i < length) {
                    val itemStack = armorContents[i]
                    if (itemStack != null) {
                        if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("shadowstep.level_I.chance")) {
                            player.teleport(player2.location.add(player2.location.direction.multiply(SettingsManager.enchant.getInt("shadowstep.level_I.distance") * -1.0)))
                        }
                        if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("shadowstep.level_II.chance")) {
                            player.teleport(player2.location.add(player2.location.direction.multiply(SettingsManager.enchant.getInt("shadowstep.level_II.distance") * -1.0)))
                        }
                        if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("shadowstep.level_III.chance")) {
                            player.teleport(player2.location.add(player2.location.direction.multiply(SettingsManager.enchant.getInt("shadowstep.level_III.distance") * -1.0)))
                        }
                    }
                    ++i
                }
            } catch (ex: Exception) {
            }

        }
    }
}
