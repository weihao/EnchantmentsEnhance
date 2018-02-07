package com.github.healpot.plugin.enhancement.visual;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import com.github.healpot.plugin.enhancement.main.Main;
import com.github.healpot.plugin.enhancement.main.SettingsManager;
import com.github.healpot.plugin.enhancement.main.util.Util;

public class Backpack {

    private Inventory screen = null;
    private ItemStack[] items = new ItemStack[3];


    public void showBackpack(Main m, Player player, ItemStack item) {
        screen = Bukkit.getServer().createInventory(null, 27,
            SettingsManager.lang.getString("Inventory.gui.title"));
        createMenu(m, player);
        player.openInventory(screen);
    }


    public void createMenu(Main m, Player player) {
        screen.clear();
        for (int i = 0; i < items.length; i++) {
            items[i] = new ItemStack(Util.stoneTypes[i]);
            screen.setItem(Util.getSlot(1, i + 1), items[i]);
        }
    }


    public Inventory getScreen() {
        return screen;
    }

}
