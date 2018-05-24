package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.entity.Sheep
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Shearer : Listener {
    @EventHandler
    fun onInteract(playerInteractEvent: PlayerInteractEvent) {
        val player = playerInteractEvent.player
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "shearer"))
        if (playerInteractEvent.action == Action.LEFT_CLICK_AIR && player.itemInHand != null && player.itemInHand.hasItemMeta() && player.itemInHand.itemMeta.hasLore() && player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
            val int1 = SettingsManager.enchant.getInt("shearer.level_I.radius")
            for (entity in player.getNearbyEntities(int1.toDouble(), int1.toDouble(), int1.toDouble())) {
                if (entity is Sheep) {
                    if (entity.isSheared) {
                        continue
                    }
                    entity.isSheared = true
                    entity.world.dropItem(entity.location, ItemStack(Material.WOOL, 1, entity.color.woolData.toShort()))
                }
            }
        }
    }
}
