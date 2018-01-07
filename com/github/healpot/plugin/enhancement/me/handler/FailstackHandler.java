package com.github.healpot.plugin.enhancement.me.handler;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class FailstackHandler implements Listener {

	public Main m;

	public FailstackHandler(Main m) {
		this.m = m;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		m.sendMessage(m.settings.getLang().getString("Config.pluginTag")
				+ m.settings.getLang().getString("Config.welcome").replaceAll("%player%", e.getPlayer().getName()),
				e.getPlayer());
		m.failstack.loadLevels(m, e.getPlayer());
		m.secretbook.loadStorage(m, e.getPlayer());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		m.failstack.saveLevels(m, e.getPlayer(), false);
		m.secretbook.saveStorageToDisk(m, e.getPlayer(), false);
	}

	@EventHandler
	public void onKick(PlayerKickEvent e) {
		m.failstack.saveLevels(m, e.getPlayer(), false);
		m.secretbook.saveStorageToDisk(m, e.getPlayer(), false);
	}

}