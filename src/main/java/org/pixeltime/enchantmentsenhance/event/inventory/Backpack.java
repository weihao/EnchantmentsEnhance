/*
 *     Copyright (C) 2017-Present HealPotion
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.event.inventory;

import org.bukkit.entity.Player;
import org.pixeltime.enchantmentsenhance.gui.GUIAbstract;
import org.pixeltime.enchantmentsenhance.gui.menu.icons.StoneIcon;
import org.pixeltime.enchantmentsenhance.manager.MM;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

/**
 * @author HealPotion
 * @version Feb 9, 2018
 */
public class Backpack extends GUIAbstract {

    public Backpack(Player p) {
        super(p, 27, SettingsManager.lang.getString("Menu.gui.title"));
        for (int i = 0; i < MM.stoneTypes.size(); i++) {
            setItem(Util.getSlot(i + 1, 1 + (i / 9)), new StoneIcon().getItem(i, p), () -> Util.sendMessage("You can't take this out for now.",
                    p));
        }
    }


    @Override
    public void update() {

    }
}
