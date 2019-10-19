package org.pixeltime.enchantmentsenhance.manager

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.util.Util


class InventoryManager {
    companion object {
        private val left_ring = Main.getMain().config.getInt("accessory.left_ring")
        private val right_ring = Main.getMain().config.getInt("accessory.right_ring")
        private val left_earring = Main.getMain().config.getInt("accessory.left_earring")
        private val right_earring = Main.getMain().config.getInt("accessory.right_earring")
        private val necklace = Main.getMain().config.getInt("accessory.necklace")
        private val belt = Main.getMain().config.getInt("accessory.belt")
        @JvmStatic
        fun getAccessorySlots(player: Player): List<ItemStack> {
            val inv = player.inventory

            val accessory = arrayListOf<ItemStack>()
            if (left_ring != -1 && inv.getItem(left_ring) != null) {
                accessory.add(inv.getItem(left_ring)!!)
            }
            if (right_ring != -1 && inv.getItem(right_ring) != null) {
                accessory.add(inv.getItem(right_ring)!!)
            }
            if (left_earring != -1 && inv.getItem(left_earring) != null) {
                accessory.add(inv.getItem(left_earring)!!)
            }
            if (right_earring != -1 && inv.getItem(right_earring) != null) {
                accessory.add(inv.getItem(right_earring)!!)
            }
            if (necklace != -1 && inv.getItem(necklace) != null) {
                accessory.add(inv.getItem(necklace)!!)
            }
            if (belt != -1 && inv.getItem(belt) != null) {
                accessory.add(inv.getItem(belt)!!)
            }
            return accessory.filter { it.hasItemMeta() && it.itemMeta!!.hasLore() && it.itemMeta!!.lore!!.isNotEmpty() }
        }

        @JvmStatic
        fun getArmorSlots(player: Player): List<ItemStack> {
            return (player.inventory.armorContents + Util.getMainHand(player))
                    .filterNotNull()
                    .filter { it.type != Material.AIR && it.hasItemMeta() && it.itemMeta!!.hasLore() && it.itemMeta!!.lore!!.isNotEmpty() }
        }

        @JvmStatic
        fun getItemList(player: Player): List<ItemStack> {
            var itemList: List<ItemStack> = ArrayList()
            if (SettingsManager.config.getBoolean("slots.enableArmor")) {
                itemList += InventoryManager.getArmorSlots(player)
            }
            if (SettingsManager.config.getBoolean("slots.enableAcessory")) {
                itemList += InventoryManager.getAccessorySlots(player)
            }
            return itemList
        }

        @JvmStatic
        fun getHighestLevel(player: Player, lore: String): Int {
            return getItemList(player).map { KotlinManager.getLevel(lore, it.itemMeta!!.lore!!) }.max() ?: 0
        }
    }
}
