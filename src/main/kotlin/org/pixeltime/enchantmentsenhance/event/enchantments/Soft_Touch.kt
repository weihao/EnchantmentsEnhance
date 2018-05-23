package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.CreatureSpawner
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Soft_Touch : Listener {
    @EventHandler
    fun onBlockBreak(blockBreakEvent: BlockBreakEvent) {
        val player = blockBreakEvent.player
        val block = blockBreakEvent.block
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "soft_touch"))
        try {
            if (player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && block.type == Material.MOB_SPAWNER) {
                val creatureSpawner = block.state as CreatureSpawner
                val itemStack = ItemStack(creatureSpawner.type, 1, block.data.toShort())
                val itemMeta = itemStack.itemMeta
                itemMeta.displayName = ChatColor.AQUA.toString() + creatureSpawner.creatureTypeName
                itemStack.itemMeta = itemMeta
                if (!blockBreakEvent.isCancelled) {
                    block.location.world.dropItem(block.location, itemStack)
                }
            }
        } catch (ex: Exception) {
        }

    }

    @EventHandler
    fun onPlace(blockPlaceEvent: BlockPlaceEvent) {
        val block = blockPlaceEvent.block
        try {
            if (block.type == Material.MOB_SPAWNER) {
                (block.state as CreatureSpawner).spawnedType = EntityType.fromName(ChatColor.stripColor(blockPlaceEvent.itemInHand.itemMeta.displayName))
            }
        } catch (ex: Exception) {
        }

    }
}
