package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

import java.util.Random

class Smelt : Listener {
    @EventHandler
    fun onBreak(blockBreakEvent: BlockBreakEvent) {
        if (blockBreakEvent.isCancelled) {
            return
        }
        val player = blockBreakEvent.player
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "smelt"))
        if (player.itemInHand != null && player.inventory.itemInHand.hasItemMeta() && player.itemInHand.itemMeta.hasLore() && player.itemInHand.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
            val block = blockBreakEvent.block
            val calculateFortune = this.calculateFortune(player, block.type)
            if (block.type != Material.IRON_ORE && block.type != Material.GOLD_ORE && block.type != Material.DIAMOND_ORE && block.type != Material.LAPIS_ORE) {
                block.type
                val emerald_ORE = Material.EMERALD_ORE
            }
            if (block.type == Material.IRON_ORE) {
                blockBreakEvent.block.world.dropItemNaturally(blockBreakEvent.block.location, ItemStack(Material.IRON_INGOT, calculateFortune))
                block.type = Material.AIR
            }
            if (block.type == Material.GOLD_ORE) {
                blockBreakEvent.block.world.dropItemNaturally(blockBreakEvent.block.location, ItemStack(Material.GOLD_INGOT, calculateFortune))
                block.type = Material.AIR
            }
            if (block.type == Material.COAL_ORE) {
                blockBreakEvent.block.world.dropItemNaturally(blockBreakEvent.block.location, ItemStack(Material.COAL, calculateFortune))
                block.type = Material.AIR
            }
            if (block.type == Material.IRON_ORE) {
                blockBreakEvent.block.world.dropItemNaturally(blockBreakEvent.block.location, ItemStack(Material.IRON_INGOT, calculateFortune))
                block.type = Material.AIR
            }
            if (block.type == Material.DIAMOND_ORE) {
                blockBreakEvent.block.world.dropItemNaturally(blockBreakEvent.block.location, ItemStack(Material.DIAMOND, calculateFortune))
                block.type = Material.AIR
            }
            if (block.type == Material.EMERALD_ORE) {
                blockBreakEvent.block.world.dropItemNaturally(blockBreakEvent.block.location, ItemStack(Material.EMERALD, calculateFortune))
                block.type = Material.AIR
            }
            if (block.type == Material.LAPIS_ORE) {
                blockBreakEvent.block.world.dropItemNaturally(blockBreakEvent.block.location, ItemStack(Material.INK_SACK, calculateFortune, 4.toShort()))
                block.type = Material.AIR
            }
        }
    }

    fun calculateFortune(player: Player, material: Material): Int {
        var n = 1
        if (player.itemInHand.enchantments.containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
            var n2 = Random().nextInt(player.itemInHand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 2) - 1
            if (n2 <= 0) {
                n2 = 1
            }
            n = (if (material == Material.LAPIS_ORE) 4 + Random().nextInt(5) else 1) * (n2 + 1)
        }
        return n
    }
}
