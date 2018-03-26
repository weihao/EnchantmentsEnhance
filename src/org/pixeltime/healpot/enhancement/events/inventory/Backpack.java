package org.pixeltime.healpot.enhancement.events.inventory;

import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.manager.ItemManager;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.GUI;
import org.pixeltime.healpot.enhancement.util.Util;

/**
 *
 * @author HealPot
 * @version Feb 9, 2018
 *
 */
public class Backpack extends GUI {

    public Backpack(Player p) {
        super(27, SettingsManager.lang.getString("Menu.gui.title"));
        for (int i = 0; i < Util.stoneTypes.length; i++) {
            setItem(Util.getSlot(i + 1, 1 + (i / 9)), ItemManager
                .stoneVisualized(i, p, true));
        }
    }
    
    public static void sendInventoryAsText(Player player) {
        int[] inv = Inventory.getPlayer(player);
        Util.sendMessage(SettingsManager.lang.getString("Item.title"), player);
        for (int i = 0; i < inv.length; i++) {
            Util.sendMessage(SettingsManager.lang.getString("Item.listing")
                .replaceAll("%ITEM%", SettingsManager.lang.getString("Item."
                    + i)).replaceAll("%COUNT%", Integer.toString(inv[i])),
                player);

        }
    }

    public static String getOneStoneCountAsString(Player player, int stoneId) {
        int[] inv = Inventory.getPlayer(player);
        return (SettingsManager.lang.getString("Item.listing").replaceAll(
            "%ITEM%", SettingsManager.lang.getString("Item." + stoneId))
            .replaceAll("%COUNT%", Integer.toString(inv[stoneId])));

    }
}
