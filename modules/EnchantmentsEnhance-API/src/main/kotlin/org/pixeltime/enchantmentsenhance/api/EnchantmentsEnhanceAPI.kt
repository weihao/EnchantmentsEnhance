package org.pixeltime.enchantmentsenhance.api

import org.bukkit.inventory.ItemStack

interface EnchantmentsEnhanceAPI {
    fun getNumberOfStone(playerName: String, stoneId: Int): Int
    fun addCustomEnchant(item: ItemStack, enchantment: String, level: Int): Boolean
    fun setItem(player: String, type: Int, level: Int)
    fun addItem(player: String, type: Int, levelsToAdd: Int)
    fun getItem(player: String, type: Int): Int
    fun resetFailstack(player: String)
    fun setFailstack(player: String, level: Int)
    fun addFailstack(player: String, levelsToAdd: Int)
    fun getFailstack(player: String): Int
    fun hasFailstack(player: String): Boolean
    fun getChance(playerName: String, enchantLevel: Int): Double
    fun addAdvice(player: String)
    fun addAdvice(player: String, level: Int)
    fun getEnchantmentMaxLevel(ench: String): Int
    fun getGearEnchantmentLevel(item: ItemStack): Int
    fun getToolEnchantmentLevel(item: ItemStack): Int
}
