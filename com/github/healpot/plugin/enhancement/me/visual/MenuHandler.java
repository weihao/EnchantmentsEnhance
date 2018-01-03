package com.github.healpot.plugin.enhancement.me.visual;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class MenuHandler implements Listener {
	private Main m;
	private ItemStack enhancingItem;

	public MenuHandler(Main m) {
		this.m = m;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!e.getInventory().getName().equalsIgnoreCase(m.menu.getScreen().getName())) {
			return;
		}
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}

		if (e.getCurrentItem().getItemMeta() == null) {
			return;
		}
		Player player = (Player) e.getWhoClicked();

		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("enhance")) {
			e.setCancelled(true);
			return;
		}

		if (e.getCurrentItem().getItemMeta().getDisplayName().equals("stats")) {
			e.setCancelled(true);
			return;
		}
		if (m.enhance.getValidationOfItem(m, player, e.getCurrentItem())) {

			enhancingItem = e.getCurrentItem();
			m.menu.updateLore(m, enhancingItem, player);
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