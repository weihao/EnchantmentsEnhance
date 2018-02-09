package org.pixeltime.healpot.enhancement.manager.modular;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlaySound_1_7_R4 implements PlaySound {
    public enum Types {
        SUCCESS, FAILED, DOWNGRADED
    }


    public void playSound(Player p, String type) {
        Types playingType = Types.valueOf(type.toUpperCase());

        switch (playingType) {
            case SUCCESS:
                p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0F, 2.0F);
                break;
            case FAILED:
                p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1.0F, 2.0F);
                break;
            case DOWNGRADED:
                p.playSound(p.getLocation(), Sound.EXPLODE, 1.0F, 2.0F);
                break;
            default:
                p.playSound(p.getLocation(), Sound.ANVIL_USE, 1.0F, 2.0F);
        }
    }
}
