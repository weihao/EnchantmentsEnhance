/*
 *     Copyright (C) 2017-Present HealPotion
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

package org.pixeltime.enchantmentsenhance.manager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pixeltime.enchantmentsenhance.enums.ItemType;
import org.pixeltime.enchantmentsenhance.event.blackspirit.Lore;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.util.Util;
import org.pixeltime.enchantmentsenhance.util.nbt.NBTItem;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static boolean isValid(ItemStack item, List<Material> comparable) {
        return item != null && comparable.contains(item.getType());
    }


    /**
     * Determines the enchantment type for the enhancing item.
     *
     * @param item
     * @return
     */
    public static ItemType getItemEnchantmentType(ItemStack item) {
        if (isValid(item, MM.weapon)) {
            return ItemType.WEAPON;
        } else if (isValid(item, MM.armor)) {
            return ItemType.ARMOR;
        } else {
            return ItemType.INVALID;
        }
    }

    public static ItemStack setLevel(ItemStack item, int enhanceLevel) {
        NBTItem nbti = new NBTItem(item);
        nbti.setInteger("ELevel", enhanceLevel);
        return nbti.getItem();
    }

    public static ItemStack setName(ItemStack item, String name) {
        NBTItem nbti = new NBTItem(item);
        nbti.setString("EName", name);
        return nbti.getItem();
    }

    public static int getItemEnchantLevel(ItemStack item) {
        NBTItem nbti = new NBTItem(item);
        return nbti.getInteger("ELevel");
    }


    public static String getItemLore(ItemStack item) {
        NBTItem nbti = new NBTItem(item);
        return nbti.getString("ELore");
    }

    public static String getItemName(ItemStack item) {
        NBTItem nbti = new NBTItem(item);
        return nbti.getString("EName");
    }


    public static void soulbound(ItemStack item) {
        Lore.removeLore(item);
        Lore.addLore(item,
                SettingsManager.lang.getString("Lore." + SettingsManager.config
                        .getString("lore.bound") + "Lore"), !SettingsManager.config
                        .getString("lore.bound").contains("un"));
    }

    public static void forgeItem(Player player, ItemStack item, int enchantLevel) {
        ItemStack currItem = setLevel(item, enchantLevel);
        // Getting Unique Name.
        List<String> oldLore = KM.stripLore(item);

        if (enchantLevel == 1) {
            currItem = setName(currItem, currItem.getItemMeta().getDisplayName());
        }

        // Unique ID applied.
        applyEnchantments(currItem);
        renameItem(currItem);
        addlore(currItem, oldLore);
        soulbound(currItem);
        player.getInventory().removeItem(item);
        MainMenu.itemOnEnhancingSlot.put(player.getName(), currItem);
        player.getInventory().addItem(currItem);
    }

    private static void addlore(ItemStack currItem, List<String> old) {
        ItemMeta im = currItem.getItemMeta();
        List<String> lore = (old != null && old.size() > 0) ? old : new ArrayList<>();
        List<String> newlore = im.hasLore() ? im.getLore() : new ArrayList<>();
        newlore.removeIf(e -> (!e.startsWith(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', "&7"))));
        for (String s : (List<String>) SettingsManager.config.getList("enhance." + getItemEnchantLevel(currItem) + ".lore." + getItemEnchantmentType(currItem).toString())) {
            lore.add(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', s));
        }
        newlore.addAll(lore);
        im.setLore(newlore);
        currItem.setItemMeta(im);
    }

    public static void applyEnchantments(ItemStack item) {
        int enchantLevel = getItemEnchantLevel(item);

        if (enchantLevel > 0) {
            ItemType type = getItemEnchantmentType(item);
            List<String> temp = SettingsManager.config.getStringList("enhance."
                    + enchantLevel + ".enchantments." + type.toString());

            //Adding New enchantment.
            for (String s : temp) {
                String[] a = s.split(":");
                applyEnchantmentToItem(item, a[0], Integer.parseInt(a[1]));
            }
        }
    }

    public static void applyEnchantmentToItem(ItemStack item, String ench, int level) {
        ItemMeta meta = item.getItemMeta();
        List<String> newlore = (meta.hasLore() ? meta.getLore() : new ArrayList<>());
        Enchantment vanilla = Enchantment.getByName(ench.toUpperCase());
        if (vanilla != null) {
            item.addUnsafeEnchantment(Enchantment.getByName(ench.toUpperCase()), (item.getEnchantmentLevel(vanilla)) + level);
        } else {
            String enchantment = SettingsManager.lang.getString("enchantments." + ench.toLowerCase());
            int keptLevel = 0;
            if (enchantment != null) {
                for (int i = 0; i < newlore.size(); i++) {
                    String curr = ChatColor.stripColor(newlore.get(i));
                    String currEnch = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', enchantment));
                    if (curr.contains(currEnch)) {
                        keptLevel += Util.romanToInt(curr.split(" ")[1]);
                        newlore.remove(i);
                        i--;
                    }
                }
                // Unique ID
                newlore.add(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', "&7" + enchantment + " " + Util.intToRoman(level + keptLevel)));
                meta.setLore(newlore);
                item.setItemMeta(meta);
                if (item.getEnchantments().isEmpty()) {
                    CompatibilityManager.glow
                            .addGlow(item);
                }
            }
        }
    }


    public static void renameItem(ItemStack item) {
        int enchantLevel = ItemManager.getItemEnchantLevel(item);
        String prefix = SettingsManager.config.getString("enhance."
                + enchantLevel + ".prefix");
        String suffix = SettingsManager.config.getString("enhance."
                + enchantLevel + ".suffix");
        String name = getFriendlyName(item);
        if (prefix != null && !prefix.equals("")) {
            name = prefix + " " + name;
        }
        if (suffix != null && !suffix.equals("")) {
            name += " " + suffix;
        }
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                name));
        item.setItemMeta(im);
    }


    /**
     * This will return a formatted name of a item.
     *
     * @param item
     * @return
     */
    public static String getFriendlyName(ItemStack item) {
        return (getItemName(item).equals("") ? Util.format(item.getType().name()) : getItemName(item));
    }
}
