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

package org.pixeltime.enchantmentsenhance.util

import com.lgou2w.ldk.bukkit.item.ItemBuilderBase
import com.lgou2w.ldk.chat.ChatSerializer
import com.lgou2w.ldk.chat.toColor
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ItemBuilder : ItemBuilderBase {

    constructor(itemStack: ItemStack) : super(itemStack)
    constructor(material: Material, count: Int, durability: Int) : super(material, count, durability)
    constructor(material: Material, count: Int) : this(material, count, 0)
    constructor(material: Material) : this(material, 1, 0)

    fun toItemStack(): ItemStack
            = build()

    fun setName(displayName: String): ItemBuilder {
        setDisplayName(ChatSerializer.fromRaw(displayName))
        return this
    }

    fun addLoreLine(loreLine: String): ItemBuilder {
        val tmp = lore?.toMutableList() ?: ArrayList()
        tmp.add(loreLine.toColor())
        lore = tmp
        return this
    }

    fun setDyeColor(color: DyeColor): ItemBuilder {
        durability = color.woolData.toInt()
        return this
    }

    // TODO
}
