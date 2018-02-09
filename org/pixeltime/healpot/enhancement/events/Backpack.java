package org.pixeltime.healpot.enhancement.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.Util;

public class Backpack {

    private static Inventory screen = null;
    private static ItemStack[] items = new ItemStack[3];


    public static void showBackpack(Player player) {
        screen = Bukkit.getServer().createInventory(null, 27,
            SettingsManager.lang.getString("Inventory.gui.title"));
        createMenu(player);
        player.openInventory(screen);
    }


    public static void createMenu(Player player) {
        screen.clear();
        for (int i = 0; i < items.length; i++) {
            items[i] = new ItemStack(Util.stoneTypes[i]);
            screen.setItem(Util.getSlot(1, i + 1), items[i]);
        }
    }


    public static Inventory getScreen() {
        return screen;
    }

}
