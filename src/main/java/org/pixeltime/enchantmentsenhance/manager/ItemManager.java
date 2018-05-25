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
        return new ItemBuilder(MM.stoneTypes.get(stoneId)).setName(Util.toColor(
                SettingsManager.lang.getString("Item." + stoneId))).setLore(lore)
                .toItemStack();
    }


    public static ItemStack stoneVisualized(int stoneId, Player player) {
        List<String> lore = new ArrayList<String>();
        return new ItemBuilder(MM.stoneTypes.get(stoneId)).setName(Util.toColor(
                SettingsManager.lang.getString("Item." + stoneId))).setLore(lore)
                .toItemStack();
    }


    public static ItemStack gradeUpdate(ItemStack item, int gradeLevel) {
        NBTItem nbti = new NBTItem(item);
        nbti.setInteger("EGrade", gradeLevel);
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

    public static void soulbound(ItemStack item) {
        Lore.addLore(item, ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("Lore." + SettingsManager.config
                        .getString("lore.bound") + "Lore")), SettingsManager.config
                .getBoolean("lore.bound"));
    }

    public static void forgeItem(Player player, ItemStack item, int enchantLevel) {
        NBTItem nbti = new NBTItem(item);
        nbti.setInteger("ELevel", enchantLevel);
        ItemStack currItem = nbti.getItem();
        applyEnchantments(currItem);
        renameItem(currItem);
        soulbound(currItem);
        MenuHandler.updateItem(player, item, currItem);
    }

    public static void applyEnchantments(ItemStack item) {
        int enchantLevel = getItemEnchantLevel(item);
        int gradeLevel = getItemGradeLevel(item);
        ItemType type = getItemEnchantmentType(item);
        List<String> temp = SettingsManager.config.getStringList("enhance."
                + enchantLevel + ".enchantment." + type.toString());
        //Clear All the enchantment before applying new enchantment
        List<String> empty = new ArrayList<String>();
        item.getItemMeta().setLore(empty);
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
        }

        if (temp2 != null) {
            for (String s : temp2) {
                String[] b = s.split(":");
                Enchantment ench = Enchantment.getByName(b[0]);
                item.addUnsafeEnchantment(ench, Integer.parseInt(b[1]) + item
                        .getEnchantmentLevel(ench));
            }
        }
    }

    public static void applyEnchantmentToItem(ItemStack item, String ench, int level) {
        ItemMeta meta = item.getItemMeta();
        List<String> newlore = (meta.hasLore() ? meta.getLore() : new ArrayList<>());
        try {
            item.addUnsafeEnchantment(Enchantment.getByName(ench), level);
        } catch (IllegalArgumentException ex) {
            String enchantment = SettingsManager.lang.getString("enchantment." + ench.toLowerCase());
            if (enchantment != null) {
                newlore.add(ChatColor.translateAlternateColorCodes('&', enchantment + " " + Util.intToRoman(level)));
            }
            meta.setLore(newlore);
            item.setItemMeta(meta);
        }
    }


    public static void renameItem(ItemStack item) {
        int enchantLevel = ItemManager.getItemEnchantLevel(item);
        int gradeLevel = ItemManager.getItemGradeLevel(item);
        switch (getItemEnchantmentType(item)) {
            case WEAPON:
                String levelName = SettingsManager.config.getString("enhance."
                        + enchantLevel + ".name");
                String gradePrefix = SettingsManager.config.getString("grade."
                        + gradeLevel + ".name");
                String itemName = "";
                if (item.getType().toString().toLowerCase().contains("axe")) {
                    itemName = SettingsManager.config.getString("name.axe");
                } else if (item.getType().toString().toLowerCase().contains(
                        "sword")) {
                    itemName = SettingsManager.config.getString("name.sword");
                } else if (item.getType().toString().toLowerCase().contains(
                        "bow")) {
                    itemName = SettingsManager.config.getString("name.bow");
                }
                String name = "";
                if (levelName != null) {
                    name += levelName + " ";
                }
                if (gradePrefix != null) {
                    name += gradePrefix + " ";
                }
                if (itemName != null) {
                    name += itemName;
                }
                ItemMeta im = item.getItemMeta();
                im.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                        name));
                item.setItemMeta(im);
            case TOOL:

            case MASK:

            default:
        }
    }


    public static String getRenamedName(ItemStack item) {
        String name = "";
        int enchantLevel = ItemManager.getItemEnchantLevel(item) + 1;
        int gradeLevel = ItemManager.getItemGradeLevel(item);
        switch (getItemEnchantmentType(item)) {
            case WEAPON:
                String levelName = SettingsManager.config.getString("enhance."
                        + enchantLevel + ".name");
                String gradePrefix = SettingsManager.config.getString("grade."
                        + gradeLevel + ".name");
                String itemName = "";
                if (item.getType().toString().toLowerCase().contains("axe")) {
                    itemName = SettingsManager.config.getString("name.axe");
                } else if (item.getType().toString().toLowerCase().contains(
                        "sword")) {
                    itemName = SettingsManager.config.getString("name.sword");
                } else if (item.getType().toString().toLowerCase().contains(
                        "bow")) {
                    itemName = SettingsManager.config.getString("name.bow");
                }
                if (levelName != null) {
                    name += levelName + " ";
                }
                if (gradePrefix != null) {
                    name += gradePrefix + " ";
                }
                if (itemName != null) {
                    name += itemName;
                }

            case TOOL:

            case MASK:

            default:
        }
        return name;
    }


}
