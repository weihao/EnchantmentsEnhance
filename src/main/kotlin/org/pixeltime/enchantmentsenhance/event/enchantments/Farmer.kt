package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Farmer : Listener {
    @EventHandler
    fun onPlace(blockPlaceEvent: BlockPlaceEvent) {
        val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "farmer"))
        val player = blockPlaceEvent.player
        try {
            val armorContents = player.inventory.armorContents
            val length = armorContents.size
            var i = 0
            while (i < length) {
                val itemStack = armorContents[i]
                if (itemStack.hasItemMeta() && itemStack.itemMeta.hasLore() && itemStack.itemMeta.lore.contains(translateAlternateColorCodes.toString() + " I")) {
                    if (player.itemInHand.type == Material.CARROT_ITEM) {
                        blockPlaceEvent.blockPlaced.setTypeIdAndData(Material.CARROT.id, 7.toByte(), true)
                    }
                    if (player.itemInHand.type == Material.MELON_SEEDS) {
                        blockPlaceEvent.blockPlaced.type = Material.MELON
                    }
                    if (player.itemInHand.type == Material.POTATO_ITEM) {
                        blockPlaceEvent.blockPlaced.setTypeIdAndData(Material.POTATO.id, 7.toByte(), true)
                    }
                    if (player.itemInHand.type == Material.SEEDS) {
                        blockPlaceEvent.blockPlaced.setTypeIdAndData(Material.CROPS.id, 7.toByte(), true)
                    }
                    if (player.itemInHand.type == Material.PUMPKIN_SEEDS) {
                        blockPlaceEvent.blockPlaced.type = Material.PUMPKIN
                    }
                }
                ++i
            }
        } catch (ex: Exception) {
        }

    }
}
