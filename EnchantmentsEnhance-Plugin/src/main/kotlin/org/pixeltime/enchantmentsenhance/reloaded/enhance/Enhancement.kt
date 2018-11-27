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

import com.lgou2w.ldk.bukkit.item.ItemBuilder
import com.lgou2w.ldk.bukkit.item.builder
import com.lgou2w.ldk.bukkit.item.isNotAir
import com.lgou2w.ldk.common.letIfNotNull
import com.lgou2w.ldk.nbt.NBTTagCompound
import com.lgou2w.ldk.nbt.ofCompound
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.reloaded.enhance.bow.BoomBow
import org.pixeltime.enchantmentsenhance.reloaded.enhance.bow.EnderBow
import java.util.*

abstract class Enhancement {

    /**
     * * Enhancement primary key for name.
     *      * Either subclass override, or remains unchanged.
     * * 增强附魔的主键名称.
     *      * 要么子类重写, 要么保持不变.
     */
    val name : String = this::class.java.simpleName

    /**
     * * Apply the current [Enhancement] enhancement to the given [stack] item stack.
     *      * This function cannot be overridden and should not be allowed to be overridden. For external access only.
     * * 将当前 [Enhancement] 增强附魔应用到给定的 [stack] 物品栈.
     *      * 此函数不能被重写并且不应该允许重写. 仅供外部访问.
     *
     * @see applyTo(compound: NBTTagCompound, level: Int)
     */
    fun applyTo(stack: ItemStack, level: Int): Boolean {
        if (!canAccept(stack) || isConflict(stack))
            return false
        stack.builder {
            applyTo(this, tag.getCompoundOrDefault(KEY), level)
            build()
        }
        return true
    }

    /**
     * * Gets the current [Enhancement] enhancement in the given [stack] item stack, and returns 0 if not.
     * * 获取当前 [Enhancement] 增强附魔在给定 [stack] 物品栈中的等级值, 没有则返回 0.
     *
     * @see EnhancementHelper.getLevel
     */
    fun getLevel(stack: ItemStack?): Int {
        return EnhancementHelper.getLevel(stack, this)
    }

    /**
     * * Indicates whether the current [Enhancement] can accept a given [stack] item stack.
     * * 表示当前 [Enhancement] 增强附魔是否可以接受给定的 [stack] 物品栈.
     */
    open fun canAccept(stack: ItemStack?): Boolean {
        return stack.isNotAir()
    }

    /**
     * * Indicates whether the current [Enhancement] enhancement conflicts with the given [stack] item stack.
     *      * Generally used to detect whether it conflicts with an enhancement.
     * * 表示当前 [Enhancement] 增强附魔是否和给定的 [stack] 物品栈冲突.
     *      * 一般用于检测是否和某个增强附魔冲突.
     */
    open fun isConflict(stack: ItemStack?): Boolean {
        return false
    }

    /**
     * * Apply the current [Enhancement] enhancement data to the given [compound] tag.
     * * 将当前 [Enhancement] 增强附魔的数据应用到给定的 [compound] 标签中.
     */
    protected open fun applyTo(builder: ItemBuilder, compound: NBTTagCompound, level: Int) {
        val enhancements = compound.getListOrDefault(KEY_ENHANCEMENTS)
        val enhancement = enhancements
                              .find { it is NBTTagCompound && it.getString(KEY_ENHANCEMENT_NAME) == name } as? NBTTagCompound
                          ?: ofCompound { }.apply { enhancements.add(this) }
        enhancement.putString(KEY_ENHANCEMENT_NAME, name)
        enhancement.putShort(KEY_ENHANCEMENT_LEVEL, level)
    }

    /**
     * * This function is called when the enhancement is successfully registered.
     * * 当增强附魔成功注册后, 那么此函数被调用.
     */
    protected open fun registered() {
    }

    companion object Constants {

        const val KEY = "EnchantmentsEnhance"
        const val KEY_ENHANCEMENTS = "Enhancements"
        const val KEY_ENHANCEMENT_NAME = "Name"
        const val KEY_ENHANCEMENT_LEVEL = "Level"

        fun init() {
        }

        private val ENHANCEMENTS : Map<String, Enhancement> = registerEnhancements()
        private fun registerEnhancements(): Map<String, Enhancement> {
            val enhances = object : HashMap<String, Enhancement>() {
                fun register(enhance: Enhancement) {
                    put(enhance.name, enhance)
                    enhance.registered()
                }
            }.apply {
                register(BoomBow())
                register(EnderBow())
            }
            return Collections
                .unmodifiableMap(enhances)
        }

        @JvmField
        val enhancementNames : Collection<String>
                = Collections.unmodifiableCollection(ENHANCEMENTS.keys)
        @JvmStatic
        fun fromName(name: String): Enhancement?
                = ENHANCEMENTS[name]
        @JvmStatic
        fun <T: Enhancement> fromClass(type: Class<T>): T?
                = ENHANCEMENTS.values.find { it.javaClass == type }.letIfNotNull { type.cast(this) }
    }
}
