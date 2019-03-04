/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

package org.pixeltime.enchantmentsenhance.compatibility

import com.lgou2w.ldk.reflect.FuzzyReflect
import org.bukkit.enchantments.Enchantment
import org.bukkit.enchantments.EnchantmentTarget
import org.bukkit.enchantments.EnchantmentWrapper
import org.bukkit.inventory.ItemStack

class UnsafeGlow_V1_12 : UnsafeGlow {

    override val wrapper: Enchantment
        get() = GLOW

    override fun addGlow(stack: ItemStack): ItemStack {
        stack.addUnsafeEnchantment(GLOW, 1)
        return stack
    }

    companion object Constants {
        const val ID = 255
        const val NAME = "Glow"
        const val MAX_LEVEL = 10
        const val START_LEVEL = 1
        @JvmStatic
        private val GLOW: Enchantment by lazy {
            val glow = UnsafeGlowWrapper()
            if (Enchantment.getByName(glow.name) == null) {
                val acceptingNew = FuzzyReflect.of(Enchantment::class.java, true)
                        .useFieldMatcher()
                        .withName("acceptingNew")
                        .resultAccessorAs<Enchantment, Boolean>()
                acceptingNew[null] = true
                Enchantment.registerEnchantment(glow)
                acceptingNew[null] = false
            }
            glow
        }
    }

    private class UnsafeGlowWrapper : EnchantmentWrapper(ID) {
        override fun canEnchantItem(item: ItemStack?): Boolean = true
        override fun getItemTarget(): EnchantmentTarget? = null
        override fun getName(): String = NAME
        override fun getMaxLevel(): Int = MAX_LEVEL
        override fun getStartLevel(): Int = START_LEVEL
        override fun conflictsWith(other: Enchantment?): Boolean = false
    }
}
