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

package org.pixeltime.enchantmentsenhance.manager

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.Main


class IM {
    companion object {
        val left_ring = Main.getMain().config.getInt("accessory.left_ring")
        val right_ring = Main.getMain().config.getInt("accessory.right_ring")
        val left_earring = Main.getMain().config.getInt("accessory.left_earring")
        val right_earring = Main.getMain().config.getInt("accessory.right_earring")
        val necklace = Main.getMain().config.getInt("accessory.necklace")
        val belt = Main.getMain().config.getInt("accessory.belt")

        @JvmStatic
        fun getAccessorySlots(player: Player): Array<ItemStack> {
            val inv = player.inventory

            val accessory = mutableListOf<ItemStack>()
            if (inv.getItem(left_ring) != null) {
                accessory.add(inv.getItem(left_ring))
            }
            if (inv.getItem(right_ring) != null) {
                accessory.add(inv.getItem(right_ring))
            }
            if (inv.getItem(left_earring) != null) {
                accessory.add(inv.getItem(left_earring))
            }
            if (inv.getItem(right_earring) != null) {
                accessory.add(inv.getItem(right_earring))
            }
            if (inv.getItem(necklace) != null) {
                accessory.add(inv.getItem(necklace))
            }
            if (inv.getItem(belt) != null) {
                accessory.add(inv.getItem(belt))
            }
            return accessory.toTypedArray()
        }
    }
}