package org.pixeltime.enchantmentsenhance.api

import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.gui.menu.icons.ItemIcon
import org.pixeltime.enchantmentsenhance.manager.ConfigManager
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat
import org.pixeltime.enchantmentsenhance.util.Util
import java.util.*

class API : EnchantmentsEnhanceAPI {
    override fun addAdvice(player: String, level: Int) {
        if (level != 0) {
            PlayerStat.getPlayerStats(player).valks.add(level)
            Collections.sort(PlayerStat.getPlayerStats(player)!!.valks, Collections.reverseOrder())
        }
    }

    override fun getNumberOfStone(playerName: String, stoneId: Int): Int {
        return ItemIcon.getOneStoneCountAsInt(playerName, stoneId)
    }

    override fun addCustomEnchant(item: ItemStack, enchantment: String, level: Int): Boolean {
        return ItemManager.applyEnchantmentToItem(item, enchantment, level)
    }


    override fun setItem(player: String, type: Int, level: Int) {
        try {
            PlayerStat.getPlayerStats(player)!!.items[type] = level
        } catch (e: Exception) {
            Main.getMain().logger.info(
                    "Error when setting the player data.")
        }

    }


    override fun addItem(player: String, type: Int, levelsToAdd: Int) {
        val newLevel = getItem(player, type) + levelsToAdd
        setItem(player, type, newLevel)
    }


    override fun getItem(player: String, type: Int): Int {
        return if (PlayerStat.getPlayerStats(player) == null) 0 else PlayerStat.getPlayerStats(player)!!.items[type]
    }


    override fun resetFailstack(player: String) {
        setFailstack(player, 0)
    }


    override fun setFailstack(player: String, level: Int) {
        PlayerStat.getPlayerStats(player)!!.failstack = level
    }


    override fun addFailstack(player: String, levelsToAdd: Int) {
        val newLevel = getFailstack(player) + levelsToAdd
        setFailstack(player, newLevel)
    }


    override fun getFailstack(player: String): Int {
        return if (PlayerStat.getPlayerStats(player) == null) 0 else PlayerStat.getPlayerStats(player)!!.failstack
    }


    override fun hasFailstack(player: String): Boolean {
        return getFailstack(player) > 0
    }


    override fun getChance(playerName: String, enchantLevel: Int): Double {
        var failstack = getFailstack(playerName)
        val maximumFailstack = ConfigManager.maximumFailstackApplied[enchantLevel]
        val baseChance = ConfigManager.baseChance[enchantLevel]
        val increasePerFailstack = ConfigManager.chanceIncreasePerFailstack[enchantLevel]

        if (failstack > maximumFailstack) {
            failstack = maximumFailstack
        }
        return if (increasePerFailstack == -1.0 || maximumFailstack == -1) {
            baseChance / 100
        } else {
            (baseChance + failstack * increasePerFailstack) / 100
        }
    }

    /**
     * Adds a player's failstack to the HashMap storage.
     *
     * @param player Targeted player.
     */
    override fun addAdvice(player: String) {
        val level = getFailstack(player)
        if (level != 0) {
            PlayerStat.getPlayerStats(player)!!.valks.add(level)
            Collections.sort(PlayerStat.getPlayerStats(player)!!.valks, Collections.reverseOrder())
            Util.sendMessage(SettingsManager.lang.getString("save.createFailstack")
            !!.replace("%failstack%".toRegex(), Integer.toString(getFailstack(
                    player))), player)
            resetFailstack(player)
        }
    }

    /**
     * Adds a specific level of advice to a player.
     *
     * @param player Targeted player.
     */
    override fun getEnchantmentMaxLevel(ench: String): Int {
        return if (SettingsManager.enchant.getConfigurationSection(ench.toLowerCase()) == null) {
            1
        } else {
            SettingsManager.enchant.getConfigurationSection(ench.toLowerCase())!!.getKeys(false).size
        }
    }

}
