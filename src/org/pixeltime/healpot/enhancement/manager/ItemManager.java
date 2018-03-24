package org.pixeltime.healpot.enhancement.manager;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pixeltime.healpot.enhancement.events.blackspirit.Lore;
import org.pixeltime.healpot.enhancement.events.inventory.Backpack;
import org.pixeltime.healpot.enhancement.util.ItemBuilder;
import org.pixeltime.healpot.enhancement.util.ItemTypes;
import org.pixeltime.healpot.enhancement.util.NBTItem;
import org.pixeltime.healpot.enhancement.util.Util;

public class ItemManager {

    public static boolean isValid(ItemStack item, Material[] comparable) {
        for (int i = 0; i < comparable.length; i++) {
            if (comparable[i].equals(item.getType())) {
                return true;
            }
        }
        return false;
    }


    /**
     * Determines the enchantment type for the enhancing item.
     * 
     * @param player
     * @param item
     * @return
     */
    public static ItemTypes getItemEnchantmentType(ItemStack item) {
        if (isValid(item, Util.weapon)) {
            return ItemTypes.WEAPON;
        }
        if (isValid(item, Util.armor)) {
            return ItemTypes.ARMOR;
        }
        return ItemTypes.INVALID;
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
        return new ItemBuilder(Util.stoneTypes[stoneId]).setName(Util.toColor(
            SettingsManager.lang.getString("Item." + stoneId))).setLore(lore)
            .toItemStack();
    }


    public static ItemStack stoneVisualized(int stoneId, Player player) {
        List<String> lore = new ArrayList<String>();
        // TODO add lore.
        return new ItemBuilder(Util.stoneTypes[stoneId]).setName(Util.toColor(
            SettingsManager.lang.getString("Item." + stoneId))).setLore(lore)
            .toItemStack();
    }


    public static ItemStack levelUpdate(ItemStack item, int enchantLevel) {
        NBTItem nbti = new NBTItem(item);
        nbti.setInteger("ELevel", enchantLevel);
        return nbti.getItem();
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


    public static ItemStack forgeItem(ItemStack item, int enchantLevel) {
        return renameItem(applyEnchantments(levelUpdate(item, enchantLevel)));
    }


    public static ItemStack applyEnchantments(ItemStack item) {
        int enchantLevel = getItemEnchantLevel(item);
        int gradeLevel = getItemGradeLevel(item);
        ItemTypes type = getItemEnchantmentType(item);
        List<String> temp = SettingsManager.config.getStringList("enhance."
            + enchantLevel + ".enchantments." + type.toString());
        for (String s : temp) {
            String[] a = s.split(":");
            item.addUnsafeEnchantment(Enchantment.getByName(a[0]), Integer
                .parseInt(a[1]));
        }
        List<String> temp2 = null;
        switch (getItemEnchantmentType(item)) {
            case WEAPON:
                temp2 = SettingsManager.config.getStringList("weaponGrade."
                    + gradeLevel + ".enchantments");
            case ARMOR:
                temp2 = SettingsManager.config.getStringList("armorGrade."
                    + gradeLevel + ".enchantments");
            default:
                break;
        }

        if (temp2 != null) {
            for (String s : temp2) {
                String[] b = s.split(":");
                Enchantment ench = Enchantment.getByName(b[0]);
                item.addUnsafeEnchantment(ench, Integer.parseInt(b[1]) + item
                    .getEnchantmentLevel(ench));
            }
        }

        return item;
    }


    public static ItemStack renameItem(ItemStack item) {
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
                }
                else if (item.getType().toString().toLowerCase().contains(
                    "sword")) {
                    itemName = SettingsManager.config.getString("name.sword");
                }
                else if (item.getType().toString().toLowerCase().contains(
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
        Lore.addLore(item, ChatColor.translateAlternateColorCodes('&',
            SettingsManager.lang.getString("Lore." + SettingsManager.config
                .getString("lore.bound") + "Lore")), SettingsManager.config
                    .getBoolean("lore.bound"));
        return item;
    }

}
