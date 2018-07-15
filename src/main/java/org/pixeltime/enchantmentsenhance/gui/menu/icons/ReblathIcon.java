package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.api.API;
import org.pixeltime.enchantmentsenhance.gui.Clickable;

public class ReblathIcon extends Clickable {
    private final double chance = 17.5

    @Override
    public ItemStack getItem() {
        return null;
    }

    @Override
    public int getPosition() {
        return 0;
    }

    public void reblath(String player) {
        // If player has item to failstack.
        if (API.getItem(player, 1) > 0) {
            // Roll.
            if ((Math.random() * 100) < 17.5) {
                API.addFailstack(player, 1);
            } else {
                API.resetFailstack(player);
            }
        }
    }
}
