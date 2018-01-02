package com.github.healpot.plugin.enhancement.me.visual;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class Menu implements Listener {

	private Inventory screen;
	private ItemStack enhance, confirm, no, force, stats;

	public Menu(Main m) {
		screen = Bukkit.getServer().createInventory(null, 27, "Gamemode Chooser");

		enhance = createItem(DyeColor.YELLOW, ChatColor.GREEN + "Enhance");
		confirm = createItem(DyeColor.GREEN, ChatColor.YELLOW + "Confirm");
		no = createItem(DyeColor.RED, ChatColor.RED + "Decline");
		force = createItem(DyeColor.BLACK, ChatColor.RED + "Force");
		stats = createItem(DyeColor.LIGHT_BLUE, ChatColor.RED + "stats");

		enhance.getItemMeta().setLore(Arrays.asList("", ""));

		screen.setItem(getSlot(1, 1), stats);
		screen.setItem(getSlot(1, 5), enhance);
		screen.setItem(getSlot(3, 5), force);
	}

	private ItemStack createItem(DyeColor dc, String name) {
		ItemStack i = new Wool(dc).toItemStack(1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		i.setItemMeta(im);
		return i;
	}

	public void show(Player p) {
		p.openInventory(screen);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!e.getInventory().getName().equalsIgnoreCase(screen.getName()))
			return;
		if (e.getCurrentItem().getItemMeta() == null)
			return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Creative")) {
			e.setCancelled(true);
			e.getWhoClicked().setGameMode(GameMode.CREATIVE);
			e.getWhoClicked().closeInventory();
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Survival")) {
			e.setCancelled(true);
			e.getWhoClicked().setGameMode(GameMode.SURVIVAL);
			e.getWhoClicked().closeInventory();
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Adventure")) {
			e.setCancelled(true);
			e.getWhoClicked().setGameMode(GameMode.ADVENTURE);
			e.getWhoClicked().closeInventory();
		}
	}

	public static int getSlot(int x, int y) {
		return (y * 9) - (9 - x) - 1;
	}
}