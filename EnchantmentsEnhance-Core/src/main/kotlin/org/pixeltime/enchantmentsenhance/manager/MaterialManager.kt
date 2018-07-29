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

class MM {
    companion object {
        @JvmField
        val sword = arrayListOf(Material.DIAMOND_SWORD, Material.GOLD_SWORD, Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD)
        @JvmField
        val axe = arrayListOf(Material.DIAMOND_AXE, Material.IRON_AXE, Material.WOOD_AXE, Material.STONE_AXE, Material.GOLD_AXE)
        @JvmField
        val helmet = arrayListOf(Material.DIAMOND_HELMET, Material.GOLD_HELMET, Material.IRON_HELMET, Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET)
        @JvmField
        val boot = arrayListOf(Material.DIAMOND_BOOTS, Material.IRON_BOOTS, Material.GOLD_BOOTS, Material.LEATHER_BOOTS, Material.CHAINMAIL_BOOTS)
        @JvmField
        val chestplate = arrayListOf(Material.DIAMOND_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLD_CHESTPLATE, Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE)
        @JvmField
        val pick = arrayListOf(Material.DIAMOND_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.STONE_PICKAXE, Material.WOOD_PICKAXE)
        @JvmField
        val hoe = arrayListOf(Material.DIAMOND_HOE, Material.IRON_HOE, Material.GOLD_HOE, Material.STONE_HOE, Material.WOOD_HOE)

        @JvmField
        val stoneTypes = mutableListOf<Material>()

        @JvmField
        val armor = mutableListOf<Material>()

        @JvmField
        val weapon = mutableListOf<Material>()

        @JvmStatic
        fun setup() {
            for (s in SettingsManager.config.getStringList(
                    "material.stoneTypes")) {
                stoneTypes.add(Material.getMaterial(s))
            }

            for (s in SettingsManager.config.getStringList(
                    "material.armor")) {
                armor.add(Material.getMaterial(s))
            }

            for (s in SettingsManager.config.getStringList(
                    "material.weapon")) {
                weapon.add(Material.getMaterial(s))
            }
        }
    }
}