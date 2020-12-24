package org.pixeltime.enchantmentsenhance.manager.modular;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.pixeltime.enchantmentsenhance.compatibility.UnsafeGlow;
import org.pixeltime.enchantmentsenhance.interfaces.GlowItem;

public class GlowItem_Unsafe implements GlowItem {

    private UnsafeGlow inst;

    public GlowItem_Unsafe() {
        inst = UnsafeGlow.Factory.create();
        inst.getWrapper(); // 服务器启动后就注册，防止 lazy 造成不发光
    }

    @NotNull
    @Override
    public ItemStack addGlow(@NotNull ItemStack item) {
        return inst.addGlow(item);
    }
}
