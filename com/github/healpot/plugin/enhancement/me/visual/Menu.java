package com.github.healpot.plugin.enhancement.me.visual;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class Menu {

	private Inventory screen;
	private ItemStack enhance, confirm, no, force, stats;
	private ItemStack item;
	private Player player;

	private ItemStack createItem(DyeColor dc, String name) {
		ItemStack i = new Wool(dc).toItemStack(1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		i.setItemMeta(im);
		return i;
	}

	public void showEnhancingMenu(Main m, Player player, ItemStack item) {
		screen = Bukkit.getServer().createInventory(null, 27, "Enhancement Menu");

		enhance = createItem(DyeColor.YELLOW, ChatColor.GREEN + "Enhance");
		confirm = createItem(DyeColor.GREEN, ChatColor.YELLOW + "Confirm");
		no = createItem(DyeColor.RED, ChatColor.RED + "Decline");
		force = createItem(DyeColor.BLACK, ChatColor.RED + "Force");
		stats = createItem(DyeColor.LIGHT_BLUE, ChatColor.RED + "Stats");

		enhance.getItemMeta().setLore(Arrays.asList("", ""));
		screen.setItem(getSlot(5, 1), stats);
		screen.setItem(getSlot(4, 3), enhance);
		screen.setItem(getSlot(6, 3), force);

		player.openInventory(screen);
	}

	public static int getSlot(int x, int y) {
		return (y * 9) - (9 - x) - 1;
	}

	public Inventory getScreen() {
		return screen;
	}

	public void updateLore(Main m, ItemStack item, Player player) {
		ItemMeta im = item.getItemMeta();
		im.setLore(m.enhance.getChanceAsList(m, item, player));
		stats.setItemMeta(im);
		screen.setItem(getSlot(5, 1), stats);
	}
}