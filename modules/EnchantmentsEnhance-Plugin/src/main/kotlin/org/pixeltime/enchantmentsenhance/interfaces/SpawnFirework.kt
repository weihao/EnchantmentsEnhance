package org.pixeltime.enchantmentsenhance.interfaces

import org.bukkit.entity.Player

interface SpawnFirework {
    fun launch(player: Player, fireworkCount: Int)

    fun launch(
        player: Player,
        fireworkCount: Int,
        fireWorkRounds: Int,
        delay: Int
    )
}
