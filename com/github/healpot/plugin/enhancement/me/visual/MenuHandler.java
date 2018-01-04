package com.github.healpot.plugin.enhancement.me.visual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import com.github.healpot.plugin.enhancement.me.lore.Data;
import com.github.healpot.plugin.enhancement.me.main.Main;

public class MenuHandler implements Listener {
	private Main m;
	private Map<Player, ItemStack> itemOnEnhancingSlot = new HashMap<Player, ItemStack>();
	Data data = new Data();

	public MenuHandler(Main m) {
		this.m = m;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getSlot() < 0) {
			return;
		}
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		if (e.getCurrentItem().getType() == (Material.AIR)) {
			return;
		}
		Player player = (Player) e.getWhoClicked();

		if (!e.getInventory().getName().equalsIgnoreCase(m.menu.getScreen().getName())) {
			List<String> loreList = new ArrayList<String>();
			if ((e.getInventory().getType() != InventoryType.CRAFTING)
					&& (e.getInventory().getType() != InventoryType.PLAYER)) {
				if ((e.getClick().equals(ClickType.NUMBER_KEY))
						&& (player.getInventory().getItem(e.getHotbarButton()) != null)) {
					ItemStack itemMoved = player.getInventory().getItem(e.getHotbarButton());
					if ((itemMoved.getItemMeta().hasLore())) {
						int loreSize = itemMoved.getItemMeta().getLore().size();
						for (int i = 0; i < loreSize; i++) {
							loreList.add((String) itemMoved.getItemMeta().getLore().get(i));
						}
						if (loreList.contains(ChatColor.translateAlternateColorCodes('&',
								m.getConfig().getString("Lore.UntradeableLore")))) {
							e.setCancelled(true);
							Data.sendMessage(m.getConfig().getString("Messages.NoStorage"), player);
						}
					}
				}
				if (e.getCurrentItem() != null) {
					if ((e.getCurrentItem().hasItemMeta()) && (e.getCurrentItem().getItemMeta().hasLore())) {
						int loreSize = e.getCurrentItem().getItemMeta().getLore().size();
						for (int i = 0; i < loreSize; i++) {
							loreList.add((String) e.getCurrentItem().getItemMeta().getLore().get(i));
						}
						if (loreList.contains(ChatColor.translateAlternateColorCodes('&',
								m.getConfig().getString("Lore.UntradeableLore")))) {
							e.setCancelled(true);
							Data.sendMessage(m.getConfig().getString("Messages.NoStorage"), player);
						}
					}
				}
			}
		} else {
			e.setCancelled(true);
			if (e.getCurrentItem().hasItemMeta()) {
				if (m.enhance.getValidationOfItem(m, player, e.getCurrentItem()) == true
						&& !itemOnEnhancingSlot.containsKey(player)) {
					m.menu.updateInv(m, e.getCurrentItem(), player);
					itemOnEnhancingSlot.put(player, e.getCurrentItem());
					m.menu.addRemoveButton();
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Enhance")
						&& itemOnEnhancingSlot.containsKey(player)) {
					m.enhance.diceToEnhancement(m, itemOnEnhancingSlot.get(player), player);
					m.menu.updateFailstack(m, itemOnEnhancingSlot.get(player), player);
					m.menu.updateInSlotItem(m, itemOnEnhancingSlot.get(player), player);
					return;
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Remove")
						&& itemOnEnhancingSlot.containsKey(player)) {
					itemOnEnhancingSlot.remove(player);
					m.menu.createMenu();
					return;
				}
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Force")
						&& itemOnEnhancingSlot.containsKey(player)) {
					m.enhance.forceToEnhancement(m, itemOnEnhancingSlot.get(player), player);
					m.menu.updateFailstack(m, itemOnEnhancingSlot.get(player), player);
					m.menu.updateInSlotItem(m, itemOnEnhancingSlot.get(player), player);
					return;
				}
			} else if (m.enhance.getValidationOfItem(m, player, e.getCurrentItem()) == true
					&& !itemOnEnhancingSlot.containsKey(player)) {
				m.menu.updateInv(m, e.getCurrentItem(), player);
				itemOnEnhancingSlot.put(player, e.getCurrentItem());
				m.menu.addRemoveButton();
			}
		}
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		if (itemOnEnhancingSlot.containsKey(e.getPlayer())) {
			itemOnEnhancingSlot.remove(e.getPlayer());
		}
		if (!e.getInventory().getName().equalsIgnoreCase(m.menu.getScreen().getName())) {
			return;
		}
	}

	public static int getSlot(int x, int y) {
		return (y * 9) - (9 - x) - 1;
	}

}