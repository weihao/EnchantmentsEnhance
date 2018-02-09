package org.pixeltime.healpot.enhancement.manager.modular;

import org.bukkit.entity.Player;

public interface PlaySound {
    /**
     * Plays a sound to a player.
     * 
     * @param p
     * @param type
     */
    public void playSound(Player p, String type);
}
