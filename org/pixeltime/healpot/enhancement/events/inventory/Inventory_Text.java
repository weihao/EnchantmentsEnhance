package org.pixeltime.healpot.enhancement.events.inventory;

import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.interfaces.Displayable;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.Util;

public class Inventory_Text implements Displayable {
    public void openInventory(Player player) {
        int[] inv = Inventory.getPlayer(player);
        Util.sendMessage(SettingsManager.lang.getString("Item.title"), player);
        for (int i = 0; i < inv.length; i++) {
            Util.sendMessage(SettingsManager.lang.getString("Item.listing")
                .replaceAll("%ITEM%", SettingsManager.lang.getString("Item."
                    + i)).replaceAll("%COUNT%", Integer.toString(inv[i])),
                player);

        }
    }


    public String getOneStoneCountAsString(Player player, int stoneId) {
        int[] inv = Inventory.getPlayer(player);
        return (SettingsManager.lang.getString("Item.listing").replaceAll(
            "%ITEM%", SettingsManager.lang.getString("Item." + stoneId))
            .replaceAll("%COUNT%", Integer.toString(inv[stoneId])));

    }

}
