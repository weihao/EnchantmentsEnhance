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

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.event.Enhance;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.MaterialManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class StoneIcon extends Clickable {

    public ItemStack getItem(int stoneId, Player player) {
        return new ItemBuilder(MaterialManager.stoneTypes.get(stoneId),
                (ItemIcon.getOneStoneCountAsCount(player.getName(), stoneId) > 64
                        ? 64 : ItemIcon.getOneStoneCountAsCount(player.getName(), stoneId)))
                .setName(SettingsManager.lang.getString("Item." + stoneId))
                .addLoreLine(ItemIcon.getOneStoneCountAsString(player.getName(), stoneId))
                .addLoreLine(SettingsManager.lang.getString("Menu.leftInfo"))
                .addLoreLine(SettingsManager.lang.getString("Menu.rightInfo"))
                .toItemStack();
    }

    public ItemStack getItem(int stoneId) {
        return new ItemBuilder(MaterialManager.stoneTypes.get(stoneId), (1)).setName(SettingsManager.lang.getString("Item." + stoneId)).addLoreLine(ItemIcon.getOneStoneCountAsString(null, stoneId)).toItemStack();
    }

    public ItemStack getItem(ItemStack item, Player player) {
        int stoneId = Enhance.getStoneId(item, ItemManager.getItemEnchantLevel(item) + 1);
        return getItem(stoneId, player);
    }

    @Override
    public ItemStack getItem(String playerName) {
        return null;
    }

    @Override
    public int getPosition() {
        return Util.getSlot(2, 4);
    }
}
