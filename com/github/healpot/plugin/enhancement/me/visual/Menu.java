package com.github.healpot.plugin.enhancement.me.visual;

import java.util.ArrayList;
import java.util.List;

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

	private Inventory screen = null;
	private ItemStack enhance, force, stats, remove;

	private ItemStack createItem(DyeColor dc, String name) {
		ItemStack i = new Wool(dc).toItemStack(1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		i.setItemMeta(im);
		return i;
	}

	public void showEnhancingMenu(Main m, Player player, ItemStack item) {
		screen = Bukkit.getServer().createInventory(null, 27, m.settings.getLang().getString("Menu.title"));
		createMenu(m);
		player.openInventory(screen);
	}

	public void createMenu(Main m) {
		enhance = createItem(DyeColor.YELLOW, ChatColor.GREEN + "Enhance");
		ItemMeta im = enhance.getItemMeta();
		List<String> update = new ArrayList<String>();
		update.add(m.settings.getLang().getString("Menu.lore.ifSuccess"));
		update.add(m.settings.getLang().getString("Menu.lore.ifFail"));
		im.setLore(update);

		force = createItem(DyeColor.BLACK, ChatColor.RED + "Force");
		stats = createItem(DyeColor.LIGHT_BLUE, ChatColor.RED + "Stats");
		remove = createItem(DyeColor.RED, ChatColor.RED + "Remove");

		screen.setItem(getSlot(5, 1), stats);
		screen.setItem(getSlot(4, 3), enhance);
		screen.setItem(getSlot(6, 3), force);
	}

	public static int getSlot(int x, int y) {
		return (y * 9) - (9 - x) - 1;
	}

	public Inventory getScreen() {
		return screen;
	}

	public void updateInv(Main m, ItemStack item, Player player) {
		updateFailstack(m, item, player);
		screen.setItem(getSlot(5, 1), m.glow.addGlow(stats));
		screen.setItem(getSlot(4, 3), m.glow.addGlow(enhance));
		screen.setItem(getSlot(6, 3), m.glow.addGlow(force));
		screen.setItem(getSlot(1, 2), item);
	}

	public void updateFailstack(Main m, ItemStack item, Player player) {
		ItemMeta im = stats.getItemMeta();
		im.setLore(m.enhance.getChanceAsList(m, item, player));
		stats.setItemMeta(im);
		screen.setItem(getSlot(5, 1), m.glow.addGlow(stats));
	}

	public void updateEnhance(Main m, ItemStack item, Player player) {
		ItemMeta im = enhance.getItemMeta();
		List<String> update = new ArrayList<String>();
		update.add(m.settings.getLang().getString("Menu.lore.ifSuccess"));
		update.add(m.settings.getLang().getString("Menu.lore.ifFail"));
		if (m.enhance.getItemEnchantLevel(m, player, item) > 16) {
			update.add(m.settings.getLang().getString("Menu.lore.ifDowngrade"));
		}
		im.setLore(update);
		screen.setItem(getSlot(4, 3), m.glow.addGlow(enhance));
	}

	public void updateInSlotItem(Main m, ItemStack item, Player player) {
		screen.setItem(getSlot(1, 2), item);
	}

	public void addRemoveButton() {
		screen.setItem(getSlot(1, 3), remove);
	}

}