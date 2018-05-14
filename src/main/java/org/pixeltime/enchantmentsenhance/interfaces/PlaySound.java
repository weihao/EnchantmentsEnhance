package org.pixeltime.enchantmentsenhance.interfaces;

import org.bukkit.entity.Player;

public interface PlaySound {
    /**
     * Plays a sound to a player.
     *
     * @param p
     * @param type
     */
     void playSound(Player p, String type);
}
