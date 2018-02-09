package org.pixeltime.healpot.enhancement.interfaces;

import org.bukkit.entity.Player;

public interface Displayable {
    /**
     * Opens a GUI to a player.
     * 
     * @param player
     */
    public void openInventory(Player player);


    /**
     * Gets a count of a player's enhancement stone.
     * 
     * @param player
     * @param stoneId
     * @return
     */
    public String getOneStoneCountAsString(Player player, int stoneId);
}
