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

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.Main


class IM {
    companion object {
        private val left_ring = Main.getMain().config.getInt("accessory.left_ring")
        private val right_ring = Main.getMain().config.getInt("accessory.right_ring")
        private val left_earring = Main.getMain().config.getInt("accessory.left_earring")
        private val right_earring = Main.getMain().config.getInt("accessory.right_earring")
        private val necklace = Main.getMain().config.getInt("accessory.necklace")
        private val belt = Main.getMain().config.getInt("accessory.belt")
        @JvmStatic
        fun getAccessorySlots(player: Player): List<ItemStack> {
            val inv = player.inventory

            val accessory = arrayListOf<ItemStack>()
            if (left_ring != -1 && inv.getItem(left_ring) != null) {
                accessory.add(inv.getItem(left_ring))
            }
            if (right_ring != -1 && inv.getItem(right_ring) != null) {
                accessory.add(inv.getItem(right_ring))
            }
            if (left_earring != -1 && inv.getItem(left_earring) != null) {
                accessory.add(inv.getItem(left_earring))
            }
            if (right_earring != -1 && inv.getItem(right_earring) != null) {
                accessory.add(inv.getItem(right_earring))
            }
            if (necklace != -1 && inv.getItem(necklace) != null) {
                accessory.add(inv.getItem(necklace))
            }
            if (belt != -1 && inv.getItem(belt) != null) {
                accessory.add(inv.getItem(belt))
            }
            return accessory.filter { it.hasItemMeta() && it.itemMeta.hasLore() && it.itemMeta.lore.isNotEmpty() }
        }

        @JvmStatic
        fun getArmorSlots(player: Player): List<ItemStack> {
            return (player.inventory.armorContents.filter { it != null && it.type != Material.AIR } + player.itemInHand).filter { it.hasItemMeta() && it.itemMeta.hasLore() && it.itemMeta.lore.isNotEmpty() }
        }

        @JvmStatic
        fun getItemList(player: Player): List<ItemStack> {
            val itemList: List<ItemStack> = ArrayList()
            if (SettingsManager.config.getBoolean("slots.enableArmor")) {
                itemList + IM.getArmorSlots(player)
            }
            if (SettingsManager.config.getBoolean("slots.enableAcessory")) {
                itemList + IM.getAccessorySlots(player)
            }
            return itemList
        }
    }
}
