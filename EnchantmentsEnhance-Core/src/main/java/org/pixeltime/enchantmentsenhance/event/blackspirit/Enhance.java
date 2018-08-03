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

package org.pixeltime.enchantmentsenhance.event.blackspirit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.api.API;
import org.pixeltime.enchantmentsenhance.chat.Broadcast;
import org.pixeltime.enchantmentsenhance.enums.ItemType;
import org.pixeltime.enchantmentsenhance.manager.*;
import org.pixeltime.enchantmentsenhance.util.Util;

public class Enhance {

    /**
     * Finds the corresponding enhancement stone ID.
     *
     * @param item
     * @param level
     * @return
     */
    public static int getStoneId(ItemStack item, int level) {
        if (ItemManager.isValid(item, MM.weapon)) {
            if (DataManager.requireConcentratedStones[level]) {
                return 2;
            } else {
                return 0;
            }
        }
        if (ItemManager.isValid(item, MM.armor)) {
            if (DataManager.requireConcentratedStones[level]) {
                return 3;
            } else {
                return 1;
            }
        }
        return -1;
    }


    /**
     * Determines the enhancement eligibility of the item.
     *
     * @param item
     * @return
     */
    public static boolean getValidationOfItem(ItemStack item) {
        // If item cannot be enhanced
        // If item level exceeds the maximum levels allowed
        return (ItemManager.getItemEnchantmentType(item) != ItemType.INVALID)
                && (ItemManager.getItemEnchantLevel(item) == 0 || (ItemManager.getItemEnchantLevel(item) < DataManager.levels - 1));
    }


    /**
     * Calls when enhancement is success.
     *
     * @param item
     * @param player
     * @param forceEnhanced
     * @param enchantLevel
     */
    public static void enhanceSuccess(
            ItemStack item,
            Player player,
            boolean forceEnhanced,
            int enchantLevel) {
        // Enchant level after a successful enhancement

        ItemManager.forgeItem(player, item, enchantLevel, true);

        // Play sound
        CompatibilityManager.playsound.playSound(player, "SUCCESS");
        // Launch fireworks
        CompatibilityManager.spawnFirework.launch(player, 1,
                DataManager.fireworkRounds[enchantLevel], SettingsManager.config
                        .getInt("fireworkDelay"));
        // Do not clear failstack if force enhanced
        if (forceEnhanced) {
            Util.sendMessage(SettingsManager.lang.getString(
                    "Enhance.forceEnhanceSuccess"), player);
        } else {
            // Clear used failstack
            API.resetFailstack(player.getName());
            Util.sendMessage(SettingsManager.lang.getString(
                    "Enhance.enhanceSuccess"), player);
        }
    }


    /**
     * Calls when enhancement is failed.
     *
     * @param item
     * @param player
     */
    public static void enhanceFail(
            ItemStack item,
            Player player,
            int level) {
        // Failed message.
        String str = SettingsManager.lang.getString("Enhance.enhanceFailed");
        // Play failed sound.
        CompatibilityManager.playsound.playSound(player, "FAILED");
        // Add failstack.
        API.addFailstack(player.getName(),
                DataManager.failstackGainedPerFail[level]);
        if (DataManager.destroyIfFail[level]) {
            // Destroy failed item.
            player.getInventory().remove(item);
            // Adds destroyed message.
            str += ("\n" + SettingsManager.lang.getString(
                    "Enhance.destroyed"));
        } else if (DataManager.downgradeIfFail[level]) {
            // Downgrade failed item.
            str += ("\n" + SettingsManager.lang.getString(
                    "Enhance.downgraded"));
            // Play destroyed sound.
            CompatibilityManager.playsound.playSound(player, "DOWNGRADED");
            // Item level after failing.
            int enchantLevel = level - 2;
            // Updates the item.
            ItemManager.forgeItem(player, item, enchantLevel, false);
        }
        // Sends the failed message.
        Util.sendMessage(str, player);
    }


    /**
     * Randomly generates a result to the enhancement.
     *
     * @param item
     * @param player
     */
    public static void diceToEnhancement(ItemStack item, Player player) {
        // If the item is a valid item
        if (getValidationOfItem(item)) {
            // Current enchant level before enhancing
            int enchantLevel = ItemManager.getItemEnchantLevel(item) + 1;
            // Finds the stone used in the enhancement
            int stoneId = getStoneId(item, enchantLevel);
            // Checks if player has enough enchant stone
            if (API.getItem(player.getName(), stoneId) - 1 >= 0) {
                API.addItem(player.getName(), stoneId, -1);
                Util.sendMessage(SettingsManager.lang.getString("Item.use")
                        .replaceAll("%ITEM%", SettingsManager.lang.getString("Item."
                                + stoneId)), player);
                // Randomly generate a double between 0 to 1
                double random = Math.random();
                // Calculate the chance
                double chance = API.getChance(player.getName(), enchantLevel);
                // Enhancement result
                boolean success = random < chance;
                // Broadcast if attempting enhancement meet enchant level
                // Proceed to enhance
                if (success) {
                    enhanceSuccess(item, player, false, enchantLevel);
                    if (DataManager.downgradeIfFail[enchantLevel]) {
                        Broadcast.broadcast(player, item, "success");
                    }
                } else {
                    enhanceFail(item, player, enchantLevel);
                    if (DataManager.downgradeIfFail[enchantLevel]) {
                        Broadcast.broadcast(player, item, "failed");
                    }
                }
            }
            // Not enough enchant stone
            else {
                Util.sendMessage(SettingsManager.lang.getString("Item.noItem")
                        .replaceAll("%STONE%", SettingsManager.lang.getString(
                                "Item." + stoneId)), player);
            }
        }
        // Not a valid item
        else {
            Util.sendMessage(SettingsManager.lang.getString("Item.invalid"),
                    player);
        }
    }


    /**
     * Guarantees a successful enhancement.
     *
     * @param item
     * @param player
     */
    public static void forceToEnhancement(ItemStack item, Player player) {
        // If the item is a valid item
        if (getValidationOfItem(item)) {
            // Current enchant level before enhancing
            int enchantLevel = ItemManager.getItemEnchantLevel(item) + 1;
            // Finds the stone used in the enhancement
            int stoneId = getStoneId(item, enchantLevel);
            // Gets the cost of force enhancing
            int costToEnhance = DataManager.costToForceEnchant[enchantLevel];
            if (costToEnhance == -1) {
                Util.sendMessage(SettingsManager.lang.getString("Item.invalid"),
                        player);
                return;
            }
            // Checks if player has enough enchant stone
            if (API.getItem(player.getName(), stoneId) - costToEnhance >= 0) {
                API.addItem(player.getName(), stoneId, -costToEnhance);
                enhanceSuccess(item, player, true, enchantLevel);
                // Broadcast if attempting enhancement meet enchant level
                if (DataManager.downgradeIfFail[enchantLevel]) {
                    Broadcast.broadcast(player, item, "success");
                }
            }
            // Not enough enchant stone
            else {
                Util.sendMessage(SettingsManager.lang.getString("Item.noItem")
                        .replaceAll("%STONE%", SettingsManager.lang.getString(
                                "Item." + stoneId)), player);
            }
        }
        // Not a valid item
        else {
            Util.sendMessage(SettingsManager.lang.getString("Item.invalid"),
                    player);
        }
    }


    /**
     * Gets chance as a list.
     *
     * @param item
     * @param playerName
     * @return
     */
    public static String getChance(ItemStack item, String playerName) {
        // Enchantment type
        ItemType type = ItemManager.getItemEnchantmentType(item);
        if (type != ItemType.INVALID) {
            // Display failstack
            String placeholder = String.format("%.2f", API.getChance(
                    playerName, ItemManager.getItemEnchantLevel(item) + 1) * 100);
            // Display chance after failstack is applied
            String chance = SettingsManager.lang.getString(
                    "Enhance.successRate").replaceAll("%chance%", placeholder);
            return chance;
        }
        // Invalid item
        else {
            return (SettingsManager.lang.getString("Enhance.itemInvalid"));
        }
    }
}
