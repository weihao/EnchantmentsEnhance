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
import java.util.*

class Lumberjack : EnchantmentListener() {
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
            if (level > 0 && blockBreakEvent.block.type == Material.LOG) {
                val list = ArrayList<Material>()
                list.add(Material.LOG)
                list.add(Material.LEAVES)
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
