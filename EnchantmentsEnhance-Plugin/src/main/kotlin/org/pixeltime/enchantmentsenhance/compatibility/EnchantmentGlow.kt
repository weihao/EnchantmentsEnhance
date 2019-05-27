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

import com.lgou2w.ldk.asm.ASMClassLoader
import com.lgou2w.ldk.bukkit.version.MinecraftBukkitVersion
import com.lgou2w.ldk.reflect.FuzzyReflect
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

interface EnchantmentGlow {

    val wrapper : Enchantment

    fun addGlow(stack: ItemStack): ItemStack

    @Suppress("DEPRECATION", "UNCHECKED_CAST")
    companion object Factory : EnchantmentGlow {

        private val CLASSES by lazy { ASMClassLoader.ofInstance().defineClasses(EnchantmentGlowImplGenerator.generate()) }
        private val CLASS_IMPL by lazy { CLASSES.first() as Class<Enchantment> }

        override val wrapper : Enchantment = CLASS_IMPL.newInstance()
        override fun addGlow(stack: ItemStack): ItemStack {
            stack.addUnsafeEnchantment(wrapper, 1)
            return stack
        }

        fun init(): EnchantmentGlow {
            val wrapper = this.wrapper
            val needRegister =
                    if (MinecraftBukkitVersion.isV113OrLater) Enchantment.getByKey(wrapper.key) == null
                    else Enchantment.getByName(wrapper.name) == null
            if (needRegister) {
                val acceptingNew = FuzzyReflect.of(Enchantment::class.java, true)
                    .useFieldMatcher()
                    .withName("acceptingNew")
                    .resultAccessorAs<Enchantment, Boolean>()
                acceptingNew[null] = true
                Enchantment.registerEnchantment(wrapper)
                acceptingNew[null] = false
            }
            return this
        }
    }
}
