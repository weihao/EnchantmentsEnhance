package org.pixeltime.healpot.enhancement.events.blackspirit;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.healpot.enhancement.events.inventory.Inventory;
import org.pixeltime.healpot.enhancement.manager.Permissions;
import org.pixeltime.healpot.enhancement.manager.SettingsManager;
import org.pixeltime.healpot.enhancement.manager.modular.SpawnFirework;
import org.pixeltime.healpot.enhancement.tasks.Main;
import org.pixeltime.healpot.enhancement.util.Broadcast;
import org.pixeltime.healpot.enhancement.util.Util;

public class Enhance {
    /**
     * Determines the enchantment type for the enhancing item.
     * 
     * @param player
     * @param item
     * @return
     */
    public static Enchantment getItemEnchantmentType(
        Player player,
        ItemStack item) {
        if ((item.getType() == Material.DIAMOND_SWORD || item
            .getType() == Material.DIAMOND_AXE)) {
            return Enchantment.DAMAGE_ALL;
        }
        else if ((item.getType() == Material.DIAMOND_CHESTPLATE || item
            .getType() == Material.DIAMOND_HELMET || item
                .getType() == Material.DIAMOND_LEGGINGS || item
                    .getType() == Material.DIAMOND_BOOTS)) {
            return Enchantment.PROTECTION_ENVIRONMENTAL;
        }
        return null;
    }


    /**
     * Finds the corresponding enhancement stone ID.
     * 
     * @param player
     * @param item
     * @param level
     * @return
     */
    public static int getStoneId(Player player, ItemStack item, int level) {
        if ((item.getType() == Material.DIAMOND_SWORD || item
            .getType() == Material.DIAMOND_AXE)) {
            if (level > 13) {
                return 2;
            }
            else {
                return 0;
            }
        }
        else if ((item.getType() == Material.DIAMOND_CHESTPLATE || item
            .getType() == Material.DIAMOND_HELMET || item
                .getType() == Material.DIAMOND_LEGGINGS || item
                    .getType() == Material.DIAMOND_BOOTS)) {
            if (level > 13) {
                return 3;
            }
            else {
                return 1;
            }
        }
        return -1;
    }


    /**
     * Gets the level of the enhancing item.
     * 
     * @param player
     * @param item
     * @return
     */
    public static int getItemEnchantLevel(Player player, ItemStack item) {
        if ((getItemEnchantmentType(player, item)) != null) {
            return item.getEnchantmentLevel(getItemEnchantmentType(player,
                item));
        }
        else {
            return 0;
        }
    }


    /**
     * Determines the enhancement eligibility of the item.
     * 
     * @param player
     * @param item
     * @return
     */
    public static boolean getValidationOfItem(Player player, ItemStack item) {
        if (getItemEnchantmentType(player, item) == null) {
            // sendMessage(SettingsManager.lang.getString("Config.pluginTag")
            // + SettingsManager.lang.getString("Enhance.itemInvalid"), player);
            return false;
        }
        else if (getItemEnchantLevel(player, item) >= 19) {
            // sendMessage(SettingsManager.lang.getString("Config.pluginTag")
            // + SettingsManager.lang.getString("Enhance.itemMax"), player);
            return false;
        }
        else {
            return true;
        }
    }


    /**
     * Calls when enhancement is success.
     * 
     * @param item
     * @param player
     * @param forceEnhanced
     */
    public static void enhanceSuccess(
        ItemStack item,
        Player player,
        boolean forceEnhanced) {
        Enchantment enchant = getItemEnchantmentType(player, item);
        int enchantLevel = getItemEnchantLevel(player, item) + 1;
        item.addUnsafeEnchantment(enchant, enchantLevel);
        Util.renameItem(item, enchantLevel);
        Main.compatibility.playsound.playSound(player, "SUCCESS");
        SpawnFirework.launch(player, SettingsManager.config.getInt(
            "fireworkCount." + enchantLevel), SettingsManager.config.getInt(
                "fireworkRounds." + enchantLevel), SettingsManager.config
                    .getInt("fireworkDelay"));
        if (forceEnhanced) {
            Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
                + SettingsManager.lang.getString("Enhance.forceEnhanceSuccess"),
                player);
        }
        else {
            Failstack.resetLevel(player);
            Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
                + SettingsManager.lang.getString("Enhance.enhanceSuccess"),
                player);
        }
        Lore.addLore(item, player, ChatColor.translateAlternateColorCodes('&',
            SettingsManager.lang.getString("Lore." + SettingsManager.config
                .getString("lore.bound") + "Lore")), true);
    }


    /**
     * Calls when enhancement is failed.
     * 
     * @param item
     * @param player
     */
    public static void enhanceFail(ItemStack item, Player player) {
        Enchantment enchant = getItemEnchantmentType(player, item);
        int enchantLevel = getItemEnchantLevel(player, item);
        String str = SettingsManager.lang.getString("Enhance.enhanceFailed");
        Main.compatibility.playsound.playSound(player, "FAILED");
        Failstack.addLevel(player, SettingsManager.config.getInt(
            "failstackGained." + enchantLevel));
        if (enchantLevel > 15) {
            str += (" " + SettingsManager.lang.getString("Enhance.downgraded"));
            Main.compatibility.playsound.playSound(player, "DOWNGRADED");
            item.addUnsafeEnchantment(enchant, enchantLevel - 1);
            Util.renameItem(item, (enchantLevel - 1));
        }
        Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
            + str, player);
    }


    /**
     * Randomly generates a result to the enhancement.
     * 
     * @param item
     * @param player
     */
    public static void diceToEnhancement(ItemStack item, Player player) {
        if (getValidationOfItem(player, item)) {
            int enchantLevel = getItemEnchantLevel(player, item);
            int stoneId = getStoneId(player, item, enchantLevel);
            if (Inventory.getLevel(stoneId, player) - 1 >= 0) {
                Inventory.addLevel(player, stoneId, -1);
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.pluginTag") + SettingsManager.lang.getString(
                        "Item.use").replaceAll("%ITEM%", SettingsManager.lang
                            .getString("Item." + stoneId)), player);
                double random = Math.random();
                double chance = Failstack.getChance(player, enchantLevel);
                if (enchantLevel > 15) {
                    Broadcast.broadcast(player, item, enchantLevel,
                        random < chance);
                }
                if (random < chance) {
                    enhanceSuccess(item, player, false);
                }
                else {
                    enhanceFail(item, player);
                }
            }
            else {
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.pluginTag") + SettingsManager.lang.getString(
                        "Item.noItem").replaceAll("%STONE%",
                            SettingsManager.lang.getString("Item." + stoneId)),
                    player);
            }
        }
        else {
            Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
                + SettingsManager.lang.getString("Item.invalid"), player);
        }
    }


    /**
     * Guarantees a successful enhancement.
     * 
     * @param item
     * @param player
     */
    public static void forceToEnhancement(ItemStack item, Player player) {
        if (getValidationOfItem(player, item) && Permissions.commandEnhance(
            player)) {
            int enchantLevel = getItemEnchantLevel(player, item);
            int stoneId = getStoneId(player, item, enchantLevel);
            int costToEnhance = SettingsManager.config.getInt("costToForce."
                + enchantLevel);
            if (Inventory.getLevel(stoneId, player) - costToEnhance > 0) {
                Inventory.addLevel(player, stoneId, -costToEnhance);
                enhanceSuccess(item, player, true);
                if (enchantLevel > 15) {
                    Broadcast.broadcast(player, item, enchantLevel, true);
                }
            }
            else {
                Util.sendMessage(SettingsManager.lang.getString(
                    "Config.pluginTag") + SettingsManager.lang.getString(
                        "Item.noItem").replaceAll("%STONE%",
                            SettingsManager.lang.getString("Item." + stoneId)),
                    player);
            }
        }
        else {
            Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
                + SettingsManager.lang.getString("Item.invalid"), player);
        }
    }


    /**
     * Gets enhancement success chance.
     * 
     * @param item
     * @param player
     */
    public static void getChance(ItemStack item, Player player) {
        Enchantment enchant = getItemEnchantmentType(player, item);
        if (enchant != null) {
            Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
                + SettingsManager.lang.getString("Enhance.currentFailstack")
                + Failstack.getLevel(player), player);
            String chance = String.format("%.2f", Failstack.getChance(player,
                item.getEnchantmentLevel(enchant)) * 100);
            Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
                + SettingsManager.lang.getString("Enhance.successRate")
                    .replaceAll("%chance%", chance), player);
        }
        else {
            Util.sendMessage(SettingsManager.lang.getString("Config.pluginTag")
                + SettingsManager.lang.getString("Enhance.itemInvalid"),
                player);
        }
    }


    /**
     * Gets chance as a list.
     * 
     * @param item
     * @param player
     * @return
     */
    public static List<String> getChanceAsList(ItemStack item, Player player) {
        Enchantment enchant = getItemEnchantmentType(player, item);
        ArrayList<String> result = new ArrayList<String>();
        if (enchant != null) {
            String fs = (SettingsManager.lang.getString(
                "Enhance.currentFailstack") + Failstack.getLevel(player));
            String placeholder = String.format("%.2f", Failstack.getChance(
                player, item.getEnchantmentLevel(enchant)) * 100);
            String chance = SettingsManager.lang.getString(
                "Enhance.successRate").replaceAll("%chance%", placeholder);
            result.add(Util.toColor(fs));
            result.add(Util.toColor(chance));
            result.add(Util.toColor(SettingsManager.lang.getString(
                "Menu.lore.stats1")));
            result.add(Util.toColor(SettingsManager.lang.getString(
                "Menu.lore.stats2")));
            return result;

        }
        else {
            result.add(SettingsManager.lang.getString("Enhance.itemInvalid"));
            return result;
        }
    }
}
