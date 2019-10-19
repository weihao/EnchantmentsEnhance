package org.pixeltime.enchantmentsenhance.event.enchantment.axe

import com.lgou2w.ldk.bukkit.compatibility.XMaterial
import com.sk89q.worldguard.bukkit.WGBukkit
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.BlockBreakEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.DropManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import java.util.*

class Lumberjack : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Chops the whole tree in one click", "一下砍倒一棵树")
    }

    override fun lang(): Array<String> {
        return arrayOf("伐木")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onBreak(blockBreakEvent: BlockBreakEvent) {
        if (blockBreakEvent.isCancelled) {
            return
        }
        val player = blockBreakEvent.player
        val blocks = arrayOf(
                XMaterial.ACACIA_LOG.toBukkit(),
                XMaterial.BIRCH_LOG.toBukkit(),
                XMaterial.DARK_OAK_LOG.toBukkit(),
                XMaterial.OAK_LOG.toBukkit(),
                XMaterial.SPRUCE_LOG.toBukkit(),
                XMaterial.ACACIA_LEAVES.toBukkit(),
                XMaterial.BIRCH_LEAVES.toBukkit(),
                XMaterial.DARK_OAK_LEAVES.toBukkit(),
                XMaterial.JUNGLE_LEAVES.toBukkit(),
                XMaterial.OAK_LEAVES.toBukkit(),
                XMaterial.SPRUCE_LEAVES.toBukkit()
        )
        val level = getLevel(player)
        if (level > 0
                && (blocks.contains(blockBreakEvent.block.type))) {
            val list = ArrayList<Material>()

            for (block in blocks) {
                if (!list.contains(block)) {
                    list.add(block)
                }
            }

            val iterator = getNearbyBlocks(blockBreakEvent.block, list, HashSet()).iterator()
            while (iterator.hasNext()) {
                val block = iterator.next()
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block)) {
                    return
                }
                if (DropManager.chopping.contains(block.type)) {
                    if (DropManager.choppingChance > Random().nextDouble()) {
                        DropManager.randomDrop(player, DropManager.choppingLootTable)
                    }
                }
                block.breakNaturally()
            }
        }
    }

    fun getNearbyBlocks(block: Block, list: List<Material>, set: HashSet<Block>): Set<Block> {
        for (i in -1..1) {
            for (j in -1..1) {
                for (k in -1..1) {
                    val block2 = block.location.clone().add(i.toDouble(), j.toDouble(), k.toDouble()).block
                    if (block2 != null && !set.contains(block2) && list.contains(block2.type) && set.size < 150) {
                        set.add(block2)
                        set.addAll(this.getNearbyBlocks(block2, list, set))
                    }
                }
            }
        }
        return set
    }
}
