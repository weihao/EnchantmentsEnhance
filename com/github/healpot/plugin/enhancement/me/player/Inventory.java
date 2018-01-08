package com.github.healpot.plugin.enhancement.me.player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.bukkit.entity.Player;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class Inventory {
	// int[0] = weapon stone, int[1] = armor stone, int[2] = conc weapon; int[3] =
	// conc armor.
	private Map<Player, int[]> backpack = new HashMap<Player, int[]>();

	public void loadInventory(Main m, Player player) {
		int[] temp = new int[] { 0, 0, 0, 0 };
		if (m.settings.getData().contains("backpack." + player.getName()) || backpack.containsKey(player)) {
			Scanner sc = new Scanner(m.settings.getData().getString("backpack." + player.getName()));
			for (int i = 0; i < temp.length; i++) {
				temp[i] = sc.nextInt();
			}
			sc.close();
			backpack.put(player, temp);
		} else {
			backpack.put(player, new int[] { 0, 0, 0, 0 });
		}
	}

	public void saveInventoryToDisk(Main m, Player player, boolean save) {
		String str = "";
		for (int i = 0; i < 4; i++) {
			str += getLevel(m, i, player) + " ";
		}
		m.settings.getData().set("backpack." + player.getName(), str);

		if (save) {
			m.settings.saveData();
		}

	}

	public void setLevel(Main m, Player player, int type, int level) {
		backpack.get(player)[type] = level;
	}

	public void addLevel(Main m, Player player, int type, int levelsToAdd) {
		int newLevel = getLevel(m, type, player) + levelsToAdd;
		setLevel(m, player, type, newLevel);
	}

	public int getLevel(Main m, int type, Player player) {
		if (backpack.containsKey(player)) {
			return backpack.get(player)[type];
		}
		return 0;
	}

	public void printInventory(Main m, Player player) {
		int[] inv = backpack.get(player);

		m.sendMessage(m.settings.getLang().getString("Item.title"), player);
		for (int i = 0; i < inv.length; i++) {
			m.sendMessage(m.settings.getLang().getString("Item.listing")
					.replaceAll("%ITEM%", m.settings.getLang().getString("Item." + i))
					.replaceAll("%COUNT%", Integer.toString(inv[i])), player);

		}
	}

	public String getOneStoneCountAsString(Main m, Player player, int stoneId) {
		int[] inv = backpack.get(player);
		return (m.settings.getLang().getString("Item.listing")
				.replaceAll("%ITEM%", m.settings.getLang().getString("Item." + stoneId))
				.replaceAll("%COUNT%", Integer.toString(inv[stoneId])));

	}
}
