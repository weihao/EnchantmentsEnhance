package com.github.healpot.plugin.enhancement.me.lore;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class ItemDrop implements Listener {
	private Main m;

	public ItemDrop(Main m) {
		this.m = m;
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		Item droppedItem = e.getItemDrop();
		ItemStack DroppedItemStack = droppedItem.getItemStack();
		Player p = e.getPlayer();
		List<String> loreList = new ArrayList<String>();
		if ((DroppedItemStack.hasItemMeta()) && (DroppedItemStack.getItemMeta().getLore() != null)) {
			int loreSize = DroppedItemStack.getItemMeta().getLore().size();
			for (int i = 0; i < loreSize; i++) {
				loreList.add((String) DroppedItemStack.getItemMeta().getLore().get(i));
			}
			if (loreList.contains(ChatColor.translateAlternateColorCodes('&',
					m.settings.getLang().getString("Lore.UntradeableLore")))) {
				e.setCancelled(true);
				m.sendMessage(m.settings.getLang().getString("Config.pluginTag")
						+ m.settings.getLang().getString("Messages.NoDrop"), p);
				return;
			}
		}
	}
}
