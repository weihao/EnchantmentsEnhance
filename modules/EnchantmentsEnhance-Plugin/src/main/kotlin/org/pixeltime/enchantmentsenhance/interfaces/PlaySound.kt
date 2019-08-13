package org.pixeltime.enchantmentsenhance.interfaces

import org.bukkit.entity.Player

interface PlaySound {
    /**
     * Plays a sound to a player.
     *
     * @param p
     * @param type
     */
    fun playSound(p: Player, type: String)
}
