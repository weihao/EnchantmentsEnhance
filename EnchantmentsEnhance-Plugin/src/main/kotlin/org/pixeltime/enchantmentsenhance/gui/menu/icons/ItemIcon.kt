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

import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.gui.Clickable
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat
import org.pixeltime.enchantmentsenhance.util.ItemBuilder
import org.pixeltime.enchantmentsenhance.util.Util
import org.pixeltime.enchantmentsenhance.util.XMaterial

class ItemIcon : Clickable() {

    fun getItem(): ItemStack {
        return ItemBuilder(XMaterial.CHEST_MINECART.parseMaterial())
            .setName(SettingsManager.lang.getString("item.gui"))
            .addLoreLine(SettingsManager.lang.getString("item.gui1"))
            .toItemStack()
    }

    override fun getItem(player: String): ItemStack {
        val stat = PlayerStat.getPlayerStats(player)
        if (stat?.items != null) {
            for (i in stat.items!!) {
                if (i > 0) {
                    return CompatibilityManager.glow.addGlow(getItem())
                }
            }
        }
        return getItem()
    }

    override val position: Int
        get() = Util.getSlot(7, 1)

    companion object {

        fun getOneStoneCountAsString(player: String?, stoneId: Int): String {
            val count = if (player == null) 0 else Main.getApi().getItem(player, stoneId)
            return SettingsManager.lang.getString("item.listing")
                .replace("%ITEM%".toRegex(), SettingsManager.lang.getString("item.$stoneId"))
                .replace("%COUNT%".toRegex(), count.toString())
        }

        fun getOneStoneCountAsInt(player: String, stoneId: Int): Int {
            return Main.getApi().getItem(player, stoneId)
        }

        fun getOneStoneCountAsCount(player: String, stoneId: Int): Int {
            return if (getOneStoneCountAsInt(player, stoneId) <= 0) 1 else getOneStoneCountAsInt(player, stoneId)
        }
    }
}
