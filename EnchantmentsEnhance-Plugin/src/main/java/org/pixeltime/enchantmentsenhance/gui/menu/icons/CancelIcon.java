/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

import com.lgou2w.ldk.bukkit.compatibility.DyeColors;
import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class CancelIcon extends Clickable {

    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(XMaterial.RED_WOOL.toBukkit())
                .setDyeColor(DyeColors.RED.toBukkit())
                .setName(SettingsManager.lang.getString("menu.gui.cancel"))
                .addLoreLine(SettingsManager.lang.getString("menu.lore.cancel"))
                .toItemStack();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(4, 5);
    }
}
