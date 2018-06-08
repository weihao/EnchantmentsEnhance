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

package org.pixeltime.enchantmentsenhance.api

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.event.blacksmith.SecretBook
import org.pixeltime.enchantmentsenhance.event.blackspirit.Failstack
import org.pixeltime.enchantmentsenhance.event.inventory.Inventory
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import java.util.*

class API {
    companion object {
        @JvmStatic
        fun addStone(player: Player, stoneType: Int, numberOfStones: Int) {
            Inventory.addLevel(player, stoneType, numberOfStones)
        }

        @JvmStatic
        fun getStones(player: Player): IntArray {
            return Inventory.getPlayer(player)
        }

        @JvmStatic
        fun getCurrentFailstack(player: Player): Int {
            return Failstack.getLevel(player)
        }

        @JvmStatic
        fun getValkStorage(): HashMap<String, MutableList<Int>> {
            return SecretBook.getStorage()
        }

        @JvmStatic
        fun getValks(player: Player): MutableList<Int>? {
            return SecretBook.getStorage()[player.name]
        }

        @JvmStatic
        fun AddCustomEnchant(item: ItemStack, enchantment: String, level: Int) {
            ItemManager.applyEnchantmentToItem(item, enchantment, level)
        }

    }
}