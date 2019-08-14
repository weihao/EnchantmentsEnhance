package org.pixeltime.enchantmentsenhance.manager

import com.lgou2w.ldk.bukkit.compatibility.XMaterial
import org.bukkit.Material
import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.util.Util
import java.util.Random

class DropManager {


    companion object {
        @JvmStatic
        fun setUp() {
            SettingsManager.config.getStringList("lifeskill.mining").forEach {
                try {
                    val mat = XMaterial.searchByType(it)!!.toBukkit()
                    if (!mining.contains(mat)) {
                        mining.add(mat)
                    }
                } catch (ex: NullPointerException) {
                    Main.getMain().logger.warning("Error trying to add $it to mining lifeskill.")
                }
            }
            SettingsManager.config.getStringList("lifeskill.chopping").forEach {
                try {
                    val mat = (XMaterial.searchByType(it)!!.toBukkit())
                    if (!chopping.contains(mat)) {
                        chopping.add(mat)
                    }
                } catch (ex: NullPointerException) {
                    Main.getMain().logger.warning("Error trying to add $it to chopping lifeskill.")
                }
            }
        }

        @JvmStatic
        val mining: ArrayList<Material> = ArrayList()
        @JvmStatic
        val chopping: ArrayList<Material> = ArrayList()
        @JvmField
        val miningChance = SettingsManager.config.getDouble(
                "reward.mining.chance")
        @JvmField
        val choppingChance = SettingsManager.config.getDouble(
                "reward.chopping.chance")
        @JvmField
        val fishingChance = SettingsManager.config.getDouble(
                "reward.fishing.chance")
        @JvmField
        val killingChance = SettingsManager.config.getDouble(
                "reward.killing.chance")
        @JvmField
        val breedingChance = SettingsManager.config.getDouble(
                "reward.breeding.chance")
        @JvmField
        val smeltingChance = SettingsManager.config.getDouble(
                "reward.smelting.chance")
        @JvmField
        val miningLootTable = SettingsManager.config
                .getIntegerList("reward.mining.drops")
        @JvmField
        val choppingLootTable = SettingsManager.config
                .getIntegerList("reward.chopping.drops")
        @JvmField
        val fishingLootTable = SettingsManager.config
                .getIntegerList("reward.fishing.drops")
        @JvmField
        val killingLootTable = SettingsManager.config
                .getIntegerList("reward.killing.drops")
        @JvmField
        val breedingLootTable = SettingsManager.config
                .getIntegerList("reward.breeding.drops")
        @JvmField
        val smeltingLootTable = SettingsManager.config
                .getIntegerList("reward.breeding.drops")

        @JvmStatic
        fun randomDrop(player: Player, table: List<Int>) {
            val stoneType = table[((0..table.size).random())]
            Main.getApi().addItem(
                    player.name, stoneType, 1)
            Util.sendMessage(SettingsManager.lang.getString("item.get")!!.replace("%ITEM%", SettingsManager.lang.getString("item.$stoneType")!!), player)
        }

        @JvmStatic
        fun ClosedRange<Int>.random() =
                Random().nextInt(endInclusive - start) + start
    }
}
