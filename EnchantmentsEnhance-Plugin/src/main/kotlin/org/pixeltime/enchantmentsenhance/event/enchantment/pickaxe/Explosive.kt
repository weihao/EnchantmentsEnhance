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
/*
* This file is part of Custom Enchantments
* Copyright (C) Taiterio 2015
*
* This program is free software: you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as published by the
* Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
* FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
* for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/
package org.pixeltime.enchantmentsenhance.event.enchantment.pickaxe

import com.sk89q.worldguard.bukkit.WGBukkit
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.BlockBreakEvent
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.DropManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.XMaterial
import java.util.*


class Explosive : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf(
                "When destroying a block that your tool was meant for you cause an explosion that breaks similar blocks arround that.",
                "破坏方块时产生爆炸")
    }

    override fun lang(): Array<String> {
        return arrayOf("爆炸")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player
        val level = getLevel(player)
        if (level > 0) {
            val locations = ArrayList<Location>()
            val locRad = SettingsManager.enchant.getInt("explosive.$level.radius")
            val r = locRad - 1
            val start = r / 2

            val sL = event.block.location
            if (SettingsManager.config.getBoolean("enableExplosionSound")) {
                player.world.createExplosion(sL, 0f) // Create a fake explosion
            }

            sL.x = sL.x - start
            sL.y = sL.y - start
            sL.z = sL.z - start

            for (x in 0 until locRad)
                for (y in 0 until locRad)
                    for (z in 0 until locRad)
//                        if (!(x == 0 && y == 0 && z == 0) && !(x == r && y == 0 && z == 0) && !(x == 0 && y == r && z == 0) && !(x == 0 && y == 0 && z == r) && !(x == r && y == r && z == 0)
//                                && !(x == 0 && y == r && z == r) && !(x == r && y == 0 && z == r) && !(x == r && y == r && z == r))
                        locations.add(Location(sL.world, sL.x + x, sL.y + y, sL.z + z))

            for (loc in locations) {
                val block = loc.block
                if (SettingsManager.enchant.getBoolean("allow-worldguard") && !WGBukkit.getPlugin().canBuild(player, block)) {
                    return
                }
                if (block.type == XMaterial.BEDROCK.parseMaterial()) {
                    return
                }
                if (DropManager.mining.contains(block.type)) {
                    if (DropManager.miningChance > Random().nextDouble()) {
                        DropManager.randomDrop(player, DropManager.miningLootTable)
                    }
                }
                block.breakNaturally()
            }
        }
    }
}
