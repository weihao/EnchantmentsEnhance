/*
 *     Copyright (C) 2017-Present HealPot
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

package org.pixeltime.enchantmentsenhance.gui.menu.icons;

import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;
import org.pixeltime.enchantmentsenhance.util.XMaterial;

public class StoreIcon extends Clickable {
    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(XMaterial.WRITABLE_BOOK.parseMaterial()).setName(SettingsManager.lang
                .getString("Menu.gui.store")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.store1")).addLoreLine(SettingsManager.lang.getString(
                "Menu.lore.store2")).toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(9, 1);
    }
}
