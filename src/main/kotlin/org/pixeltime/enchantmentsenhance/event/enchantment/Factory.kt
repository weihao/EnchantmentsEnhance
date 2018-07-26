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
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.manager.IM

class Factory : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Auto makes the block of items", "自动将你挖到的矿物变成矿物块")
    }

    override fun lang(): Array<String> {
        return arrayOf("工厂")
    }


    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onBreak(blockBreakEvent: BlockBreakEvent) {
        val player = blockBreakEvent.player
        val level = IM.getHighestLevel(player, this.name())
        if (level > 0) {
            this.autoBlock(player)
        }
    }

    fun autoBlock(player: Player) {
        try {
            for (itemStack in player.inventory) {
                val n = itemStack.amount / 9
                val n2 = itemStack.amount / 4
                if (n > 0) {
                    if (itemStack.type == Material.EMERALD) {
                        removeItems(player.inventory, Material.EMERALD, n * 9)
                        player.inventory.addItem(ItemStack(Material.EMERALD_BLOCK, n))
                    }
                    if (itemStack.type == Material.DIAMOND) {
                        removeItems(player.inventory, Material.DIAMOND, n * 9)
                        player.inventory.addItem(ItemStack(Material.DIAMOND_BLOCK, n))
                    }
                    if (itemStack.type == Material.IRON_INGOT) {
                        removeItems(player.inventory, Material.IRON_INGOT, n * 9)
                        player.inventory.addItem(ItemStack(Material.IRON_BLOCK, n))
                    }
                    if (itemStack.type == Material.GOLD_INGOT) {
                        removeItems(player.inventory, Material.GOLD_INGOT, n * 9)
                        player.inventory.addItem(ItemStack(Material.GOLD_BLOCK, n))
                    }
                    if (itemStack.type == Material.INK_SACK) {
                        removeItems(player.inventory, Material.INK_SACK, n * 9)
                        player.inventory.addItem(ItemStack(Material.LAPIS_BLOCK, n))
                    }
                    if (itemStack.type == Material.COAL) {
                        removeItems(player.inventory, Material.COAL, n * 9)
                        player.inventory.addItem(ItemStack(Material.COAL_BLOCK, n))
                    }
                    if (itemStack.type == Material.REDSTONE) {
                        removeItems(player.inventory, Material.REDSTONE, n * 9)
                        player.inventory.addItem(ItemStack(Material.REDSTONE_BLOCK, n))
                    }
                }
                if (n2 > 0) {
                    if (itemStack.type == Material.CLAY_BALL) {
                        removeItems(player.inventory, Material.CLAY_BALL, n * 4)
                        player.inventory.addItem(ItemStack(Material.CLAY, n))
                    }
                    if (itemStack.type == Material.SNOW_BALL) {
                        removeItems(player.inventory, Material.SNOW_BALL, n * 9)
                        player.inventory.addItem(ItemStack(Material.SNOW, n))
                    }
                }
            }
        } catch (ex: Exception) {
        }
    }


    companion object {
        fun removeItems(inventory: Inventory, material: Material, num: Int) {
            var n = num
            if (n <= 0) {
                return
            }
            val size = inventory.size
            var i = 0
            while (i < size) {
                val item = inventory.getItem(i)
                if (item != null) {
                    if (material == item.type) {
                        val amount = item.amount - n
                        if (amount > 0) {
                            item.amount = amount
                            break
                        }
                        inventory.clear(i)
                        n = -amount
                        if (n == 0) {
                            break
                        }
                    }
                }
                ++i
            }
        }
    }
}
