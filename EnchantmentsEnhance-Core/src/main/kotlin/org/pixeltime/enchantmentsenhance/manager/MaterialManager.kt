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

package org.pixeltime.enchantmentsenhance.manager

import org.bukkit.Material
import org.pixeltime.enchantmentsenhance.util.XMaterial

class MaterialManager {
    companion object {
        @JvmField
        val sword = arrayListOf(
                XMaterial.DIAMOND_SWORD.parseMaterial(),
                XMaterial.GOLDEN_SWORD.parseMaterial(),
                XMaterial.WOODEN_SWORD.parseMaterial(),
                XMaterial.STONE_SWORD.parseMaterial(),
                XMaterial.IRON_SWORD.parseMaterial())

        @JvmField
        val axe = mutableListOf<Material>()

        @JvmField
        val helmet = arrayListOf(
                XMaterial.DIAMOND_HELMET.parseMaterial(),
                XMaterial.GOLDEN_HELMET.parseMaterial(),
                XMaterial.IRON_HELMET.parseMaterial(),
                XMaterial.LEATHER_HELMET.parseMaterial(),
                XMaterial.CHAINMAIL_HELMET.parseMaterial())
        @JvmField
        val boot = arrayListOf(
                XMaterial.DIAMOND_BOOTS.parseMaterial(),
                XMaterial.IRON_BOOTS.parseMaterial(),
                XMaterial.GOLDEN_BOOTS.parseMaterial(),
                XMaterial.LEATHER_BOOTS.parseMaterial(),
                XMaterial.CHAINMAIL_BOOTS.parseMaterial())
        @JvmField
        val chestplate = arrayListOf(
                XMaterial.DIAMOND_CHESTPLATE.parseMaterial(),
                XMaterial.IRON_CHESTPLATE.parseMaterial(),
                XMaterial.GOLDEN_CHESTPLATE.parseMaterial(),
                XMaterial.LEATHER_CHESTPLATE.parseMaterial(),
                XMaterial.CHAINMAIL_CHESTPLATE.parseMaterial())
        @JvmField
        val pickaxe = mutableListOf<Material>()

        @JvmField
        val hoe = mutableListOf<Material>()

        @JvmField
        val shovel = mutableListOf<Material>()
        @JvmField
        val stoneTypes = mutableListOf<Material>()

        @JvmField
        val armor = mutableListOf<Material>()

        @JvmField
        val weapon = mutableListOf<Material>()

        @JvmField
        val bow = mutableListOf<Material>()

        @JvmStatic
        fun setUp() {
            for (s in SettingsManager.config.getStringList(
                    "material.stoneTypes")) {
                stoneTypes.add(XMaterial.fromString(s).parseMaterial())
            }

            for (s in SettingsManager.config.getStringList(
                    "material.armor")) {
                armor.add(XMaterial.fromString(s).parseMaterial())
            }

            for (s in SettingsManager.config.getStringList(
                    "material.weapon")) {
                weapon.add(XMaterial.fromString(s).parseMaterial())
            }
            for (s in SettingsManager.config.getStringList(
                    "material.bow")) {
                bow.add(XMaterial.fromString(s).parseMaterial())
            }
            for (s in SettingsManager.config.getStringList(
                    "material.hoe")) {
                hoe.add(XMaterial.fromString(s).parseMaterial())
            }
            for (s in SettingsManager.config.getStringList(
                    "material.shovel")) {
                shovel.add(XMaterial.fromString(s).parseMaterial())
            }
            for (s in SettingsManager.config.getStringList(
                    "material.pickaxe")) {
                pickaxe.add(XMaterial.fromString(s).parseMaterial())
            }
            for (s in SettingsManager.config.getStringList(
                    "material.axe")) {
                axe.add(XMaterial.fromString(s).parseMaterial())
            }
        }
    }
}