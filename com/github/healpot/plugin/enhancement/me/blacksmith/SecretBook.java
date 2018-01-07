package com.github.healpot.plugin.enhancement.me.blacksmith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.bukkit.entity.Player;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class SecretBook {
	private HashMap<Player, List<Integer>> storage = new HashMap<Player, List<Integer>>();

	public void addFailstackToStorage(Main m, Player player) {
		storage.get(player).add(m.failstack.getLevel(m, player));
		m.failstack.resetLevel(m, player);
	}

	public void loadStorage(Main m, Player player) {
		List<Integer> adviceOfValks = new ArrayList<Integer>();
		if (m.settings.getData().contains("AdviceOfValks." + player.getName()) || storage.containsKey(player)) {
			Scanner sc = new Scanner(m.settings.getData().getString("AdviceOfValks." + player.getName()));
			while (sc.hasNext()) {
				adviceOfValks.add(sc.nextInt());
			}
			sc.close();
		}
		storage.put(player, adviceOfValks);
	}

	public void saveStorageToDisk(Main m, Player player, boolean save) {
		List<Integer> working = storage.get(player);
		String result = "";
		if (working.size() > 0) {
			for (int i = 0; i < working.size(); i++) {
				result += storage.get(player).get(i) + " ";
			}
			m.settings.getData().set("AdviceOfValks." + player.getName(), result);
		}

		if (save)
			m.settings.saveData();
	}

}
