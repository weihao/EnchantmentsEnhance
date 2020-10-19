package org.pixeltime.enchantmentsenhance.manager

import com.lgou2w.ldk.bukkit.compatibility.XMaterial
import org.bukkit.Material

class MaterialManager {
    companion object {
        @JvmField
        val sword = arrayListOf(
            XMaterial.DIAMOND_SWORD.toBukkit(),
            XMaterial.GOLDEN_SWORD.toBukkit(),
            XMaterial.WOODEN_SWORD.toBukkit(),
            XMaterial.STONE_SWORD.toBukkit(),
            XMaterial.IRON_SWORD.toBukkit()
        )

        @JvmField
        val axe = mutableListOf<Material>()

        @JvmField
        val helmet = arrayListOf(
            XMaterial.DIAMOND_HELMET.toBukkit(),
            XMaterial.GOLDEN_HELMET.toBukkit(),
            XMaterial.IRON_HELMET.toBukkit(),
            XMaterial.LEATHER_HELMET.toBukkit(),
            XMaterial.CHAINMAIL_HELMET.toBukkit()
        )

        @JvmField
        val boot = arrayListOf(
            XMaterial.DIAMOND_BOOTS.toBukkit(),
            XMaterial.IRON_BOOTS.toBukkit(),
            XMaterial.GOLDEN_BOOTS.toBukkit(),
            XMaterial.LEATHER_BOOTS.toBukkit(),
            XMaterial.CHAINMAIL_BOOTS.toBukkit()
        )

        @JvmField
        val chestplate = arrayListOf(
            XMaterial.DIAMOND_CHESTPLATE.toBukkit(),
            XMaterial.IRON_CHESTPLATE.toBukkit(),
            XMaterial.GOLDEN_CHESTPLATE.toBukkit(),
            XMaterial.LEATHER_CHESTPLATE.toBukkit(),
            XMaterial.CHAINMAIL_CHESTPLATE.toBukkit()
        )

        @JvmField
        val pickaxe = mutableListOf<Material>()

        @JvmField
        val hoe = mutableListOf<Material>()

        @JvmField
        val shovel = mutableListOf<Material>()

        @JvmField
        val knife = mutableListOf<Material>()

        @JvmField
        val rod = mutableListOf<Material>()

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
                "material.stoneTypes"
            )) {
                stoneTypes.add(XMaterial.searchByType(s)!!.toBukkit())
            }

            for (s in SettingsManager.config.getStringList(
                "material.armor"
            )) {
                armor.add(XMaterial.searchByType(s)!!.toBukkit())
            }

            for (s in SettingsManager.config.getStringList(
                "material.weapon"
            )) {
                weapon.add(XMaterial.searchByType(s)!!.toBukkit())
            }
            for (s in SettingsManager.config.getStringList(
                "material.bow"
            )) {
                bow.add(XMaterial.searchByType(s)!!.toBukkit())
            }
            for (s in SettingsManager.config.getStringList(
                "material.hoe"
            )) {
                hoe.add(XMaterial.searchByType(s)!!.toBukkit())
            }
            for (s in SettingsManager.config.getStringList(
                "material.shovel"
            )) {
                shovel.add(XMaterial.searchByType(s)!!.toBukkit())
            }
            for (s in SettingsManager.config.getStringList(
                "material.knife"
            )) {
                knife.add(XMaterial.searchByType(s)!!.toBukkit())
            }
            for (s in SettingsManager.config.getStringList(
                "material.rod"
            )) {
                rod.add(XMaterial.searchByType(s)!!.toBukkit())
            }
            for (s in SettingsManager.config.getStringList(
                "material.pickaxe"
            )) {
                pickaxe.add(XMaterial.searchByType(s)!!.toBukkit())
            }
            for (s in SettingsManager.config.getStringList(
                "material.axe"
            )) {
                axe.add(XMaterial.searchByType(s)!!.toBukkit())
            }
        }
    }
}
