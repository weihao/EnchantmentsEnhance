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

package org.pixeltime.enchantmentsenhance.event.enchantment.axe

import com.sk89q.worldguard.bukkit.WGBukkit
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.BlockBreakEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.DropManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import com.lgou2w.ldk.bukkit.compatibility.XMaterial
import java.util.*

class Pumpking : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Harvests all the pumpkin and melon.", "一下收成南瓜和西瓜")
    }

    override fun lang(): Array<String> {
        return arrayOf("瓜王")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onBreak(blockBreakEvent: BlockBreakEvent) {
        if (blockBreakEvent.isCancelled) {
            return
        }
        val player = blockBreakEvent.player
        val level = getLevel(player)
        if (level > 0
                && (blockBreakEvent.block.type == XMaterial.PUMPKIN.toBukkit()
                        || blockBreakEvent.block.type == XMaterial.MELON.toBukkit())) {
            val list = ArrayList<Material>()

            if (!list.contains(blockBreakEvent.block.type)) {
                list.add(blockBreakEvent.block.type)
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
        for (x in -1..1) {
            for (z in -1..1) {
                val block2 = block.location.clone().add(x.toDouble(), 0.0, z.toDouble()).block
                if (block2 != null && !set.contains(block2) && list.contains(block2.type)) {
                    set.add(block2)
                    set.addAll(this.getNearbyBlocks(block2, list, set))
                }
            }
        }
        return set
    }
}
