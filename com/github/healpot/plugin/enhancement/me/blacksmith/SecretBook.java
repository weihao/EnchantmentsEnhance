package com.github.healpot.plugin.enhancement.me.blacksmith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.bukkit.entity.Player;

import com.github.healpot.plugin.enhancement.me.failstack.Failstack;
import com.github.healpot.plugin.enhancement.me.main.Main;

public class SecretBook {
	private List<Integer> adviceOfValks = new ArrayList<Integer>();
	private HashMap<Player, List<Integer>> storage = new HashMap<Player, List<Integer>>();

	public void addFailstackToStorage(Main m, Player player, Failstack fs) {
		storage.get(player).add(fs.getLevel(m, player));
		fs.resetLevel(m, player);
	}

	public void loadStorage(Main m, Player player) {
		if (m.settings.getData().contains("AdviceOfValks." + player.getName()) || storage.containsKey(player)) {
			List<Integer> placeholder = new ArrayList<Integer>();
			Scanner sc = new Scanner(m.settings.getData().getString("AdviceOfValks." + player.getName()));
			while (sc.next() != ".") {
				if (sc.hasNextInt()) {
					placeholder.add(sc.nextInt());
				} else {
					sc.next();
				}
			}

			storage.put(player, placeholder);
		}
	}

	public void saveStorageToDisk(Main m, Player player, boolean save) {
		List<Integer> working = storage.get(player);
		String result = "";
		if (working.size() > 0) {
			for (int i = 0; i < working.size(); i++) {
				result += storage.get(player).get(i) + ",";
			}
			result += ".";
			m.settings.getData().set("AdviceOfValks." + player.getName(), result);
		}

		if (save)
			m.settings.saveData();
	}

}
