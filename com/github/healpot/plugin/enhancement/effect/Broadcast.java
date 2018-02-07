package com.github.healpot.plugin.enhancement.effect;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.github.healpot.plugin.enhancement.main.Main;
import com.github.healpot.plugin.enhancement.main.SettingsManager;
import com.github.healpot.plugin.enhancement.main.util.Util;

public class Broadcast {
	public void broadcast(Main m, Player player, ItemStack item, int enchantLevel, boolean success) {
		if (success) {
			Bukkit.broadcastMessage(Util.toColor(SettingsManager.lang.getString("Config.pluginTag")
					+ (SettingsManager.lang.getString("Annoucer.success") + player.getDisplayName()
							+ SettingsManager.lang.getString("Annoucer.got")
							+ SettingsManager.lang.getString("Name." + Integer.toString(enchantLevel + 1)) + " "
							+ getFriendlyName(item))));
		} else {
			Bukkit.broadcastMessage(Util.toColor(SettingsManager.lang.getString("Config.pluginTag")
					+ (SettingsManager.lang.getString("Annoucer.failed") + player.getDisplayName()
							+ SettingsManager.lang.getString("Annoucer.lost")
							+ SettingsManager.lang.getString("Name." + Integer.toString(enchantLevel + 1)) + " "
							+ getFriendlyName(item))));
		}
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
