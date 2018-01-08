package com.github.healpot.plugin.enhancement.me.handler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class PlayerStreamHandler implements Listener {

	public Main m;

	public PlayerStreamHandler(Main m) {
		this.m = m;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		m.sendMessage(
				m.settings.getLang().getString("Config.pluginTag")
						+ m.settings.getLang().getString("Config.welcome").replaceAll("%player%", player.getName()),
				player);
		m.failstack.loadLevels(m, player);
		m.secretbook.loadStorage(m, player);
		m.inventory.loadInventory(m, player);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		m.failstack.saveLevels(m, player, false);
		m.secretbook.saveStorageToDisk(m, player, false);
		m.inventory.saveInventoryToDisk(m, player, false);
	}

	@EventHandler
	public void onKick(PlayerKickEvent e) {
		Player player = e.getPlayer();
		m.failstack.saveLevels(m, player, false);
		m.secretbook.saveStorageToDisk(m, player, false);
		m.inventory.saveInventoryToDisk(m, player, false);
	}

}