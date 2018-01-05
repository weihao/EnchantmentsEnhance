package com.github.healpot.plugin.enhancement.me.effect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class Broadcast {
	public void broadcast(Main m, Player player, ItemStack item, int enchantLevel, boolean success) {
		if (success) {
			Bukkit.broadcastMessage(toColor(m.settings.getLang().getString("Config.pluginTag"))
					+ (m.settings.getLang().getString("Annoucer.success") + player.getDisplayName()
							+ m.settings.getLang().getString("Annoucer.got")
							+ m.settings.getLang().getString("Name." + Integer.toString(enchantLevel + 1)) + " "
							+ getFriendlyName(item)));
		} else {
			Bukkit.broadcastMessage(toColor(m.settings.getLang().getString("Config.pluginTag"))
					+ (m.settings.getLang().getString("Annoucer.failed") + player.getDisplayName()
							+ m.settings.getLang().getString("Annoucer.lost")
							+ m.settings.getLang().getString("Name." + Integer.toString(enchantLevel + 1)) + " "
							+ getFriendlyName(item)));
		}
	}

	public String toColor(String str) {
		return ChatColor.translateAlternateColorCodes('&', str);
	}

	public String format(String s) {
		if (!s.contains("_")) {
			return capitalize(s);
		}
		String[] j = s.split("_");

		String c = "";

		for (String f : j) {
			f = capitalize(f);
			c += c.equalsIgnoreCase("") ? f : " " + f;
		}
		return c;
	}

	public String capitalize(String text) {
		String firstLetter = text.substring(0, 1).toUpperCase();
		String next = text.substring(1).toLowerCase();
		String capitalized = firstLetter + next;

		return capitalized;
	}

	public String getFriendlyName(ItemStack item) {

		Material m = item.getType();
		String name = format(m.name());

		return name;
	}
}
