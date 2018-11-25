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

import com.lgou2w.ldk.bukkit.item.ItemFactory
import com.lgou2w.ldk.bukkit.item.isNotAir
import com.lgou2w.ldk.bukkit.item.readTag
import com.lgou2w.ldk.bukkit.item.readTagSafe
import com.lgou2w.ldk.common.letIfNotNull
import com.lgou2w.ldk.nbt.NBTTagCompound
import com.lgou2w.ldk.nbt.ofCompound
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.reloaded.enhance.bow.Boom
import java.util.*

abstract class Enhance {

    /**
     * * Enhanced primary key for name.
     *      * Either subclass override, or remains unchanged.
     */
    val name : String = this::class.java.simpleName

    open fun canAccept(stack: ItemStack?): Boolean {
        return stack.isNotAir()
    }

    fun applyTo(stack: ItemStack, level: Int): Boolean {
        if (!canAccept(stack))
            return false
        val tag = stack.readTagSafe()
        val compound = tag.getCompoundOrDefault(KEY)
        applyTo(compound, level)
        ItemFactory.writeTag(stack, tag)
        return true
    }

    protected open fun applyTo(compound: NBTTagCompound, level: Int) {
        val enhances = compound.getListOrDefault(KEY_ENHANCES)
        val enhance = enhances.find { it is NBTTagCompound && it.getString(KEY_ENHANCE_NAME) == name } as? NBTTagCompound
                      ?: ofCompound { }.apply { enhances.add(this) }
        enhance.putString(KEY_ENHANCE_NAME, name)
        enhance.putShort(KEY_ENHANCE_LEVEL, level)
    }

    protected open fun registered() {
    }

    fun getLevel(stack: ItemStack?): Int {
        val tag = stack?.readTag() ?: return 0
        val compound = tag.getCompoundOrNull(KEY)
        val enhances = compound?.getListOrNull(KEY_ENHANCES)
        val enhance = enhances?.find { it is NBTTagCompound && it.getStringOrNull(KEY_ENHANCE_NAME) == name } as? NBTTagCompound
        return enhance?.getShortOrNull(KEY_ENHANCE_LEVEL)?.toInt() ?: 0
    }

    companion object Constants {

        const val KEY = "EnchantmentsEnhance"
        const val KEY_ENHANCES = "Enhances"
        const val KEY_ENHANCE_NAME = "Name"
        const val KEY_ENHANCE_LEVEL = "Level"

        private val ENHANCES : Map<String, Enhance> = registerEnhances()
        private fun registerEnhances(): Map<String, Enhance> {
            val enhances = object : HashMap<String, Enhance>() {
                fun register(enhance: Enhance) {
                    put(enhance.name, enhance)
                    enhance.registered()
                }
            }.apply {
                register(Boom())
            }
            return Collections
                .unmodifiableMap(enhances)
        }

        @JvmField
        val enhanceNames : Collection<String>
                = Collections.unmodifiableCollection(ENHANCES.keys)

        @JvmStatic
        fun fromName(name: String): Enhance?
                = ENHANCES[name]

        @JvmStatic
        fun <T: Enhance> fromClass(type: Class<T>): T?
                = ENHANCES.values.find { it.javaClass == type }.letIfNotNull { type.cast(this) }
    }
}
