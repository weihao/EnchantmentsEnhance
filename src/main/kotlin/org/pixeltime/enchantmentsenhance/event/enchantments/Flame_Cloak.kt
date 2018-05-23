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

class Flame_Cloak : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onDamage(entityDamageByEntityEvent: EntityDamageByEntityEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "flame_cloak"))
        if (entityDamageByEntityEvent.damager is Player && entityDamageByEntityEvent.entity is Player) {
            try {
                val player = entityDamageByEntityEvent.damager as Player
                val player2 = entityDamageByEntityEvent.entity as Player
                if (entityDamageByEntityEvent.isCancelled) {
                    return
                }
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && WGBukkit.getRegionManager(player2.world).getApplicableRegions(player2.location).queryState(null, *arrayOf(DefaultFlag.PVP)) == StateFlag.State.DENY) {
                    return
                }
                val armorContents = player2.inventory.armorContents
                val n = (Math.random() * 100.0).toInt()
                val array: Array<ItemStack>
                val length = (array = armorContents).size
                var i = 0
                while (i < length) {
                    val itemStack = array[i]
                    if (itemStack != null && itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && n < SettingsManager.enchant.getInt("flame_cloak.level_I.chance")) {
                        player.fireTicks = SettingsManager.enchant.getInt("flame_cloak.level_I.duration") * 20
                    }
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " II") && n < SettingsManager.enchant.getInt("flame_cloak.level_II.chance")) {
                        player.fireTicks = SettingsManager.enchant.getInt("flame_cloak.level_II.duration") * 20
                    }
                    if (itemStack.hasItemMeta() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " III") && n < SettingsManager.enchant.getInt("flame_cloak.level_III.chance")) {
                        player.fireTicks = SettingsManager.enchant.getInt("flame_cloak.level_III.duration") * 20
                    }
                    ++i
                }
            } catch (ex: Exception) {
            }

        }
    }
}
