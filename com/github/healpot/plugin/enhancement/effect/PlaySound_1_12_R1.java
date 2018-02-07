package com.github.healpot.plugin.enhancement.effect;

import org.bukkit.entity.Player;
import com.github.healpot.plugin.enhancement.modular.Sounds;

public class PlaySound_1_12_R1 implements PlaySound {
	public enum Types {
		SUCCESS, FAILED, DOWNGRADED
	}

	public void playSound(Player p, String type) {
		Types playingType = Types.valueOf(type.toUpperCase());

		switch (playingType) {
		case SUCCESS:
			p.playSound(p.getLocation(), Sounds.LEVEL_UP.bukkitSound(), 1.0F, 2.0F);
			break;
		case FAILED:
			p.playSound(p.getLocation(), Sounds.ANVIL_BREAK.bukkitSound(), 1.0F, 2.0F);
			break;
		case DOWNGRADED:
			p.playSound(p.getLocation(), Sounds.EXPLODE.bukkitSound(), 1.0F, 2.0F);
			break;
		default:
			p.playSound(p.getLocation(), Sounds.ANVIL_USE.bukkitSound(), 1.0F, 2.0F);
		}
	}
}