package org.pixeltime.enchantmentsenhance.manager

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


class IM {
    companion object {
        @JvmStatic
        fun getAccessorySlots(player: Player): Array<ItemStack> {
            val inv = player.inventory

            val accessory = mutableListOf<ItemStack>()
            if (inv.getItem(9) != null) {
                accessory.add(inv.getItem(9))
            }
            if (inv.getItem(10) != null) {
                accessory.add(inv.getItem(10))
            }
            if (inv.getItem(18) != null) {
                accessory.add(inv.getItem(18))
            }
            if (inv.getItem(19) != null) {
                accessory.add(inv.getItem(19))
            }
            return accessory.toTypedArray()
        }
    }
}