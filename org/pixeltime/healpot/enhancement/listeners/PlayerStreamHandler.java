package org.pixeltime.healpot.enhancement.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.pixeltime.healpot.enhancement.events.Inventory;
import org.pixeltime.healpot.enhancement.events.blacksmith.SecretBook;
import org.pixeltime.healpot.enhancement.events.blackspirit.Failstack;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.tasks.Main;
import org.pixeltime.healpot.enhancement.util.Util;

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