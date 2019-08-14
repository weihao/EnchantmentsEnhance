package org.pixeltime.enchantmentsenhance.manager.modular

import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.compatibility.UnsafeGlow
import org.pixeltime.enchantmentsenhance.interfaces.GlowItem

class GlowItem_Unsafe : GlowItem {

    private val inst: UnsafeGlow

    init {
        inst = UnsafeGlow.create()
        inst.wrapper // 服务器启动后就注册，防止 lazy 造成不发光
    }

    override fun addGlow(item: ItemStack): ItemStack {
        return inst.addGlow(item)
    }
}
