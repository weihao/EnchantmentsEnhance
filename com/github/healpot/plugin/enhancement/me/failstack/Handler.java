package com.github.healpot.plugin.enhancement.me.failstack;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.healpot.plugin.enhancement.me.main.Main;

public class Handler implements Listener {

	public Main m;

	public Handler(Main m) {
		this.m = m;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		m.failstack.loadLevels(m, event.getPlayer());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		m.failstack.saveLevels(m, event.getPlayer(), false);
	}

	@EventHandler
	public void onKick(PlayerKickEvent event) {
		m.failstack.saveLevels(m, event.getPlayer(), false);
	}

}