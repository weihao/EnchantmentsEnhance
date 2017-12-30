package me.bukkit.lore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Data {
	public static String pluginName(FileConfiguration lang) {
		String pluginName = ChatColor.translateAlternateColorCodes('&', lang.getString("Config.PluginTag"));

		return pluginName;
	}

	public static void sendMessage(String msg, CommandSender sender) {
		String message = ChatColor.translateAlternateColorCodes('&', msg);
		sender.sendMessage(message);
	}

	public static void addLore(ItemStack is, Player p, String lore, FileConfiguration lang, boolean tradeable) {
		ItemMeta im = is.getItemMeta();
		List<String> loreList = new ArrayList<String>();

		String x = null;
		String y = null;
		if (tradeable) {
			x = "Tradeable";
			y = "Untradeable";
		} else {
			x = "Untradeable";
			y = "Tradeable";
		}

		if ((is.hasItemMeta()) && (is.getItemMeta().hasLore())) {
			int loreSize = is.getItemMeta().getLore().size();
			for (int i = 0; i < loreSize; i++) {
				loreList.add(ChatColor.translateAlternateColorCodes('&', (String) is.getItemMeta().getLore().get(i)));
			}
			if (loreList.contains(lore)) {
				// sendMessage(lang.getString("Messages.Already" + x), p);
				return;
			}
			if (loreList.contains(ChatColor.translateAlternateColorCodes('&', lang.getString("Lore." + y + "Lore")))) {
				loreList.remove(ChatColor.translateAlternateColorCodes('&', lang.getString("Lore." + y + "Lore")));
			}
			loreList.add(lore);
			im.setLore(loreList);
			is.setItemMeta(im);
			// sendMessage(lang.getString("Messages.Made" + x), p);
			return;
		}
		im.setLore(Arrays.asList(new String[] { lore }));
		is.setItemMeta(im);
		p.updateInventory();
		// sendMessage(lang.getString("Messages.Made" + x), p);
	}

	public static void removeLore(ItemStack is, Player p, FileConfiguration lang) {
		ItemMeta im = is.getItemMeta();
		List<String> loreList = new ArrayList<String>();
		String x = ChatColor.translateAlternateColorCodes('&', lang.getString("Lore.TradeableLore"));
		String y = ChatColor.translateAlternateColorCodes('&', lang.getString("Lore.UntradeableLore"));
		if ((is.hasItemMeta()) && (is.getItemMeta().hasLore())) {
			int loreSize = is.getItemMeta().getLore().size();
			for (int i = 0; i < loreSize; i++) {
				loreList.add(ChatColor.translateAlternateColorCodes('&', (String) is.getItemMeta().getLore().get(i)));
			}
			if ((loreList.contains(x)) || (loreList.contains(y))) {
				loreList.remove(x);
				loreList.remove(y);
			}
			im.setLore(loreList);
			is.setItemMeta(im);
			sendMessage(lang.getString("Messages.MadeUnbound"), p);
			return;
		}
		sendMessage(lang.getString("Messages.AlreadyUnbound"), p);
	}
}
