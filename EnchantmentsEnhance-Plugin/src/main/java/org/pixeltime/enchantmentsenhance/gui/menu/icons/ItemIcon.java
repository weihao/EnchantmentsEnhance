/*
 *     Copyright (C) 2017-Present 25
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

import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ItemIcon extends Clickable {

    public static String getOneStoneCountAsString(String player, int stoneId) {
        int count = player == null ? 0 : Main.getApi().getItem(player, stoneId);
        return (SettingsManager.lang.getString("item.listing").replaceAll(
                "%ITEM%", SettingsManager.lang.getString("item." + stoneId))
                .replaceAll("%COUNT%", String.valueOf(count)));
    }

    public static int getOneStoneCountAsInt(String player, int stoneId) {
        return Main.getApi().getItem(player, stoneId);
    }

    public static int getOneStoneCountAsCount(String player, int stoneId) {
        return (getOneStoneCountAsInt(player, stoneId) <= 0) ? 1 : getOneStoneCountAsInt(player, stoneId);
    }


    public ItemStack getItem() {
        return (new ItemBuilder(XMaterial.CHEST_MINECART.toBukkit())
                .setName(SettingsManager.lang.getString("item.gui"))
                .addLoreLine(SettingsManager.lang.getString("item.gui1"))
                .toItemStack());
    }

    @Override
    public ItemStack getItem(String player) {
        PlayerStat stat = PlayerStat.getPlayerStats(player);
        if (stat != null && stat.getItems() != null) {
            for (int i : PlayerStat.getPlayerStats(player).getItems()) {
                if (i > 0) {
                    return CompatibilityManager.glow.addGlow(getItem());
                }
            }
        }
        return getItem();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(7, 1);
    }

}
