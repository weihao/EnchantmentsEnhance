package org.pixeltime.enchantmentsenhance.manager.modular;

import com.lgou2w.ldk.bukkit.compatibility.Sounds;
import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.interfaces.PlaySound;

public class PlaySound_Safe implements PlaySound {
    public void playSound(Player p, String type) {
        Types playingType = Types.valueOf(type.toUpperCase());

        switch (playingType) {
            case SUCCESS:
                p.playSound(p.getLocation(), Sounds.NOTE_PLING.toBukkit(),
                        1.0F, 2.0F);
                break;
            case FAILED:
                p.playSound(p.getLocation(), Sounds.ANVIL_BREAK.toBukkit(),
                        1.0F, 2.0F);
                break;
            case DOWNGRADED:
                p.playSound(p.getLocation(), Sounds.EXPLODE.toBukkit(), 1.0F,
                        2.0F);
                break;
            default:
                p.playSound(p.getLocation(), Sounds.ANVIL_USE.toBukkit(),
                        1.0F, 2.0F);
        }
    }


    public enum Types {
        SUCCESS, FAILED, DOWNGRADED
    }
}
