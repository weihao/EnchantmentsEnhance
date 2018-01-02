package me.bukkit.main;

import org.bukkit.ChatColor;
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
		Player p = e.getPlayer();
		p.sendMessage(ChatColor.translateAlternateColorCodes('&',
				m.settings.getLang().getString("Config.welcome").replaceAll("%player%", p.getName())));
	}

	public void broadcast() {

	}

}
