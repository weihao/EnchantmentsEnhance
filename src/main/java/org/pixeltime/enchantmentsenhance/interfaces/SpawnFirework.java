package org.pixeltime.enchantmentsenhance.interfaces;

import org.bukkit.entity.Player;

public interface SpawnFirework {
     void launch(Player player, int fireworkCount);

     void launch(
            Player player,
            int fireworkCount,
            int fireWorkRounds,
            int delay);
}
