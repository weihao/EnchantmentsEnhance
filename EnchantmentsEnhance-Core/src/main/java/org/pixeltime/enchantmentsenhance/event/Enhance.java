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

package org.pixeltime.enchantmentsenhance.event;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.chat.Announcement;
import org.pixeltime.enchantmentsenhance.chat.Notification;
import org.pixeltime.enchantmentsenhance.enums.AnnounceType;
import org.pixeltime.enchantmentsenhance.enums.ItemType;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
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
        if (ItemManager.isValid(item, MaterialManager.weapon)) {
            if (DataManager.requireConcentratedStones[level]) {
                return 2;
            } else {
                return 0;
            }
        }
        if (ItemManager.isValid(item, MaterialManager.armor)) {
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
        if (!SettingsManager.config.getBoolean("enableStackedItem") && item.getAmount() > 1) {
            return false;
        }
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

        ItemStack forged = ItemManager.forgeItem(player, item, enchantLevel, true);

        // Play sound
        CompatibilityManager.playsound.playSound(player, "SUCCESS");
        // Launch fireworks
        CompatibilityManager.spawnFirework.launch(player, 1,
                DataManager.fireworkRounds[enchantLevel], SettingsManager.config
                        .getInt("fireworkDelay"));
        // Do not clear failstack if force enhanced
        if (forceEnhanced) {
            Util.sendMessage(SettingsManager.lang.getString(
                    "enhance.forceEnhanceSuccess"), player);
        } else {
            // Clear used failstack
            Main.getApi().resetFailstack(player.getName());
            Main.getNotifierManager()
                    .call(new Notification(player, SettingsManager.lang.getString("enhance.enhanceSuccess")));
        }
        if (DataManager.broadcastEnhance[enchantLevel]) {
            String msg = SettingsManager.lang.getString("annoucer.success")
                    .replace("%player%", player.getName())
                    .replace("%item%", forged.getItemMeta().getDisplayName());
            Main.getAnnoucerManager().call(new Announcement(msg, AnnounceType.SUCCESS));
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
        String[] msg = new String[2];
        msg[0] = SettingsManager.lang.getString("enhance.enhanceFailed");
        // Play failed sound.
        CompatibilityManager.playsound.playSound(player, "FAILED");
        // Add failstack.
        Main.getApi().addFailstack(player.getName(),
                DataManager.failstackGainedPerFail[level]);
        if (DataManager.destroyIfFail[level]) {
            // Broadcast
            if (DataManager.broadcastEnhance[level]) {
                String str = SettingsManager.lang.getString("annoucer.destroyed")
                        .replace("%player%", player.getName())
                        .replace("%item%", item.getItemMeta().getDisplayName());
                Main.getAnnoucerManager().call(new Announcement(str, AnnounceType.FAIL));
            }
            // Destroy failed item.
            player.getInventory().removeItem(item);
            MainMenu.clearPlayer(player.getName());
            // Adds destroyed message.
            msg[1] = (SettingsManager.lang.getString("enhance.destroyed"));
        } else if (DataManager.downgradeIfFail[level]) {
            // Downgrade failed item.
            msg[1] = (SettingsManager.lang.getString("enhance.downgraded"));
            // Play destroyed sound.
            CompatibilityManager.playsound.playSound(player, "DOWNGRADED");
            // Item level after failing.
            int enchantLevel = level - 2;
            // Updates the item.
            ItemManager.forgeItem(player, item, enchantLevel, false);
            // Broadcast
            if (DataManager.broadcastEnhance[level]) {
                String str = SettingsManager.lang.getString("annoucer.failed")
                        .replace("%player%", player.getName())
                        .replace("%item%", item.getItemMeta().getDisplayName());
                Main.getAnnoucerManager().call(new Announcement(str, AnnounceType.FAIL));
            }
        }
        // Sends the failed message.
        Main.getNotifierManager().call(new Notification(player, msg));
    }

    public static boolean getValidationOfPlayer(ItemStack item, Player player) {
        // Current enchant level before enhancing
        int enchantLevel = ItemManager.getItemEnchantLevel(item) + 1;
        // Finds the stone used in the enhancement
        int stoneId = getStoneId(item, enchantLevel);
        // Checks if player has enough enchant stone
        return Main.getApi().getItem(player.getName(), stoneId) - 1 >= 0;
    }

    /**
     * Randomly generates a result to the enhancement.
     *
     * @param item
     * @param player
     */
    public static void diceToEnhancement(ItemStack item, Player player) {
        // If the item is a valid item
        if (getValidationOfItem(item) && getValidationOfPlayer(item, player)) {
            int enchantLevel = ItemManager.getItemEnchantLevel(item) + 1;
            int stoneId = getStoneId(item, enchantLevel);
            Main.getApi().addItem(player.getName(), stoneId, -1);
            // Randomly generate a double between 0 to 1
            double random = Math.random();
            // Calculate the chance
            double chance = Main.getApi().getChance(player.getName(), enchantLevel);
            // Proceed to enhance
            if (random < chance) {
                enhanceSuccess(item, player, false, enchantLevel);
            } else {
                enhanceFail(item, player, enchantLevel);
            }
        }
        // Not enough enchant stone
    }

    public static int getForceEnhanceCost(ItemStack item, Player player) {
        // Current enchant level before enhancing
        int enchantLevel = ItemManager.getItemEnchantLevel(item) + 1;
        // Gets the cost of force enhancing
        return DataManager.costToForceEnchant[enchantLevel];
    }

    public static boolean getValidationOfForce(ItemStack item, Player player) {
        // Current enchant level before enhancing
        int enchantLevel = ItemManager.getItemEnchantLevel(item) + 1;
        // Finds the stone used in the enhancement
        int stoneId = getStoneId(item, enchantLevel);
        // Finds the cost of enhance.
        int costToEnhance = getForceEnhanceCost(item, player);
        if (costToEnhance <= 0) {
            return false;
        }
        return (Main.getApi().getItem(player.getName(), stoneId) - costToEnhance >= 0);
    }


    /**
     * Guarantees a successful enhancement.
     *
     * @param item
     * @param player
     */
    public static void forceToEnhancement(ItemStack item, Player player) {
        // Current enchant level before enhancing
        int enchantLevel = ItemManager.getItemEnchantLevel(item) + 1;
        // Finds the stone used in the enhancement
        int stoneId = getStoneId(item, enchantLevel);
        // Finds the cost of enhance.
        int costToEnhance = getForceEnhanceCost(item, player);
        Main.getApi().addItem(player.getName(), stoneId, -costToEnhance);
        enhanceSuccess(item, player, true, enchantLevel);
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
            String placeholder = String.format("%.2f", Main.getApi().getChance(
                    playerName, ItemManager.getItemEnchantLevel(item) + 1) * 100);
            // Display chance after failstack is applied
            String chance = SettingsManager.lang.getString(
                    "enhance.successRate").replaceAll("%chance%", placeholder);
            return chance;
        }
        // Invalid item
        else {
            return (SettingsManager.lang.getString("enhance.itemInvalid"));
        }
    }


}
