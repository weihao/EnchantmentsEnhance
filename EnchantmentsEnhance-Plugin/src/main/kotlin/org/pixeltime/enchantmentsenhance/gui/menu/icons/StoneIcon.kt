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

package org.pixeltime.enchantmentsenhance.gui.menu.icons

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.event.Enhance
import org.pixeltime.enchantmentsenhance.gui.Clickable
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.MaterialManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.ItemBuilder
import org.pixeltime.enchantmentsenhance.util.Util

class StoneIcon : Clickable() {

    fun getItem(stoneId: Int, player: Player): ItemStack {
        return ItemBuilder(MaterialManager.stoneTypes[stoneId],
                if (ItemIcon.getOneStoneCountAsCount(player.name, stoneId) > 64)
                    64
                else
                    ItemIcon.getOneStoneCountAsCount(player.name, stoneId))
            .setName(SettingsManager.lang.getString("item.$stoneId"))
            .addLoreLine(ItemIcon.getOneStoneCountAsString(player.name, stoneId))
            .addLoreLine(SettingsManager.lang.getString("menu.leftInfo"))
            .addLoreLine(SettingsManager.lang.getString("menu.rightInfo"))
            .toItemStack()
    }

    fun getItem(stoneId: Int): ItemStack {
        return ItemBuilder(MaterialManager.stoneTypes[stoneId], 1)
            .setName(SettingsManager.lang.getString("item.$stoneId"))
            .addLoreLine(ItemIcon.getOneStoneCountAsString(null, stoneId))
            .toItemStack()
    }

    fun getItem(item: ItemStack, player: Player, clicked: Clickable): ItemStack? {
        val stoneId: Int = when (clicked) {
            MainMenu.gear -> Enhance.getStoneId(item, ItemManager.getItemEnchantLevel(item) + 1, clicked)
            MainMenu.tool -> Enhance.getStoneId(item, ItemManager.getToolEnchantLevel(item) + 1, clicked)
            else -> return null
        }
        return getItem(stoneId, player)
    }

    override fun getItem(playerName: String): ItemStack {
        TODO()
    }

    override val position: Int
        get() = Util.getSlot(2, 4)
}
