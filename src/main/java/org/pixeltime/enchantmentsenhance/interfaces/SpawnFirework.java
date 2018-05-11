package org.pixeltime.enchantmentsenhance.interfaces;

import org.bukkit.entity.Player;

public interface SpawnFirework {
    public void launch(Player player, int fireworkCount);

    public void launch(
            Player player,
            int fireworkCount,
            int fireWorkRounds,
            int delay);
}
