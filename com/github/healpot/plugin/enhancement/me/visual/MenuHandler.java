package com.github.healpot.plugin.enhancement.me.visual;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class MenuHandler implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!e.getInventory().getName().equalsIgnoreCase(
				((Main) Bukkit.getPluginManager().getPlugin("EnchantmentsEnhance")).menu.getScreen().getName())) {
			return;
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("")) {
			e.setCancelled(true);

		}

	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		if (!e.getInventory().getName().equalsIgnoreCase(
				((Main) Bukkit.getPluginManager().getPlugin("EnchantmentsEnhance")).menu.getScreen().getName())) {
			return;
		}
	}

	public static int getSlot(int x, int y) {
		return (y * 9) - (9 - x) - 1;
	}
}