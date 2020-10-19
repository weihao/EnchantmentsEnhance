package org.pixeltime.enchantmentsenhance.compatibility

import com.lgou2w.ldk.reflect.FuzzyReflect
import org.bukkit.enchantments.Enchantment
import org.bukkit.enchantments.EnchantmentTarget
import org.bukkit.enchantments.EnchantmentWrapper
import org.bukkit.inventory.ItemStack

class UnsafeGlow_V1_13 : UnsafeGlow {

    override val wrapper: Enchantment
        get() = GLOW

    override fun addGlow(stack: ItemStack): ItemStack {
        stack.addUnsafeEnchantment(GLOW, 1)
        return stack
    }

    companion object Constants {
        const val NAME = "eeglow"
        const val MAX_LEVEL = 10
        const val START_LEVEL = 1

        @JvmStatic
        private val GLOW: Enchantment by lazy {
            val glow = UnsafeGlowWrapper()
            if (Enchantment.getByKey(glow.key) == null) {
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

    private class UnsafeGlowWrapper : EnchantmentWrapper(NAME) {
        override fun canEnchantItem(item: ItemStack): Boolean = true
        override fun getItemTarget(): EnchantmentTarget = EnchantmentTarget.ALL
        override fun getMaxLevel(): Int = MAX_LEVEL
        override fun getStartLevel(): Int = START_LEVEL
        override fun conflictsWith(other: Enchantment): Boolean = false
        override fun getName(): String = NAME
    }
}
