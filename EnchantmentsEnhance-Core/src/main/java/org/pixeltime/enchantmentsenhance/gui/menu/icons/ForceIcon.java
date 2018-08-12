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
import org.pixeltime.enchantmentsenhance.event.Enhance;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.DataManager;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;
import org.pixeltime.enchantmentsenhance.util.XMaterial;

public class ForceIcon extends Clickable {
    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(XMaterial.RED_WOOL.parseMaterial())
                .setDyeColor(DyeColor.RED)
                .setName(SettingsManager.lang.getString("menu.gui.force"))
                .addLoreLine(SettingsManager.lang.getString(
                        "menu.lore.force1")).toItemStack();
    }

    public ItemStack getItem(ItemStack item) {
        int enchantLevel = ItemManager.getItemEnchantLevel(item);
        int stoneId = Enhance.getStoneId(item, enchantLevel);
        int costToEnhance = DataManager.costToForceEnchant[enchantLevel + 1];
        return new ItemBuilder(XMaterial.RED_WOOL.parseMaterial())
                .setDyeColor(DyeColor.RED)
                .setName(SettingsManager.lang.getString("menu.gui.force"))
                .addLoreLine(SettingsManager.lang.getString("menu.lore.force1"))
                .addLoreLine(SettingsManager.lang.getString("menu.lore.force2")
                        .replaceAll("%COUNT%", Integer.toString(costToEnhance))
                        .replaceAll("%ITEM%", SettingsManager.lang.getString("item." + stoneId))).toItemStack();
    }

    public ItemStack getGlowingItem(ItemStack item) {
        return CompatibilityManager.glow.addGlow(getItem(item));
    }

    @Override
    public int getPosition() {
        return Util.getSlot(6, 5);
    }
}
