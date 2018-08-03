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

package org.pixeltime.enchantmentsenhance.util.anvil;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;

public class ColorHandler {
    public static ItemStack getTranslatedItem(final Player p, final AnvilInventory inv, final AnvilTask task) {
        final ItemStack outputItem = inv.getItem(2);
        if (outputItem != null && outputItem.hasItemMeta()) {
            return colorItemWithPermissions(outputItem, p);
        }
        return outputItem;
    }

    public static ItemStack colorItemWithPermissions(final ItemStack item, final Player p) {
        final ItemMeta itemMeta = item.getItemMeta();
        String coloredName = ChatColor.translateAlternateColorCodes('&', itemMeta.getDisplayName());
        for (int i = 0; i < coloredName.length(); ++i) {
            if (coloredName.charAt(i) == '§') {
                final char c = coloredName.charAt(i + 1);
                if (ItemManager.getItemEnchantLevel(item) < 1) {
                    coloredName = coloredName.replaceAll("§" + c, "&" + c);
                }
            }
        }
        itemMeta.setDisplayName(coloredName);
        item.setItemMeta(itemMeta);
        return item;
    }
}
