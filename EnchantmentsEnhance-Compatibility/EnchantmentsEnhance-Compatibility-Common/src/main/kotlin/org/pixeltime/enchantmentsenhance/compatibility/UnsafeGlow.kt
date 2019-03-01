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

package org.pixeltime.enchantmentsenhance.compatibility

import com.lgou2w.ldk.bukkit.version.MinecraftBukkitVersion
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

interface UnsafeGlow {

    val wrapper : Enchantment

    fun addGlow(stack: ItemStack): ItemStack

    companion object Factory {
        private const val PACKAGE = "org.pixeltime.enchantmentsenhance.compatibility"
        private val CLASS_UNSAFE_GLOW by lazy {
            @Suppress("UNCHECKED_CAST")
            Class.forName(
                    if (MinecraftBukkitVersion.isV113OrLater)
                        "$PACKAGE.UnsafeGlow_V1_13"
                    else
                        "$PACKAGE.UnsafeGlow_V1_12"
            ) as Class<UnsafeGlow>
        }
        fun create(): UnsafeGlow {
            return CLASS_UNSAFE_GLOW.newInstance()
        }
    }
}
