package org.pixeltime.enchantmentsenhance.event.enchantment.pickaxe

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.listener.EnchantmentListener
import org.pixeltime.enchantmentsenhance.util.Util
import java.util.*

class Smelt : EnchantmentListener() {
    override fun desc(): Array<String> {
        return arrayOf("Auto Smelts the blocks you break", "自动冶炼你挖到的矿物")
    }

    override fun lang(): Array<String> {
        return arrayOf("冶炼")
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun onBreak(blockBreakEvent: BlockBreakEvent) {
        if (blockBreakEvent.isCancelled) {
            return
        }
        val player = blockBreakEvent.player

        val level = getLevel(player)
        if (level > 0) {
            val block = blockBreakEvent.block
            val calculateFortune = this.calculateFortune(player, block.type)
            if (block.type == Material.IRON_ORE) {
                blockBreakEvent.block.world.dropItemNaturally(blockBreakEvent.block.location, ItemStack(Material.IRON_INGOT, calculateFortune))
                block.type = Material.AIR
            }
            if (block.type == Material.GOLD_ORE) {
                blockBreakEvent.block.world.dropItemNaturally(blockBreakEvent.block.location, ItemStack(Material.GOLD_INGOT, calculateFortune))
                block.type = Material.AIR
            }
        }
    }

    fun calculateFortune(player: Player, material: Material): Int {
        var n = 1
        try {
            if (Util.getMainHand(player).enchantments.containsKey(Enchantment.LOOT_BONUS_BLOCKS)) {
                var n2 = Random().nextInt(Util.getMainHand(player).getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 2) - 1
                if (n2 <= 0) {
                    n2 = 1
                }
                n = (if (material == Material.LAPIS_ORE) 4 + Random().nextInt(5) else 1) * (n2 + 1)
            }
        } catch (ex: NullPointerException) {
            // Empty enchantments.
        }
        return n
    }
}
