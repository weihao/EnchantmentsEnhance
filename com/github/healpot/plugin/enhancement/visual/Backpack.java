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

public class Backpack {

    private Inventory screen = null;
    private ItemStack armor, weapon, carmor, cweapon;

    public void showBackpack(Main m, Player player, ItemStack item) {
        screen = Bukkit.getServer().createInventory(null, 27, m.settings.getLang().getString("Inventory.gui.title"));
        createMenu(m, player);
        player.openInventory(screen);
    }

    public void createMenu(Main m, Player player) {
        Material.GHAST_TEAR, Material.GOLD_NUGGET, Material.SUGAR,
        Material.GLOWSTONE_DUST 
        screen.clear();
        armor = new Material.G


        screen.setItem(getSlot(5, 1), stats);
        screen.setItem(getSlot(4, 3), enhance);
        screen.setItem(getSlot(6, 3), force);
        if (m.failstack.getLevel(m, player) != 0) {
            screen.setItem(getSlot(6, 1), store);
        } else {
            screen.setItem(getSlot(6, 1), null);
        }
    }

    public static int getSlot(int x, int y) {
        return (y * 9) - (9 - x) - 1;
    }

    public Inventory getScreen() {
        return screen;
    }

}
