/*
 *     Copyright (C) 2017-Present HealPotion
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.BlockBreakEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.util.XMaterial
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


        try {
            val level = IM.getHighestLevel(player, this.name())
            if (level > 0
                    && (blockBreakEvent.block.type == XMaterial.ACACIA_LOG.parseMaterial()
                            || blockBreakEvent.block.type == XMaterial.BIRCH_LOG.parseMaterial()
                            || blockBreakEvent.block.type == XMaterial.DARK_OAK_LOG.parseMaterial()
                            || blockBreakEvent.block.type == XMaterial.OAK_LOG.parseMaterial()
                            || blockBreakEvent.block.type == XMaterial.SPRUCE_LOG.parseMaterial()
                            || blockBreakEvent.block.type == XMaterial.STRIPPED_ACACIA_LOG.parseMaterial()
                            || blockBreakEvent.block.type == XMaterial.STRIPPED_BIRCH_LOG.parseMaterial()
                            || blockBreakEvent.block.type == XMaterial.STRIPPED_DARK_OAK_LOG.parseMaterial()
                            || blockBreakEvent.block.type == XMaterial.STRIPPED_JUNGLE_LOG.parseMaterial()
                            || blockBreakEvent.block.type == XMaterial.STRIPPED_OAK_LOG.parseMaterial()
                            || blockBreakEvent.block.type == XMaterial.STRIPPED_SPRUCE_LOG.parseMaterial())) {
                val list = ArrayList<Material>()
                list.add(XMaterial.ACACIA_LOG.parseMaterial())
                list.add(XMaterial.BIRCH_LOG.parseMaterial())
                list.add(XMaterial.DARK_OAK_LOG.parseMaterial())
                list.add(XMaterial.OAK_LOG.parseMaterial())
                list.add(XMaterial.SPRUCE_LOG.parseMaterial())
                list.add(XMaterial.STRIPPED_ACACIA_LOG.parseMaterial())
                list.add(XMaterial.STRIPPED_BIRCH_LOG.parseMaterial())
                list.add(XMaterial.STRIPPED_DARK_OAK_LOG.parseMaterial())
                list.add(XMaterial.STRIPPED_JUNGLE_LOG.parseMaterial())
                list.add(XMaterial.STRIPPED_OAK_LOG.parseMaterial())
                list.add(XMaterial.STRIPPED_SPRUCE_LOG.parseMaterial())
                list.add(XMaterial.ACACIA_LEAVES.parseMaterial())
                list.add(XMaterial.BIRCH_LEAVES.parseMaterial())
                list.add(XMaterial.DARK_OAK_LEAVES.parseMaterial())
                list.add(XMaterial.JUNGLE_LEAVES.parseMaterial())
                list.add(XMaterial.OAK_LEAVES.parseMaterial())
                list.add(XMaterial.SPRUCE_LEAVES.parseMaterial())
                val iterator = this.getTree(blockBreakEvent.block, list).iterator()
                while (iterator.hasNext()) {
                    iterator.next().breakNaturally()
                }
            }
        } catch (ex: Exception) {
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
