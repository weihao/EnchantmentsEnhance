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

package org.pixeltime.enchantmentsenhance.manager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.enums.ItemType;
import org.pixeltime.enchantmentsenhance.event.Lore;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;
import org.pixeltime.enchantmentsenhance.util.XMaterial;
import org.pixeltime.enchantmentsenhance.util.datastructure.DoublyLinkedList;
import org.pixeltime.enchantmentsenhance.util.datastructure.interfaces.Iterator;
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
        if (isValid(item, MaterialManager.weapon)) {
            return ItemType.WEAPON;
        } else if (isValid(item, MaterialManager.armor)) {
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

    public static ItemStack setHistory(ItemStack item, String history) {
        NBTItem nbti = new NBTItem(item);
        nbti.setString("EHistory", history);
        return nbti.getItem();
    }

    public static String getHistory(ItemStack item) {
        NBTItem nbti = new NBTItem(item);
        return nbti.getString("EHistory");
    }

    public static ItemStack setGive(ItemStack item, String give) {
        NBTItem nbti = new NBTItem(item);
        nbti.setString("EGive", give);
        return nbti.getItem();
    }

    public static String getGive(ItemStack item) {
        NBTItem nbti = new NBTItem(item);
        return nbti.getString("EGive");
    }


    public static void soulbound(ItemStack item) {
        Lore.removeLore(item);
        Lore.addLore(item,
                SettingsManager.lang.getString("Lore." + SettingsManager.config
                        .getString("lore.bound") + "Lore"), !SettingsManager.config
                        .getString("lore.bound").contains("un"));
    }

    public static ItemStack forgeItem(Player player, ItemStack item, int enchantLevel, boolean addition) {
        ItemStack currItem = setLevel(item, enchantLevel);
        // Getting Unique Name.
        List<String> oldLore = KotlinManager.stripLore(item);

        if (enchantLevel == 1 && getItemName(currItem) == null && SettingsManager.config.getBoolean("enableRename")) {
            currItem = setName(currItem, currItem.getItemMeta().getDisplayName());
        }

        // Unique ID applied.
        currItem = applyEnchantments(currItem, addition);
        if (SettingsManager.config.getBoolean("enableRename")) {
            renameItem(currItem);
        }
        addlore(currItem, oldLore);
        if (!SettingsManager.config.getString("lore.bound").equalsIgnoreCase("disabled")) {
            soulbound(currItem);
        }
        player.getInventory().removeItem(item);
        MainMenu.itemOnEnhancingSlot.put(player.getName(), currItem);
        player.getInventory().addItem(currItem);
        return currItem;
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

    public static ItemStack applyEnchantments(ItemStack item, boolean addition) {
        int enchantLevel = getItemEnchantLevel(item);

        if (enchantLevel > 0) {
            DoublyLinkedList<String> node = new DoublyLinkedList<>();
            String history = ItemManager.getHistory(item);
            if (!history.isEmpty()) {
                String[] temp = history
                        .replace("{", "")
                        .replace("}", "")
                        .split("; ");
                for (int i = 0; i < temp.length; i++) {
                    node.add(temp[i]);
                }
            }
            if (addition) {
                ItemType type = getItemEnchantmentType(item);
                List<String> temp = SettingsManager.config.getStringList("enhance."
                        + enchantLevel + ".enchantments." + type.toString());

                //Adding New enchantment.
                for (String s : temp) {
                    String[] a = s.split(":");
                    applyEnchantmentToItem(item, a[0], Integer.parseInt(a[1]));
                }
                node.add(temp.toString());
                return setHistory(item, node.toString());
            } else {
                Iterator<String> it = node.iterator();
                String downgrade = null;
                while (it.hasNext()) {
                    downgrade = it.next();
                }
                if (downgrade == null) {
                    ItemType type = getItemEnchantmentType(item);
                    List<String> temp = SettingsManager.config.getStringList("enhance."
                            + (enchantLevel + 1) + ".enchantments." + type.toString());
                    //Adding New enchantment.
                    for (String s : temp) {
                        String[] a = s.split(":");
                        applyEnchantmentToItem(item, a[0], -Integer.parseInt(a[1]));
                    }
                } else {
                    for (String s : downgrade
                            .replace("[", "")
                            .replace("]", "")
                            .split(", ")) {
                        String[] a = s.split(":");
                        applyEnchantmentToItem(item, a[0], -Integer.parseInt(a[1]));
                    }
                }
            }
        }
        return item;
    }

    public static boolean applyEnchantmentToItem(ItemStack item, String ench, int level) {
        ItemMeta meta = item.getItemMeta();
        List<String> newlore = (meta.hasLore() ? meta.getLore() : new ArrayList<>());
        Enchantment vanilla = Enchantment.getByName(ench.toUpperCase());

        if (vanilla != null) {
            int lvl = (item.getEnchantmentLevel(vanilla)) + level;
            if (lvl > 0) {
                item.addUnsafeEnchantment(Enchantment.getByName(ench.toUpperCase()), lvl);
            } else {
                item.removeEnchantment(vanilla);
            }
            return true;
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
                int max = 1;
                try {
                    max = Main.getApi().getEnchantmentMaxLevel(ench);
                } catch (NullPointerException ex) {
                }
                int finalLevel = ((level + keptLevel) > max) ? max : level + keptLevel;
                if (finalLevel > 0) {
                    newlore.add(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', "&7" + enchantment + " " + Util.intToRoman(finalLevel)));
                    meta.setLore(newlore);
                    item.setItemMeta(meta);
                    if (item.getEnchantments().isEmpty()) {
                        CompatibilityManager.glow
                                .addGlow(item);
                    }
                    return true;
                }
            }
            return false;
        }
    }


    public static void renameItem(ItemStack item) {
        int enchantLevel = ItemManager.getItemEnchantLevel(item);
        String name = getFriendlyName(item);

        if (SettingsManager.config.getBoolean("renamingIncludes.prefix")) {
            String prefix = SettingsManager.config.getString("enhance."
                    + enchantLevel + ".prefix");
            if (prefix != null && !prefix.equals("")) {
                name = prefix + " " + name;
            }
        }
        if (SettingsManager.config.getBoolean("renamingIncludes.suffix")) {
            String suffix = SettingsManager.config.getString("enhance."
                    + enchantLevel + ".suffix");
            if (suffix != null && !suffix.equals("")) {
                name += " " + suffix;
            }
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

    public static ItemStack itemMaterialize(int stoneId, int amount) {
        return CompatibilityManager.glow
                .addGlow(setGive(new ItemBuilder(MaterialManager.stoneTypes.get(stoneId))
                                .setName(SettingsManager.lang.getString("Item." + stoneId) + " Bundle: " + amount)
                                .addLoreLine(SettingsManager.lang.getString("Materialize.info1"))
                                .addLoreLine(SettingsManager.lang.getString("Materialize.info2")
                                        .replace("%amount%", Integer.toString(amount))
                                        .replace("%item%", SettingsManager.lang.getString("Item." + stoneId)))
                                .toItemStack(),
                        stoneId + ":" + amount));
    }

    public static ItemStack adviceMaterialize(int level) {
        return CompatibilityManager.glow
                .addGlow(setGive(new ItemBuilder(XMaterial.BOOK.parseItem())
                                .setName(SettingsManager.lang.getString("Item.valks") + "+" + level)
                                .addLoreLine(SettingsManager.lang.getString("Materialize.info1"))
                                .addLoreLine(SettingsManager.lang.getString("Materialize.advice1")
                                        .replace("%level%", Integer.toString(level)))
                                .toItemStack(),
                        "-1:" + level));
    }
}
