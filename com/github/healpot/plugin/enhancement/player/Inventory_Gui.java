package com.github.healpot.plugin.enhancement.player;

import org.bukkit.entity.Player;
import com.github.healpot.plugin.enhancement.interfaces.Displayable;
import com.github.healpot.plugin.enhancement.main.SettingsManager;
import com.github.healpot.plugin.enhancement.visual.Backpack;

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
