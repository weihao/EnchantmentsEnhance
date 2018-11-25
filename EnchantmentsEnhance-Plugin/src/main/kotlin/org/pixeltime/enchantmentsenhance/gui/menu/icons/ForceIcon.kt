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

import org.bukkit.DyeColor
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.event.Enhance
import org.pixeltime.enchantmentsenhance.gui.Clickable
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager
import org.pixeltime.enchantmentsenhance.manager.DataManager
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.ItemBuilder
import org.pixeltime.enchantmentsenhance.util.Util
import com.lgou2w.ldk.bukkit.compatibility.XMaterial

class ForceIcon : Clickable() {

    override fun getItem(playerName: String): ItemStack {
        return ItemBuilder(XMaterial.RED_WOOL.toBukkit())
            .setDyeColor(DyeColor.RED)
            .setName(SettingsManager.lang.getString("menu.gui.force"))
            .addLoreLine(SettingsManager.lang.getString(
                    "menu.lore.force1"))
            .toItemStack()
    }

    fun getItem(item: ItemStack, clicked: Clickable): ItemStack {
        val enchantLevel = ItemManager.getItemEnchantLevel(item)
        val stoneId = Enhance.getStoneId(item, enchantLevel, clicked)
        val costToEnhance = DataManager.costToForceEnchant[enchantLevel + 1]
        return ItemBuilder(XMaterial.RED_WOOL.toBukkit())
            .setDyeColor(DyeColor.RED)
            .setName(SettingsManager.lang.getString("menu.gui.force"))
            .addLoreLine(SettingsManager.lang.getString("menu.lore.force1"))
            .addLoreLine(SettingsManager.lang.getString("menu.lore.force2")
                .replace("%COUNT%", Integer.toString(costToEnhance))
                .replace("%ITEM%", SettingsManager.lang.getString("item.$stoneId"))).toItemStack()
    }

    fun getGlowingItem(item: ItemStack, clicked: Clickable): ItemStack {
        return CompatibilityManager.glow.addGlow(getItem(item, clicked))
    }

    override val position: Int
        get() = Util.getSlot(6, 5)
}
