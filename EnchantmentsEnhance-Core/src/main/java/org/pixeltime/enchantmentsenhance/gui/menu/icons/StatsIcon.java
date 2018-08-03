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
import org.pixeltime.enchantmentsenhance.api.API;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;
import org.pixeltime.enchantmentsenhance.util.XMaterial;

public class StatsIcon extends Clickable {

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(XMaterial.WHITE_WOOL.parseMaterial()).setDyeColor(DyeColor.LIGHT_BLUE)
                .setName(SettingsManager.lang.getString("Menu.gui.stats"))
                .addLoreLine(SettingsManager.lang.getString("Menu.lore.stats1"))
                .addLoreLine(SettingsManager.lang.getString("Menu.lore.stats2"))
                .toItemStack();
    }

    public ItemStack getItem(String playerName) {
        if (playerName != null && MainMenu.itemOnEnhancingSlot.get(playerName) != null) {
            return CompatibilityManager.glow.addGlow(new ItemBuilder(XMaterial.WHITE_WOOL.parseMaterial()).setDyeColor(DyeColor.LIGHT_BLUE).setName(SettingsManager.lang.getString("Menu.gui.stats")).addLoreLine(SettingsManager.lang.getString("Enhance.currentFailstack")
                    + API.getFailstack(playerName)).addLoreLine(Enhance.getChance(MainMenu.itemOnEnhancingSlot.get(playerName), playerName)).addLoreLine(SettingsManager.lang.getString(
                    "Menu.lore.stats1")).addLoreLine(SettingsManager.lang.getString(
                    "Menu.lore.stats2")).toItemStack());
        }
        if (playerName != null && API.hasFailstack(playerName)) {
            return CompatibilityManager.glow.addGlow(new ItemBuilder(XMaterial.WHITE_WOOL.parseMaterial())
                    .setDyeColor(DyeColor.LIGHT_BLUE)
                    .setName(SettingsManager.lang.getString("Menu.gui.stats"))
                    .addLoreLine(SettingsManager.lang.getString("Enhance.currentFailstack")
                            + API.getFailstack(playerName))
                    .addLoreLine(SettingsManager.lang.getString("Menu.lore.stats1"))
                    .addLoreLine(SettingsManager.lang.getString("Menu.lore.stats2"))
                    .toItemStack());
        }
        return getItem();
    }

    @Override
    public int getPosition() {
        return Util.getSlot(5, 2);
    }
}
