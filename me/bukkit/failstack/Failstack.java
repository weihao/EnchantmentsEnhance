package me.bukkit.failstack;

import java.util.HashMap;

import org.bukkit.entity.Player;

import me.bukkit.main.Main;

public class Failstack {
	private HashMap<Player, Integer> failstack = new HashMap<Player, Integer>();

	public void loadLevels(Main m, Player player) {
		if (m.settings.getData().contains("Failstack." + player.getName()) || failstack.containsKey(player)) {
			failstack.put(player, m.settings.getData().getInt("Failstack." + player.getName()));
		}
	}

	public void saveLevels(Main m, Player player, boolean save) {
		m.settings.getData().set("Failstack." + player.getName(), getLevel(m, player));
		if (save)
			m.settings.saveData();
	}

	public void resetLevel(Main m, Player player) {
		setLevel(m, player, 0);
	}

	public void setLevel(Main m, Player player, int level) {
		failstack.put(player, level);
	}

	public int addLevel(Main m, Player player, int levelsToAdd) {
		int newLevel = getLevel(m, player) + levelsToAdd;
		setLevel(m, player, newLevel);
		return newLevel;
	}

	public int getLevel(Main m, Player player) {
		if (failstack.containsKey(player)) {
			return failstack.get(player);
		}
		return 0;
	}

	public double getChance(Main m, int enchantLevel, int failstack) {
		int fs = failstack;
		int maximumFailstack = m.settings.getConfig().getInt("maximumFailstack." + Integer.toString(enchantLevel));
		double baseChance = m.settings.getConfig().getDouble("baseChance." + Integer.toString(enchantLevel));
		double increasePerFailstack = m.settings.getConfig()
				.getDouble("increasePerFailstack." + Integer.toString(enchantLevel));

		if (fs > maximumFailstack) {
			fs = maximumFailstack;
		}

		return baseChance + fs * increasePerFailstack;
	}
}
