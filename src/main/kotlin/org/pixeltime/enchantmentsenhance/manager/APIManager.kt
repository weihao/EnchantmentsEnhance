package org.pixeltime.enchantmentsenhance.manager

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.events.blacksmith.SecretBook
import org.pixeltime.enchantmentsenhance.events.blackspirit.Failstack
import org.pixeltime.enchantmentsenhance.events.inventory.Inventory
import java.util.*

class API {
    companion object {
        @JvmStatic
        fun addStone(player: Player, stoneType: Int, numberOfStones: Int) {
            Inventory.addLevel(player, stoneType, numberOfStones)
        }

        fun getStones(player: Player): IntArray {
            return Inventory.getPlayer(player)
        }

        fun getCurrentFailstack(player: Player): Int {
            return Failstack.getLevel(player)
        }

        fun getValkStorage(): HashMap<String, MutableList<Int>> {
            return SecretBook.getStorage()
        }

        fun getValks(player: Player): MutableList<Int>? {
            return SecretBook.getStorage()[player.name]
        }

        fun customEnchant(item: ItemStack, enchantment: String, level: Int) {
            ItemManager.applyEnchantmentToItem(item, enchantment, level)
        }

    }
}