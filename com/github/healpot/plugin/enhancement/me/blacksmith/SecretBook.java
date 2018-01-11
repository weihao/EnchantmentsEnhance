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
		m.sendMessage(m.settings.getLang().getString("Config.pluginTag")
				+ m.settings.getLang().getString("Save.createFailstack").replaceAll("%failstack%",
						Integer.toString(m.failstack.getLevel(m, player))),
				player);
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

	public void list(Main m, Player player, int pageNumber) {
		List<Integer> adviceOfValks = storage.get(player);

		if (adviceOfValks.size() <= 0 || adviceOfValks == null) {
			m.sendMessage(m.settings.getLang().getString("Config.pluginTag")
					+ m.settings.getLang().getString("Save.noFailstack"), player);
			return;
		}

		int page = 1;
		if (pageNumber > 1) {
			try {
				page = pageNumber;
			} catch (Exception e) {
			}
			if (pageNumber <= 0) {
				page = 1;
			}
		}

		int count = 0;

		m.sendMessage(m.settings.getLang().getString("Config.pluginTag")
				+ m.settings.getLang().getString("Save.failstackTitle").replaceAll("%page%", Integer.toString(page)),
				player);
		for (Integer fs : adviceOfValks) {
			count++;
			m.sendMessage(m.settings.getLang().getString("Config.pluginTag")
					+ m.settings.getLang().getString("Save.listing").replaceAll("%NUMBER%", Integer.toString(count))
							.replaceAll("%FAILSTACK%", Integer.toString(fs)),
					player);

		}

	}

	public void select(Main m, Player player, int selectedFailstack) {
		if (selectedFailstack > 0 && m.failstack.getLevel(m, player) == 0) {
			try {
				m.failstack.addLevel(m, player, m.secretbook.storage.get(player).get(selectedFailstack - 1));
				m.secretbook.storage.get(player).remove(selectedFailstack - 1);
			} catch (Exception e) {
				m.sendMessage(m.settings.getLang().getString("Config.pluginTag")
						+ m.settings.getLang().getString("Valks.noAdvice"), player);
			}
		} else
			m.sendMessage(m.settings.getLang().getString("Valks.noAdvicce")
					+ m.settings.getLang().getString("Valks.hasFailstack"), player);
	}
}