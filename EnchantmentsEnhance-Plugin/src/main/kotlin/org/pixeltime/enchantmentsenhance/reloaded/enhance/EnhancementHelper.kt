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

package org.pixeltime.enchantmentsenhance.reloaded.enhance

import com.lgou2w.ldk.bukkit.item.readTag
import com.lgou2w.ldk.common.letIfNotNull
import com.lgou2w.ldk.nbt.NBTTagCompound
import org.bukkit.inventory.ItemStack

object EnhancementHelper {

    fun apply(stack: ItemStack, enhancement: Enhancement, level: Int): Boolean {
        return enhancement.applyTo(stack, level)
    }

    fun apply(stack: ItemStack, type: Class<out Enhancement>, level: Int): Boolean {
        val enhancement = Enhancement.fromClass(type) ?: return false
        return apply(stack, enhancement, level)
    }

    fun getLevel(stack: ItemStack?, enhancement: Enhancement): Int {
        val tag = stack?.readTag()
        val compound = tag?.getCompoundOrNull(Enhancement.KEY)
        val enchantments = compound?.getListOrNull(Enhancement.KEY_ENHANCEMENTS)
        return enchantments
            ?.asElements<NBTTagCompound>()
            ?.find { it.getStringOrNull(Enhancement.KEY_ENHANCEMENT_NAME) == enhancement.name }
            ?.getShortOrNull(Enhancement.KEY_ENHANCEMENT_LEVEL)?.toInt()
               ?: 0
    }

    fun getLevel(stack: ItemStack?, type: Class<out Enhancement>): Int {
        val enhancement = Enhancement.fromClass(type) ?: return 0
        return getLevel(stack, enhancement)
    }

    fun getEntries(stack: ItemStack?): Map<Enhancement, Int> {
        val tag = stack?.readTag()
        val compound = tag?.getCompoundOrNull(Enhancement.KEY)
        val enchantments = compound?.getListOrNull(Enhancement.KEY_ENHANCEMENTS)
        return enchantments
            ?.asElements<NBTTagCompound>()
            ?.mapNotNull { enhancement ->
                val name = enhancement.getString(Enhancement.KEY_ENHANCEMENT_NAME)
                val level = enhancement.getShort(Enhancement.KEY_ENHANCEMENT_LEVEL)
                Enhancement.fromName(name).letIfNotNull { this to level.toInt() }
            }
            ?.associate { it }
               ?: emptyMap()
    }

    fun has(stack: ItemStack?, vararg enhancements: Enhancement): Boolean {
        val entries = getEntries(stack)
        return (entries.isEmpty() && enhancements.isEmpty()) ||
               enhancements.all {
                   val level = entries[it]
                   return level != null && level > 0
               }
    }

    fun has(stack: ItemStack?, vararg types: Class<out Enhancement>): Boolean {
        val enchantments = types.mapNotNull { Enhancement.fromClass(it) }
        return has(stack, *enchantments.toTypedArray())
    }
}
