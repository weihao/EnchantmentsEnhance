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
import org.pixeltime.enchantmentsenhance.util.Materials

class MM {
    companion object {
        @JvmField
        val sword = arrayListOf(Materials.DIAMOND_SWORD.bukkitMaterial(), Materials.GOLD_SWORD.bukkitMaterial(), Materials.WOOD_SWORD.bukkitMaterial(), Materials.STONE_SWORD.bukkitMaterial(), Materials.IRON_SWORD)
        @JvmField
        val axe = arrayListOf(Materials.DIAMOND_AXE.bukkitMaterial(), Materials.IRON_AXE.bukkitMaterial(), Materials.WOOD_AXE.bukkitMaterial(), Materials.STONE_AXE.bukkitMaterial(), Materials.GOLD_AXE)
        @JvmField
        val helmet = arrayListOf(Materials.DIAMOND_HELMET.bukkitMaterial(), Materials.GOLD_HELMET.bukkitMaterial(), Materials.IRON_HELMET.bukkitMaterial(), Materials.LEATHER_HELMET.bukkitMaterial(), Materials.CHAINMAIL_HELMET)
        @JvmField
        val boot = arrayListOf(Materials.DIAMOND_BOOTS.bukkitMaterial(), Materials.IRON_BOOTS.bukkitMaterial(), Materials.GOLD_BOOTS.bukkitMaterial(), Materials.LEATHER_BOOTS.bukkitMaterial(), Materials.CHAINMAIL_BOOTS)
        @JvmField
        val chestplate = arrayListOf(Materials.DIAMOND_CHESTPLATE.bukkitMaterial(), Materials.IRON_CHESTPLATE.bukkitMaterial(), Materials.GOLD_CHESTPLATE.bukkitMaterial(), Materials.LEATHER_CHESTPLATE.bukkitMaterial(), Materials.CHAINMAIL_CHESTPLATE)
        @JvmField
        val pick = arrayListOf(Materials.DIAMOND_PICKAXE.bukkitMaterial(), Materials.IRON_PICKAXE.bukkitMaterial(), Materials.GOLD_PICKAXE.bukkitMaterial(), Materials.STONE_PICKAXE.bukkitMaterial(), Materials.WOOD_PICKAXE)
        @JvmField
        val hoe = arrayListOf(Materials.DIAMOND_HOE.bukkitMaterial(), Materials.IRON_HOE.bukkitMaterial(), Materials.GOLD_HOE.bukkitMaterial(), Materials.STONE_HOE.bukkitMaterial(), Materials.WOOD_HOE)

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