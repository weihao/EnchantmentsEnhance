package org.pixeltime.enchantmentsenhance.compatibility

import com.lgou2w.ldk.bukkit.version.MinecraftBukkitVersion
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

interface UnsafeGlow {

    val wrapper: Enchantment

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
