/*
 *     Copyright (C) 2017-Present HealPot
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.util.anvil

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.inventory.AnvilInventory
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.ItemManager

object ColorHandler {

    fun getTranslatedItem(p: Player, inv: AnvilInventory, task: AnvilTask): ItemStack? {
        val outputItem = inv.getItem(2)
        return if (outputItem != null && outputItem.hasItemMeta()) {
            colorItemWithPermissions(outputItem, p)
        } else outputItem
    }

    fun colorItemWithPermissions(item: ItemStack, p: Player): ItemStack {
        if (item.hasItemMeta()) {
            val itemMeta = item.itemMeta
            if (itemMeta.hasDisplayName()) {
                var coloredName = ChatColor.translateAlternateColorCodes('&', itemMeta.displayName)
                for (i in 0 until coloredName.length) {
                    if (coloredName[i] == '§') {
                        val c = coloredName[i + 1]
                        if (ItemManager.getItemEnchantLevel(item) < 1) {
                            coloredName = coloredName.replace("§$c".toRegex(), "&$c")
                        }
                    }
                }
                itemMeta.displayName = coloredName
                item.itemMeta = itemMeta
            }
        }
        return item
    }
}
