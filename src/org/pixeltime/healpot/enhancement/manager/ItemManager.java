package org.pixeltime.healpot.enhancement.manager;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.junit.experimental.theories.Theories;
import org.pixeltime.healpot.enhancement.Main;
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
            lore.add(Util.toColor(Main.inventoryText.getOneStoneCountAsString(
                player, stoneId)));
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
        ItemTypes type = getItemEnchantmentType(item);
        List<String> temp = SettingsManager.config.getStringList("enhance."
            + enchantLevel + ".enchantments." + type.toString());
        for (String s : temp) {
            String[] a = s.split(":");
            item.addUnsafeEnchantment(Enchantment.getByName(a[0]), Integer
                .parseInt(a[1]));
        }
        List<String> temp2 = SettingsManager.config.getStringList("grade."
            + enchantLevel + ".enchantments." + type.toString());
        {
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
        String s = "";
        int i = getItemGradeLevel(item);

        item.getItemMeta().setDisplayName(s);
        return item;
    }

}
