package org.pixeltime.healpot.enhancement.events.inventory;

import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.events.Backpack;
import org.pixeltime.healpot.enhancement.interfaces.Displayable;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;

public class Inventory_Gui implements Displayable {
    public void openInventory(Player player) {
        Backpack.showBackpack(player);
    }


    public String getOneStoneCountAsString(Player player, int stoneId) {
        int[] inv = Inventory.getPlayer(player);
        return (SettingsManager.lang.getString("Item.listing").replaceAll(
            "%ITEM%", SettingsManager.lang.getString("Item." + stoneId))
            .replaceAll("%COUNT%", Integer.toString(inv[stoneId])));

    }
}
