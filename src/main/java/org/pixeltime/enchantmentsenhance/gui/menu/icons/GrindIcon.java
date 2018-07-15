package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;

import java.util.Random;

public class GrindIcon extends Clickable {


    @Override
    public ItemStack getItem() {
        return null;
    }

    @Override
    public int getPosition() {
        return 0;
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
