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

import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import org.bukkit.DyeColor;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.event.Enhance;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.DataManager;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ForceIcon extends Clickable {
    @Override
    public ItemStack getItem(String playerName) {
        return new ItemBuilder(XMaterial.RED_WOOL.toBukkit())
                .setDyeColor(DyeColor.RED)
                .setName(SettingsManager.lang.getString("menu.gui.force"))
                .addLoreLine(SettingsManager.lang.getString(
                        "menu.lore.force1")).toItemStack();
    }

    public ItemStack getItem(ItemStack item, Clickable clicked) {
        int enchantLevel;
        if (clicked.equals(MainMenu.gear)) {
            enchantLevel = ItemManager.getItemEnchantLevel(item);
        } else if (clicked.equals(MainMenu.tool)) {
            enchantLevel = ItemManager.getToolEnchantLevel(item);
        } else {
            return null;
        }
        int stoneId = Enhance.getStoneId(item, enchantLevel, clicked);
        int costToEnhance = DataManager.costToForceEnchant[enchantLevel + 1];
        return new ItemBuilder(XMaterial.RED_WOOL.toBukkit())
                .setDyeColor(DyeColor.RED)
                .setName(SettingsManager.lang.getString("menu.gui.force"))
                .addLoreLine(SettingsManager.lang.getString("menu.lore.force1"))
                .addLoreLine(SettingsManager.lang.getString("menu.lore.force2")
                        .replaceAll("%COUNT%", Integer.toString(costToEnhance))
                        .replaceAll("%ITEM%", SettingsManager.lang.getString("item." + stoneId))).toItemStack();
    }

    public ItemStack getGlowingItem(ItemStack item, Clickable clicked) {
        return CompatibilityManager.glow.addGlow(getItem(item, clicked));
    }

    @Override
    public int getPosition() {
        return Util.getSlot(6, 5);
    }
}
