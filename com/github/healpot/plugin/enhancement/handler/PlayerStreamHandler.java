package com.github.healpot.plugin.enhancement.handler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import com.github.healpot.plugin.enhancement.blacksmith.SecretBook;
import com.github.healpot.plugin.enhancement.failstack.Failstack;
import com.github.healpot.plugin.enhancement.main.Main;
import com.github.healpot.plugin.enhancement.main.SettingsManager;
import com.github.healpot.plugin.enhancement.main.util.Util;
import com.github.healpot.plugin.enhancement.player.Inventory;

public class PlayerStreamHandler implements Listener {

	public Main m;

	public PlayerStreamHandler(Main m) {
		this.m = m;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		Util.sendMessage(
				SettingsManager.lang.getString("Config.pluginTag")
						+ SettingsManager.lang.getString("Config.welcome").replaceAll("%player%", player.getName()),
				player);
		Failstack.loadLevels(player);
		SecretBook.loadStorage(player);
		Inventory.loadInventory(player);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		Failstack.saveLevels(player, false);
		SecretBook.saveStorageToDisk(player, false);
		Inventory.saveInventoryToDisk(player, false);
	}

	@EventHandler
	public void onKick(PlayerKickEvent e) {
		Player player = e.getPlayer();
		Failstack.saveLevels(player, false);
		SecretBook.saveStorageToDisk(player, false);
		Inventory.saveInventoryToDisk(player, false);
	}

}