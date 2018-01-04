package com.github.healpot.plugin.enhancement.me.lore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class playerdeath implements Listener {
	private Main plugin;

	public playerdeath(Main pl) {
		this.plugin = pl;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		List<ItemStack> newInventory = new ArrayList<ItemStack>();
		UUID uuid = p.getUniqueId();

		File playerFile = new File(this.plugin.getDataFolder() + "/Data" + "/Players/" + uuid.toString() + ".yml");
		FileConfiguration pFile = YamlConfiguration.loadConfiguration(playerFile);

		pFile.set("PlayerName", p.getDisplayName());
		for (int i = 0; i < e.getDrops().size(); i++) {
			if ((((ItemStack) e.getDrops().get(i)).hasItemMeta())
					&& (((ItemStack) e.getDrops().get(i)).getItemMeta().hasLore())) {
				if ((((ItemStack) e.getDrops().get(i)).getItemMeta().getLore()
						.contains(ChatColor.translateAlternateColorCodes('&',
								this.plugin.settings.getLang().getString("Lore.UntradeableLore"))))
						||

						(((ItemStack) e.getDrops().get(i)).getItemMeta().getLore()
								.contains(ChatColor.translateAlternateColorCodes('&',
										this.plugin.settings.getLang().getString("Lore.TradeableLore"))))) {
					newInventory.add((ItemStack) e.getDrops().get(i));
				}
			}
		}
		ItemStack[] newStack = newInventory.toArray(new ItemStack[newInventory.size()]);
		pFile.set("Items", newStack);
		try {
			pFile.save(playerFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		e.getDrops().removeAll(newInventory);
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();

		UUID uuid = p.getUniqueId();

		File playerFile = new File(this.plugin.getDataFolder() + "/Data" + "/Players/" + uuid.toString() + ".yml");
		FileConfiguration pFile = YamlConfiguration.loadConfiguration(playerFile);
		if (playerFile.exists()) {
			ItemStack[] content = (ItemStack[]) ((List<?>) pFile.get("Items")).toArray(new ItemStack[0]);
			p.getInventory().addItem(content);
			playerFile.delete();
		}
	}
}
