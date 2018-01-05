package com.github.healpot.plugin.enhancement.me.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Annoucer implements Listener {
	private Main m;

	public Annoucer(Main m) {
		this.m = m;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		m.sendMessage(
				m.settings.getLang().getString("Config.pluginTag")
						+ m.settings.getLang().getString("Config.welcome").replaceAll("%player%", player.getName()),
				player);
	}
}
