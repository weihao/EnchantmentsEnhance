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

class Spiked : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "spiked"))
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            try {
                val player = entityDamageByEntityEvent.damager as Player
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(entityDamageByEntityEvent.entity.world).getApplicableRegions(entityDamageByEntityEvent.entity.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                    return
                }
                val armorContents = (entityDamageByEntityEvent.entity as Player).inventory.armorContents
                val length = armorContents.size
                var i = 0
                while (i < length) {
                    val itemStack = armorContents[i]
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                        player.damage(2.0)
                    }
                    if (itemStack!!.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II")) {
                        player.damage(4.0)
                    }
                    ++i
                }
            } catch (ex: Exception) {
            }

        }
    }
}
