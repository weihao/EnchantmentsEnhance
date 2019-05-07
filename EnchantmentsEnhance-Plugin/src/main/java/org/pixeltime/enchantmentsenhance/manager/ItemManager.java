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

package org.pixeltime.enchantmentsenhance.manager;

import com.lgou2w.ldk.bukkit.compatibility.XMaterial;
import com.lgou2w.ldk.bukkit.item.ItemFactory;
import com.lgou2w.ldk.nbt.NBTTagCompound;
import kotlin.Unit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.enums.ItemType;
import org.pixeltime.enchantmentsenhance.event.Lore;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.util.ItemBuilder;
import org.pixeltime.enchantmentsenhance.util.Util;
import org.pixeltime.enchantmentsenhance.util.datastructure.DoublyLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
        } else if (isValid(item, Arrays.asList(Material.BOW))) {
            return ItemType.BOW;
        } else {
            return ItemType.INVALID;
        }
    }

    public static ItemType getToolItemEnchantmentType(ItemStack item) {
        if (isValid(item, MaterialManager.axe)) {
            return ItemType.AXE;
        } else if (isValid(item, MaterialManager.pickaxe)) {
            return ItemType.PICKAXE;
        } else if (isValid(item, MaterialManager.hoe)) {
            return ItemType.HOE;
        } else if (isValid(item, MaterialManager.shovel)) {
            return ItemType.SHOVEL;
        } else if (isValid(item, MaterialManager.knife)) {
            return ItemType.KNIFE;
        } else if (isValid(item, MaterialManager.rod)) {
            return ItemType.ROD;
        } else {
            return ItemType.INVALID;
        }
    }

    public static ItemStack setLevel(ItemStack item, int enhanceLevel) {
        return ItemFactory.modifyTag(item, tag -> {
            tag.putInt("ELevel", enhanceLevel);
            return Unit.INSTANCE;
        });
    }

    public static ItemStack setName(ItemStack item, String name) {
        return ItemFactory.modifyTag(item, tag -> {
            tag.putString("EName", name);
            return Unit.INSTANCE;
        });
    }

    public static int getItemEnchantLevel(ItemStack item) {
        NBTTagCompound tag = ItemFactory.readTagSafe(item);
        Integer level = tag.getIntOrNull("ELevel");
        return level != null ? level : 0;
    }

    public static int getToolEnchantLevel(ItemStack item) {
        NBTTagCompound tag = ItemFactory.readTagSafe(item);
        Integer level = tag.getIntOrNull("ETool");
        return level != null ? level : 0;
    }

    public static ItemStack setToolEnchantLevel(ItemStack item, int enhanceLevel) {
        return ItemFactory.modifyTag(item, tag -> {
            tag.putInt("ETool", enhanceLevel);
            return Unit.INSTANCE;
        });
    }

    public static String getItemLore(ItemStack item) {
        NBTTagCompound tag = ItemFactory.readTagSafe(item);
        String lore = tag.getStringOrNull("ELore");
        return lore != null ? lore : "";
    }

    public static String getItemName(ItemStack item) {
        NBTTagCompound tag = ItemFactory.readTagSafe(item);
        String name = tag.getStringOrNull("EName");
        return name != null ? name : "";
    }

    public static ItemStack setHistory(ItemStack item, String history) {
        return ItemFactory.modifyTag(item, tag -> {
            tag.putString("EHistory", history);
            return Unit.INSTANCE;
        });
    }

    public static String getHistory(ItemStack item) {
        NBTTagCompound tag = ItemFactory.readTagSafe(item);
        String history = tag.getStringOrNull("EHistory");
        return history != null ? history : "";
    }

    public static ItemStack setGive(ItemStack item, String give) {
        return ItemFactory.modifyTag(item, tag -> {
            tag.putString("EGive", give);
            return Unit.INSTANCE;
        });
    }

    public static String getGive(ItemStack item) {
        NBTTagCompound tag = ItemFactory.readTagSafe(item);
        String give = tag.getStringOrNull("EGive");
        return give != null ? give : "";
    }


    public static void soulbound(ItemStack item) {
        Lore.removeLore(item);
        Lore.addLore(item,
                SettingsManager.lang.getString("lore." + SettingsManager.config
                        .getString("lore.bound") + "Lore"), !SettingsManager.config
                        .getString("lore.bound").contains("un"));
    }

    public static ItemStack forgeItem(Player player, ItemStack item, int enchantLevel, boolean addition, Clickable clicked) {
        ItemStack currItem;
        if (clicked.equals(MainMenu.gear)) {
            currItem = setLevel(item, enchantLevel);
        } else if (clicked.equals(MainMenu.tool)) {
            currItem = setToolEnchantLevel(item, enchantLevel);
        } else {
            return null;
        }

        // Getting Unique Name.
        List<String> oldLore = KotlinManager.stripLore(item);

        if (enchantLevel == 1 && getItemName(currItem) == null && SettingsManager.config.getBoolean("enableRename")) {
            currItem = setName(currItem, currItem.getItemMeta().getDisplayName());
        }

        // Unique ID applied.
        currItem = applyEnchantments(currItem, addition, clicked);

        if (SettingsManager.config.getBoolean("enableRename")) {
            renameItem(currItem, clicked);
        }

        addlore(currItem, oldLore, clicked, player.getDisplayName());
        if (!SettingsManager.config.getString("lore.bound").equalsIgnoreCase("disabled")) {
            soulbound(currItem);
        }
        ItemMeta itemMeta = currItem.getItemMeta();
        itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });

        currItem.setItemMeta(itemMeta);

        return currItem;
    }

    public static ItemStack forgeItemWithReplacement(Player player, ItemStack item, int enchantLevel, boolean addition, Clickable clicked) {
        ItemStack currItem = forgeItem(player, item, enchantLevel, addition, clicked);

        player.getInventory().removeItem(item);
        MainMenu.itemOnEnhancingSlot.put(player.getName(), currItem);
        player.getInventory().addItem(currItem);
        return currItem;
    }

    private static void addlore(ItemStack currItem, List<String> old, Clickable clicked, String playerName) {
        ItemMeta im = currItem.getItemMeta();
        List<String> lore = (old != null && old.size() > 0) ? old : new ArrayList<>();
        List<String> newlore = im.hasLore() ? im.getLore() : new ArrayList<>();
        newlore.removeIf(e -> (!e.startsWith(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', "&7"))));
        List<String> applyingLores = null;
        if (clicked.equals(MainMenu.gear)) {
            applyingLores = (List<String>) SettingsManager.config.getList("enhance." + getItemEnchantLevel(currItem) + ".lore");
        } else if (clicked.equals(MainMenu.tool)) {
            applyingLores = (List<String>) SettingsManager.config.getList("enhance." + getToolEnchantLevel(currItem) + ".lore");
        }
        for (String s : applyingLores) {
            lore.add(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', s)
                    .replaceAll("%player%", playerName)
                    .replaceAll("%date%", Util.getCurrentDate()));
        }
        newlore.addAll(lore);
        im.setLore(newlore);
        currItem.setItemMeta(im);
    }

    public static ItemStack applyEnchantments(ItemStack item, boolean addition, Clickable clicked) {
        int enchantLevel;
        if (clicked.equals(MainMenu.gear)) {
            enchantLevel = getItemEnchantLevel(item);
        } else if (clicked.equals(MainMenu.tool)) {
            enchantLevel = getToolEnchantLevel(item);
        } else {
            return null;
        }
        if (enchantLevel > 0) {
            DoublyLinkedList<List<String>> node = new DoublyLinkedList<>();
            String history = ItemManager.getHistory(item);
            if (!history.isEmpty()) {
                String[] temp = history
                        .replace("{", "")
                        .replace("}", "")
                        .split("; ");
                for (int i = 0; i < temp.length; i++) {
                    // Nodes
                    node.add(Arrays.asList(temp[i]
                            .replace("[", "")
                            .replace("]", "")
                            .split(", ")));
                }
            }
            if (addition) {
                ItemType type = null;
                if (clicked.equals(MainMenu.gear)) {
                    type = getItemEnchantmentType(item);
                } else if (clicked.equals(MainMenu.tool)) {
                    type = getToolItemEnchantmentType(item);
                }
                List<String> temp = SettingsManager.config.getStringList("enhance."
                        + enchantLevel + ".enchantments." + type.toString());
                ArrayList<String> newNode = new ArrayList<>();
                //Adding New enchantment.
                for (String str : temp) {
                    String s = str;
                    boolean condition = false;
                    // Conditional Checks.
                    if (s.contains("!")) {
                        String processing = s.split("!")[0];
                        String conditionalEnch;
                        if (processing.contains("^")) {
                            conditionalEnch = processing.split("\\^")[1];
                            condition = (!hasEnchantment(item, conditionalEnch));
                        } else {
                            conditionalEnch = processing;
                            condition = hasEnchantment(item, conditionalEnch);
                        }
                        s = s.split("!")[1];
                    } else {
                        condition = true;
                    }

                    // Chance checks.
                    double prob;
                    if (s.contains("?")) {
                        prob = Double.parseDouble(s.split("\\?")[0]);
                        s = s.split("\\?")[1];
                    } else {
                        prob = 100.0;
                    }

                    // Get enchantment name.
                    String ench = s.split(":")[0];
                    s = s.split(":")[1];


                    // Get enchantment level.
                    int level;
                    // Random level.
                    if (s.contains("-")) {
                        String[] range = s.split("-");
                        int upper = Integer.parseInt(range[1]);
                        int lower = Integer.parseInt(range[0]);
                        level = (int) (Math.random() * (upper - lower)) + lower;
                    } else {
                        level = Integer.parseInt(s);
                    }
                    // Random enchantment.
                    Random random = new Random();
                    if (random.nextDouble() * 100.0 <= prob) {
                        // If all the conditions are met.
                        if (condition) {
                            applyEnchantmentToItem(item, ench, level);
                            newNode.add(ench + ":" + level);
                        }
                    }
                }
                node.add(newNode);
                return setHistory(item, node.toString());
            } else {
                Iterator<List<String>> it = node.iterator();
                List<String> downgrade = null;
                while (it.hasNext()) {
                    downgrade = it.next();
                }
                for (String s : downgrade) {
                    String[] a = s.replace("[", "").replace("]", "").split(":");
                    try {
                        applyEnchantmentToItem(item, a[0], -Integer.parseInt(a[1]));
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        // Empty linked list
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
                String currEnch = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', enchantment));
                for (int i = 0; i < newlore.size(); i++) {
                    String[] curr = ChatColor.stripColor(newlore.get(i)).split(
                            " ");
                    if (curr.length == 2 && curr[0].equals(currEnch)) {
                        // Adds original level.
                        // TODO
//                        keptLevel += Util.romanToInt(curr[1]);
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
                } else {
                    meta.setLore(newlore);
                    item.setItemMeta(meta);
                    if (item.getEnchantments().isEmpty()) {
                        CompatibilityManager.glow
                                .addGlow(item);
                    }
                }
            }
            return false;
        }
    }


    public static void renameItem(ItemStack item, Clickable clicked) {
        int enchantLevel;
        if (clicked.equals(MainMenu.gear)) {
            enchantLevel = ItemManager.getItemEnchantLevel(item);
        } else if (clicked.equals(MainMenu.tool)) {
            enchantLevel = ItemManager.getToolEnchantLevel(item);
        } else {
            return;
        }
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
                                .setName(SettingsManager.lang.getString("item." + stoneId) + " Bundle: " + amount)
                                .addLoreLine(SettingsManager.lang.getString("materialize.info1"))
                                .addLoreLine(SettingsManager.lang.getString("materialize.info2")
                                        .replace("%amount%", Integer.toString(amount))
                                        .replace("%item%", SettingsManager.lang.getString("item." + stoneId)))
                                .toItemStack(),
                        stoneId + ":" + amount));
    }

    public static ItemStack adviceMaterialize(int level) {
        return CompatibilityManager.glow
                .addGlow(setGive(new ItemBuilder(XMaterial.BOOK.toBukkit())
                                .setName(SettingsManager.lang.getString("item.valks") + "+" + level)
                                .addLoreLine(SettingsManager.lang.getString("materialize.info1"))
                                .addLoreLine(SettingsManager.lang.getString("materialize.advice1")
                                        .replace("%level%", Integer.toString(level)))
                                .toItemStack(),
                        "-1:" + level));
    }

    public static boolean hasEnchantment(ItemStack item, String ench) {
        Enchantment vanilla = Enchantment.getByName(ench.toUpperCase());
        if (vanilla != null) {
            int lvl = (item.getEnchantmentLevel(vanilla));
            return lvl > 0;
        } else {
            String enchantment = SettingsManager.lang.getString("enchantments." + ench.toLowerCase());
            if (enchantment != null) {
                String currEnch = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', enchantment));
                List<String> lores = item.getItemMeta().getLore();
                for (int i = 0; i < lores.size(); i++) {
                    String[] curr = ChatColor.stripColor(lores.get(i)).split(
                            " ");
                    if (curr.length > 0 && curr[0].equals(currEnch)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
