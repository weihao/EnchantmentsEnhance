package me.bukkit.effect;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlaySound {

	public void playSound(Player p) {
		p.playSound(p.getLocation(), Sound.AMBIENCE_CAVE, 1.0F, 2.0F);
	}
}
