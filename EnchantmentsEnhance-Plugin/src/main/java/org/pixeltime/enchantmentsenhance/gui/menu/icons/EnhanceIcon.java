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

import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.DataManager;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;
import com.lgou2w.ldk.bukkit.compatibility.XMaterial;

public class EnhanceIcon extends Clickable {
    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(XMaterial.YELLOW_WOOL.toBukkit()).setDyeColor(DyeColor.YELLOW).setName(SettingsManager.lang.getString("menu.gui.enhance")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.ifSuccess")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.ifFail")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.ifDowngrade")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.ifDestroy")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.skip"))
                .toItemStack();
    }

    public ItemStack getItem(ItemStack item) {
        int level = ItemManager.getItemEnchantLevel(item);
        ItemBuilder ib = new ItemBuilder(XMaterial.YELLOW_WOOL.toBukkit()).setDyeColor(DyeColor.YELLOW).setName(SettingsManager.lang.getString("menu.gui.enhance")).addLoreLine(SettingsManager.lang.getString(
                "menu.lore.ifSuccess"));
        if (DataManager.baseChance[level] != 100.0) {
            ib.addLoreLine(SettingsManager.lang.getString(
                    "menu.lore.ifFail"));
        }
        if (DataManager.downgradeChanceIfFail[level] > 0) {
            ib.addLoreLine(SettingsManager.lang.getString("menu.lore.ifDowngrade"));
        }
        if (DataManager.destroyChanceIfFail[level] > 0) {
            ib.addLoreLine(SettingsManager.lang.getString(
                    "menu.lore.ifDestroy")).toItemStack();
        }
        ib.addLoreLine(SettingsManager.lang.getString(
                "menu.lore.skip")).toItemStack();
        return ib.toItemStack();
    }

    public ItemStack getGlowingItem(ItemStack item) {
        return CompatibilityManager.glow.addGlow(getItem(item));
    }

    @Override
    public int getPosition() {
        return Util.getSlot(4, 5);
    }
}
