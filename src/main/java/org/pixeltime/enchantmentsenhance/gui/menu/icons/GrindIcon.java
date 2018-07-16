package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.Random;

public class GrindIcon extends Clickable {


    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.ANVIL)
                .setName(SettingsManager.lang.getString("Menu.gui.grind"))
                .toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(2, 4);
    }

    public void click(String player, int locked) {
        Random random = new Random();
        int num = (int) (0.01 + 0.99 / (1 - random.nextDouble()));
        if (num < locked) {
            fail(player);
        } else {
            reward(player, locked);
        }
    }

    private void reward(String player, int reward) {

    }

    private void fail(String player) {

    }
}
