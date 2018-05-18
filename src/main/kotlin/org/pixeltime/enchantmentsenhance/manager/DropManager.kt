package org.pixeltime.enchantmentsenhance.manager

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.util.Util
import java.util.*

class DropManager() {
    companion object {
        @JvmField
        val mining = SettingsManager.config.getStringList(
                "lifeskill.mining")
        @JvmField
        val chopping = SettingsManager.config.getStringList(
                "lifeskill.chopping")
        @JvmField
        val fishing = SettingsManager.config.getStringList(
                "lifeskill.fishing")
        @JvmField
        val killing = SettingsManager.config.getStringList(
                "lifeskill.killing")
        @JvmField
        val breeding = SettingsManager.config.getStringList(
                "lifeskill.breeding")
        @JvmField
        val smelting = SettingsManager.config.getStringList(
                "lifeskill.smelting")
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
            org.pixeltime.enchantmentsenhance.event.inventory.Inventory.addLevel(
                    player, stoneType, 1)
            Util.sendMessage(SettingsManager.lang.getString("Item.get") + SettingsManager.lang.getString("Item.$stoneType"), player)
        }

        @JvmStatic
        fun ClosedRange<Int>.random() =
                Random().nextInt(endInclusive - start) + start
    }
}