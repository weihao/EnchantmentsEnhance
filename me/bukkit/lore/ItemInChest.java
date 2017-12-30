package me.bukkit.lore;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import me.bukkit.main.Main;

public class ItemInChest implements Listener {
	private Main plugin;
	Data data = new Data();

	public ItemInChest(Main pl) {
		this.plugin = pl;
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		List<String> loreList = new ArrayList<String>();
		if ((e.getInventory().getType() != InventoryType.CRAFTING)
				&& (e.getInventory().getType() != InventoryType.PLAYER)) {
			if ((e.getClick().equals(ClickType.NUMBER_KEY))
					&& (e.getWhoClicked().getInventory().getItem(e.getHotbarButton()) != null)) {
				ItemStack itemMoved = e.getWhoClicked().getInventory().getItem(e.getHotbarButton());
				if ((itemMoved.hasItemMeta()) && (itemMoved.getItemMeta().hasLore())) {
					int loreSize = itemMoved.getItemMeta().getLore().size();
					for (int i = 0; i < loreSize; i++) {
						loreList.add((String) itemMoved.getItemMeta().getLore().get(i));
					}
					if (loreList.contains(ChatColor.translateAlternateColorCodes('&',
							this.plugin.getConfig().getString("Lore.UntradeableLore")))) {
						e.setCancelled(true);
						Data.sendMessage(this.plugin.getConfig().getString("Messages.NoStorage"), e.getWhoClicked());
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
							this.plugin.getConfig().getString("Lore.UntradeableLore")))) {
						e.setCancelled(true);
						Data.sendMessage(this.plugin.getConfig().getString("Messages.NoStorage"), e.getWhoClicked());
					}
				}
			}
		}
	}
}
