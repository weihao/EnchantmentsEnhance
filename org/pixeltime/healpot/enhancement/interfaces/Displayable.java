package org.pixeltime.healpot.enhancement.interfaces;

import org.bukkit.entity.Player;

public interface Displayable {
    public void openInventory(Player player);
    public String getOneStoneCountAsString(Player player, int stoneId);
}
