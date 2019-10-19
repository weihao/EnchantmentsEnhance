package org.pixeltime.enchantmentsenhance.event.enchantment.pickaxe

import com.lgou2w.ldk.bukkit.compatibility.XMaterial
import org.bukkit.ChatColor
import org.bukkit.block.CreatureSpawner
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener


class Touch : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf(
                "You can harvest mob spawners.",
                "你可以敲下刷怪笼")
    }

    override fun lang(): Array<String> {
        return arrayOf("触及")
    }

    @EventHandler
    fun onBlockBreak(blockBreakEvent: BlockBreakEvent) {
        val player = blockBreakEvent.player
        val block = blockBreakEvent.block
        val level = getLevel(player)
        if (level > 0 && block.type == XMaterial.SPAWNER.toBukkit()) {
            val creatureSpawner = block.state as CreatureSpawner
            val itemStack = ItemStack(creatureSpawner.type, 1, block.data.toShort())
            val itemMeta = itemStack.itemMeta
            itemMeta!!.setDisplayName("${ChatColor.AQUA}${creatureSpawner.creatureTypeName.toUpperCase()}")
            itemStack.itemMeta = itemMeta
            if (!blockBreakEvent.isCancelled) {
                block.location.world!!.dropItem(block.location, itemStack)
            }
        }
    }

    @EventHandler
    fun onPlace(blockPlaceEvent: BlockPlaceEvent) {
        val block = blockPlaceEvent.block
        if (block.type == XMaterial.SPAWNER.toBukkit()) {
            val item = blockPlaceEvent.itemInHand
            if (item.hasItemMeta() && item.itemMeta!!.hasDisplayName()) {
                try {
                    val entity = EntityType.valueOf(ChatColor.stripColor(item.itemMeta!!.displayName.toUpperCase())!!.trim())
                    val blockState = block.getState()
                    val spawner = blockState as CreatureSpawner
                    spawner.spawnedType = entity
                    blockState.update()
                } catch (e: IllegalArgumentException) {
                    // Unknown entity type.
                }
            }
        }
    }
}
