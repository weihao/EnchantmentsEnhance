package org.pixeltime.enchantmentsenhance.manager

import org.bukkit.Material
import java.util.ArrayList

class MM {
    companion object {
        @JvmField
        val sword = arrayOf(Material.DIAMOND_SWORD, Material.GOLD_SWORD, Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD)
        @JvmField
        val axe = arrayOf(Material.DIAMOND_AXE, Material.IRON_AXE, Material.WOOD_AXE, Material.STONE_AXE, Material.GOLD_AXE)
        @JvmField
        val helmet = arrayOf(Material.DIAMOND_HELMET, Material.GOLD_HELMET, Material.IRON_HELMET, Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET)
        @JvmField
        val boot = arrayOf(Material.DIAMOND_BOOTS, Material.IRON_BOOTS, Material.GOLD_BOOTS, Material.LEATHER_BOOTS, Material.CHAINMAIL_BOOTS)
        @JvmField
        val chestplate = arrayOf(Material.DIAMOND_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLD_CHESTPLATE, Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE)
        @JvmField
        val pick = arrayOf(Material.DIAMOND_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.STONE_PICKAXE, Material.WOOD_PICKAXE)
        @JvmField
        val hoe = arrayOf(Material.DIAMOND_HOE, Material.IRON_HOE, Material.GOLD_HOE, Material.STONE_HOE, Material.WOOD_HOE)
        @JvmField
        var stoneTypes: Array<Material>? = null
        @JvmField
        var armor: Array<Material>? = null
        @JvmField
        var weapon: Array<Material>? = null
        @JvmStatic
        fun setup() {
            val temp = ArrayList<Material>()
            for (s in SettingsManager.config.getStringList(
                    "material.stoneTypes")) {
                temp.add(Material.getMaterial(s))
            }
            stoneTypes = temp.toTypedArray()
            temp.clear()

            for (s in SettingsManager.config.getStringList(
                    "material.armor")) {
                temp.add(Material.getMaterial(s))
            }
            armor = temp.toTypedArray()
            temp.clear()

            for (s in SettingsManager.config.getStringList(
                    "material.weapon")) {
                temp.add(Material.getMaterial(s))
            }
            weapon = temp.toTypedArray()
            temp.clear()
        }
    }
}