package org.pixeltime.healpot.enhancement.events;

import org.bukkit.entity.Player;
import org.pixeltime.healpot.enhancement.manager.ItemManager;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.util.GUI;
import org.pixeltime.healpot.enhancement.util.Util;

/**
 * Incomplete.
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
}
