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
import org.pixeltime.enchantmentsenhance.event.inventory.Backpack;
import org.pixeltime.enchantmentsenhance.listener.MenuHandler;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;
import org.pixeltime.enchantmentsenhance.util.nbt.NBTItem;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static boolean isValid(ItemStack item, List<Material> comparable) {
        return comparable.contains(item.getType());
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
        }
        if (isValid(item, MM.armor)) {
            return ItemType.ARMOR;
        }
        return ItemType.INVALID;
    }


    /**
     * Displays enhancement stone as a itemstack on GUI
     *
     * @param stoneId
     * @param player
     * @return
     */
    public static ItemStack stoneVisualized(
            int stoneId,
            Player player,
            Boolean count) {
        List<String> lore = new ArrayList<String>();
        if (player != null) {
            lore.add(Util.toColor(Backpack.getOneStoneCountAsString(player,
                    stoneId)));
        }
        ItemStack item = new ItemBuilder(MM.stoneTypes.get(stoneId)).setName(Util.toColor(SettingsManager.lang.getString("Item." + stoneId))).setLore(lore).toItemStack();
        int stack = (Backpack.getOneStoneCountAsInt(player, stoneId) > 64 ? 64 : Backpack.getOneStoneCountAsInt(player, stoneId));
        item.setAmount(stack);
        return item;
    }


    public static ItemStack stoneVisualized(int stoneId) {
        List<String> lore = new ArrayList<String>();
        return new ItemBuilder(MM.stoneTypes.get(stoneId)).setName(Util.toColor(
                SettingsManager.lang.getString("Item." + stoneId))).setLore(lore)
                .toItemStack();
    }


    public static ItemStack setGrade(ItemStack item, int gradeLevel) {
        NBTItem nbti = new NBTItem(item);
        nbti.setInteger("EGrade", gradeLevel);

        return nbti.getItem();
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


    public static int getItemGradeLevel(ItemStack item) {
        NBTItem nbti = new NBTItem(item);
        return nbti.getInteger("EGrade");
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
        Lore.addLore(item, Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("Lore." + SettingsManager.config
                        .getString("lore.bound") + "Lore")), !SettingsManager.config
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
        soulbound(currItem);
        addlore(currItem, oldLore);
        MenuHandler.updateItem(player, item, currItem);
    }

    private static void addlore(ItemStack currItem, List<String> old) {
        if (old != null && old.size() > 0) {
            ItemMeta im = currItem.getItemMeta();
            List<String> lore = im.getLore();
            List<String> addlore = (List<String>) SettingsManager.config.getList("enhance." + getItemEnchantLevel(currItem) + ".lore." + getItemEnchantmentType(currItem).toString());
            for (String s : addlore) {
                lore.add(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', s));
            }
            lore.addAll(old);
            im.setLore(lore);
            currItem.setItemMeta(im);
        }
    }

    public static void applyEnchantments(ItemStack item) {
        int enchantLevel = getItemEnchantLevel(item);
        int gradeLevel = getItemGradeLevel(item);
        ItemType type = getItemEnchantmentType(item);
        List<String> temp = SettingsManager.config.getStringList("enhance."
                + enchantLevel + ".enchantments." + type.toString());
        //Clear All the enchantment before applying new enchantment
        List<String> empty = new ArrayList<String>();
        ItemMeta meta = item.getItemMeta();
        meta.setLore(empty);
        item.setItemMeta(meta);

        for (Enchantment ench : item.getEnchantments().keySet()) {
            item.removeEnchantment(ench);
        }

        //Adding New enchantment.
        for (String s : temp) {
            String[] a = s.split(":");
            applyEnchantmentToItem(item, a[0], Integer.parseInt(a[1]));
        }
        List<String> temp2 = null;
        switch (getItemEnchantmentType(item)) {
            case WEAPON:
                temp2 = SettingsManager.config.getStringList("weaponGrade."
                        + gradeLevel + ".enchantment");
            case ARMOR:
                temp2 = SettingsManager.config.getStringList("armorGrade."
                        + gradeLevel + ".enchantment");
            case MASK:
                break;
            case TOOL:
                break;
            case INVALID:
                break;
        }

        if (temp2 != null) {
            for (String s : temp2) {
                String[] b = s.split(":");
                applyEnchantmentToItem(item, b[0], Integer.parseInt(b[1]));
            }
        }
    }

    public static void applyEnchantmentToItem(ItemStack item, String ench, int level) {
        ItemMeta meta = item.getItemMeta();
        List<String> newlore = (meta.hasLore() ? meta.getLore() : new ArrayList<>());
        try {
            item.addUnsafeEnchantment(Enchantment.getByName(ench.toUpperCase()), level);
        } catch (IllegalArgumentException ex) {
            String enchantment = SettingsManager.lang.getString("enchantments." + ench.toLowerCase());
            if (enchantment != null) {
                // Unique ID
                newlore.add(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', enchantment + " " + Util.intToRoman(level)));
            }
            meta.setLore(newlore);
            item.setItemMeta(meta);
        }
    }


    public static void renameItem(ItemStack item) {
        int enchantLevel = ItemManager.getItemEnchantLevel(item);
        int gradeLevel = ItemManager.getItemGradeLevel(item);
        String prefix = SettingsManager.config.getString("enhance."
                + enchantLevel + ".prefix");
        String suffix = SettingsManager.config.getString("enhance."
                + enchantLevel + ".suffix");
        String gradePrefix = SettingsManager.config.getString("grade."
                + gradeLevel + ".name");
        String name = getFriendlyName(item);
        if (prefix != null && !prefix.equals("")) {
            name = prefix + " " + name;
        }
        if (gradePrefix != null && !gradePrefix.equals("")) {
            name += " " + gradePrefix;
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
