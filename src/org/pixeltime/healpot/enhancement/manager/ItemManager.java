package org.pixeltime.healpot.enhancement.manager;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.healpot.enhancement.Main;
import org.pixeltime.healpot.enhancement.util.ItemBuilder;
import org.pixeltime.healpot.enhancement.util.Util;

public class ItemManager {

    /**
     * Displays enhancement stone as a itemstack on GUI
     * 
     * @param stoneId
     * @param player
     * @return
     */
    public static ItemStack stoneVisualized(
        int stoneId,
        Player player,
        Boolean count) {
        List<String> lore = new ArrayList<String>();
        if (player != null) {
            lore.add(Util.toColor(Main.inventoryText.getOneStoneCountAsString(
                player, stoneId)));
        }
        return new ItemBuilder(Util.stoneTypes[stoneId]).setName(Util.toColor(
            SettingsManager.lang.getString("Item." + stoneId))).setLore(lore)
            .toItemStack();
    }


    public static ItemStack stoneVisualized(int stoneId, Player player) {
        List<String> lore = new ArrayList<String>();
        // TODO add lore.
        return new ItemBuilder(Util.stoneTypes[stoneId]).setName(Util.toColor(
            SettingsManager.lang.getString("Item." + stoneId))).setLore(lore)
            .toItemStack();
    }

}
