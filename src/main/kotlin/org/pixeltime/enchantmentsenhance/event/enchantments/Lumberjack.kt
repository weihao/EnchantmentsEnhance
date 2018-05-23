package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

import java.util.*

class Lumberjack : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onBreak(blockBreakEvent: BlockBreakEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "lumberjack"))
        if (blockBreakEvent.isCancelled) {
            return
        }
        val player = blockBreakEvent.player
        if (player.itemInHand != null && player.itemInHand.hasItemMeta() && player.itemInHand.itemMeta.hasLore() && player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I") && blockBreakEvent.block.type == Material.LOG) {
            val list = ArrayList<Material>()
            list.add(Material.LOG)
            list.add(Material.LEAVES)
            val iterator = this.getTree(blockBreakEvent.block, list).iterator()
            while (iterator.hasNext()) {
                iterator.next().breakNaturally()
            }
        }
    }

    private fun getNearbyBlocks(block: Block, list: List<Material>, set: HashSet<Block>): Set<Block> {
        for (i in -1..1) {
            for (j in -1..1) {
                for (k in -1..1) {
                    val block2 = block.location.clone().add(i.toDouble(), j.toDouble(), k.toDouble()).block
                    if (block2 != null && !set.contains(block2) && list.contains(block2.type)) {
                        set.add(block2)
                        set.addAll(this.getNearbyBlocks(block2, list, set))
                    }
                }
            }
        }
        return set
    }

    fun getTree(block: Block, list: List<Material>): Set<Block> {
        return this.getNearbyBlocks(block, list, HashSet())
    }
}
