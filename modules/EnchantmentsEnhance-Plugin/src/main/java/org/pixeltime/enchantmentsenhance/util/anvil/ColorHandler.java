package org.pixeltime.enchantmentsenhance.util.anvil;

import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.util.Util;

public class ColorHandler {
    public static ItemStack getTranslatedItem(final Player p, final AnvilInventory inv, final AnvilTask task) {
        final ItemStack outputItem = inv.getItem(2);
        if (outputItem != null && outputItem.hasItemMeta()) {
            return colorItemWithPermissions(outputItem, p);
        }
        return outputItem;
    }

    public static ItemStack colorItemWithPermissions(final ItemStack item, final Player p) {
        if (item.hasItemMeta()) {
            final ItemMeta itemMeta = item.getItemMeta();
            if (itemMeta.hasDisplayName()) {
                String coloredName = Util.toColor(itemMeta.getDisplayName());
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
            }
        }
        return item;
    }
}
