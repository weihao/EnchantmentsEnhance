/*
 *     Copyright (C) 2017-Present HealPot
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

package org.pixeltime.enchantmentsenhance.event.enchantment.gear

import com.sk89q.worldguard.bukkit.WGBukkit
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.BlockBreakEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.DropManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
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


        val level = getLevel(player)
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
            val blocks = arrayOf(
                    XMaterial.ACACIA_LOG.parseMaterial(),
                    XMaterial.BIRCH_LOG.parseMaterial(),
                    XMaterial.DARK_OAK_LOG.parseMaterial(),
                    XMaterial.OAK_LOG.parseMaterial(),
                    XMaterial.SPRUCE_LOG.parseMaterial(),
                    XMaterial.ACACIA_LEAVES.parseMaterial(),
                    XMaterial.BIRCH_LEAVES.parseMaterial(),
                    XMaterial.DARK_OAK_LEAVES.parseMaterial(),
                    XMaterial.JUNGLE_LEAVES.parseMaterial(),
                    XMaterial.OAK_LEAVES.parseMaterial(),
                    XMaterial.SPRUCE_LEAVES.parseMaterial()
            )

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
                block.breakNaturally()
                if (DropManager.chopping.contains(block.type.toString()))
                    if (DropManager.choppingChance > Random().nextDouble()) {
                        DropManager.randomDrop(player, DropManager.choppingLootTable)
                    }
            }
        }
    }

    fun getNearbyBlocks(block: Block, list: List<Material>, set: HashSet<Block>): Set<Block> {
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
}
