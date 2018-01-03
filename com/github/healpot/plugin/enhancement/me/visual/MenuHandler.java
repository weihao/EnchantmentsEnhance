package com.github.healpot.plugin.enhancement.me.visual;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class MenuHandler implements Listener {

	private Inventory screen;
	private ItemStack item;
	private Player player;
	private Main m;

	public MenuHandler() {

	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!e.getInventory().getName().equalsIgnoreCase(screen.getName()))
			return;
		if (e.getCurrentItem().getItemMeta() == null)
			return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Enhance")) {
			e.setCancelled(true);

			if (!e.getWhoClicked().equals(player)) {
				return;
			}
			m.enhance.diceToEnhancement(m, item, player);
			e.getWhoClicked().closeInventory();

		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Survival")) {
			e.setCancelled(true);
			e.getWhoClicked().setGameMode(GameMode.SURVIVAL);
			e.getWhoClicked().closeInventory();
		}
	}

	@EventHandler
	public void onInventoryDrag(InventoryDragEvent e) {

	}
}