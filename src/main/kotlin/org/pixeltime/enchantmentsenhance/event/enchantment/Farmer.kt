package org.pixeltime.enchantmentsenhance.event.enchantment

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.manager.KM
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

class Farmer : Listener {
    private val translateAlternateColorCodes = ChatColor.translateAlternateColorCodes('&', SettingsManager.lang.getString("enchantment." + "farmer"))

    @EventHandler
    fun onPlace(blockPlaceEvent: BlockPlaceEvent) {
        val player = blockPlaceEvent.player
        try {
            val armorContents = player.inventory.armorContents + IM.getAccessorySlots(player)
            for (i in armorContents.indices) {
                val itemStack = armorContents[i]
                if (itemStack.hasItemMeta() && itemStack.itemMeta.hasLore()) {
                    val level = KM.getLevel(translateAlternateColorCodes, itemStack.itemMeta.lore)
                    if (level > 0) {
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
                }
            }
        } catch (ex: Exception) {
        }

    }
}
