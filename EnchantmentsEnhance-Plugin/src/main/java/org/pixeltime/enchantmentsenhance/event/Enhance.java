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

package org.pixeltime.enchantmentsenhance.event;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.chat.Announcement;
import org.pixeltime.enchantmentsenhance.chat.Notification;
import org.pixeltime.enchantmentsenhance.enums.AnnounceType;
import org.pixeltime.enchantmentsenhance.enums.ItemType;
import org.pixeltime.enchantmentsenhance.gui.Clickable;
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.DataManager;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.MaterialManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.Random;

public class Enhance {

    /**
     * Finds the corresponding enhancement stone ID.
     *
     * @param item
     * @param level
     * @return
     */
    public static int getStoneId(ItemStack item, int level, Clickable clicked) {
        if (clicked.equals(MainMenu.gear)) {
            if (ItemManager.isValid(item, MaterialManager.weapon)
                    || (ItemManager.isValid(item, MaterialManager.bow))) {
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
        } else if (clicked.equals(MainMenu.tool)) {
            if (ItemManager.isValid(item, MaterialManager.weapon)
                    || ItemManager.isValid(item, MaterialManager.pickaxe)
                    || ItemManager.isValid(item, MaterialManager.axe)
                    || ItemManager.isValid(item, MaterialManager.hoe)
                    || ItemManager.isValid(item, MaterialManager.shovel)
                    || ItemManager.isValid(item, MaterialManager.knife)
                    || ItemManager.isValid(item, MaterialManager.rod)) {
                if (DataManager.requireConcentratedStones[level]) {
                    return 2;
                } else {
                    return 0;
                }
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
        if (SettingsManager.config.getBoolean("enableEERenamedItemOnly") && ItemManager.getItemName(item).isEmpty()) {
            return false;
        }
        return (ItemManager.getItemEnchantmentType(item) != ItemType.INVALID)
                && (ItemManager.getItemEnchantLevel(item) == 0 || (ItemManager.getItemEnchantLevel(item) < DataManager.levels - 1))
                && ItemManager.getToolEnchantLevel(item) == 0;
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
            int enchantLevel,
            Clickable clicked) {
        // Enchant level after a successful enhancement

        ItemStack forged = ItemManager.forgeItemWithReplacement(player, item, enchantLevel, true, clicked);

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
            int level, Clickable clicked) {
        // Failed message.
        String[] msg = new String[2];
        msg[0] = SettingsManager.lang.getString("enhance.enhanceFailed");
        // Play failed sound.
        CompatibilityManager.playsound.playSound(player, "FAILED");
        // Add failstack.
        Main.getApi().addFailstack(player.getName(),
                DataManager.failstackGainedPerFail[level]);
        Random random = new Random();
        if (random.nextDouble() < DataManager.destroyChanceIfFail[level]) {
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
        } else if (random.nextDouble() < DataManager.downgradeChanceIfFail[level]) {
            // Downgrade failed item.
            msg[1] = (SettingsManager.lang.getString("enhance.downgraded"));
            // Play destroyed sound.
            CompatibilityManager.playsound.playSound(player, "DOWNGRADED");
            // Item level after failing.
            int enchantLevel = level - 2;
            // Updates the item.
            ItemManager.forgeItemWithReplacement(player, item, enchantLevel, false, clicked);
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

    public static boolean getValidationOfPlayer(ItemStack item, Player player, Clickable clicked) {
        // Current enchant level before enhancing
        int enchantLevel = ItemManager.getItemEnchantLevel(item) + 1;
        // Finds the stone used in the enhancement
        int stoneId = getStoneId(item, enchantLevel, clicked);
        // Checks if player has enough enchant stone
        return Main.getApi().getItem(player.getName(), stoneId) - 1 >= 0;
    }

    public static boolean getToolValidationOfPlayer(ItemStack item, Player player, Clickable clicked) {
        // Current enchant level before enhancing
        int enchantLevel = ItemManager.getToolEnchantLevel(item) + 1;
        // Finds the stone used in the enhancement
        int stoneId = getStoneId(item, enchantLevel, clicked);
        // Checks if player has enough enchant stone
        return Main.getApi().getItem(player.getName(), stoneId) - 1 >= 0;
    }

    public static boolean validate(ItemStack item, Player player, Clickable clicked) {
        boolean valid = false;
        if (Enhance.getValidationOfItem(item) && Enhance.getValidationOfPlayer(item, player, clicked)) {
            valid = true;
        }

        if (Enhance.getValidationOfToolItem(item) && Enhance.getToolValidationOfPlayer(item, player, clicked)) {
            valid = true;
        }
        return valid;
    }

    public static boolean validateForce(ItemStack item, Player player, Clickable clicked) {
        boolean valid = false;
        if (Enhance.getValidationOfForce(item, player, clicked)) {
            valid = true;
        }
        return valid;
    }

    /**
     * Randomly generates a result to the enhancement.
     *
     * @param item
     * @param player
     */
    public static void diceToEnhancement(ItemStack item, Player player, Clickable clicked) {
        int enchantLevel;
        if (clicked.equals(MainMenu.gear)) {
            enchantLevel = ItemManager.getItemEnchantLevel(item) + 1;
        } else if (clicked.equals(MainMenu.tool)) {
            enchantLevel = ItemManager.getToolEnchantLevel(item) + 1;
        } else {
            return;
        }
        int stoneId = getStoneId(item, enchantLevel, clicked);

        if (!validate(item, player, clicked)) {
            return;
        }

        Main.getApi().addItem(player.getName(), stoneId, -1);
        // Randomly generate a double between 0 to 1
        double random = Math.random();
        // Calculate the chance
        double chance = Main.getApi().getChance(player.getName(), enchantLevel);
        // Proceed to enhance
        if (random < chance) {
            enhanceSuccess(item, player, false, enchantLevel, clicked);
        } else {
            enhanceFail(item, player, enchantLevel, clicked);
        }

    }

    public static int getForceEnhanceCost(ItemStack item, Player player,
                                          Clickable clicked) {
        // Current enchant level before enhancing
        int enchantLevel;
        if (clicked.equals(MainMenu.gear)) {
            enchantLevel = ItemManager.getItemEnchantLevel(item) + 1;
        } else if (clicked.equals(MainMenu.tool)) {
            enchantLevel = ItemManager.getToolEnchantLevel(item) + 1;
        } else {
            return -1;
        }
        // Gets the cost of force enhancing
        return DataManager.costToForceEnchant[enchantLevel];
    }

    public static boolean getValidationOfForce(ItemStack item, Player player, Clickable clicked) {
        // Current enchant level before enhancing
        int enchantLevel;
        if (clicked.equals(MainMenu.gear)) {
            enchantLevel = ItemManager.getItemEnchantLevel(item) + 1;
        } else if (clicked.equals(MainMenu.tool)) {
            enchantLevel = ItemManager.getToolEnchantLevel(item) + 1;
        } else {
            return false;
        }
        // Finds the stone used in the enhancement
        int stoneId = getStoneId(item, enchantLevel, clicked);
        // Finds the cost of enhance.
        int costToEnhance = getForceEnhanceCost(item, player, clicked);
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
    public static void forceToEnhancement(ItemStack item, Player player, Clickable clicked) {
        if (!validateForce(item, player, clicked)) {
            return;
        }
        // Current enchant level before enhancing
        int enchantLevel;
        if (clicked.equals(MainMenu.gear)) {
            enchantLevel = ItemManager.getItemEnchantLevel(item) + 1;
        } else if (clicked.equals(MainMenu.tool)) {
            enchantLevel = ItemManager.getToolEnchantLevel(item) + 1;
        } else {
            return;
        }
        // Finds the stone used in the enhancement
        int stoneId = getStoneId(item, enchantLevel, clicked);
        // Finds the cost of enhance.
        int costToEnhance = getForceEnhanceCost(item, player, clicked);
        Main.getApi().addItem(player.getName(), stoneId, -costToEnhance);
        enhanceSuccess(item, player, true, enchantLevel, clicked);
    }


    /**
     * Gets chance as a list.
     *
     * @param item
     * @param playerName
     * @return
     */
    public static String getChance(ItemStack item, String playerName, Clickable clicked) {
        // Enchantment type
        ItemType type = ItemManager.getItemEnchantmentType(item);
        ItemType tooltype = ItemManager.getToolItemEnchantmentType(item);
        if (type != ItemType.INVALID || tooltype != ItemType.INVALID) {
            // Display failstack
            String placeholder = "";
            if (clicked.equals(MainMenu.gear)) {
                placeholder = String.format("%.2f", Main.getApi().getChance(
                        playerName, ItemManager.getItemEnchantLevel(item) + 1) * 100);
            } else if (clicked.equals(MainMenu.tool)) {
                placeholder = String.format("%.2f", Main.getApi().getChance(
                        playerName, ItemManager.getToolEnchantLevel(item) + 1) * 100);
            }

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


    public static boolean getValidationOfToolItem(ItemStack item) {
        // If item cannot be enhanced
        if (!SettingsManager.config.getBoolean("enableStackedItem") && item.getAmount() > 1) {
            return false;
        }
        if (SettingsManager.config.getBoolean("enableEERenamedItemOnly") && ItemManager.getItemName(item).isEmpty()) {
            return false;
        }
        return (ItemManager.getToolItemEnchantmentType(item) != ItemType.INVALID)
                && (ItemManager.getToolEnchantLevel(item) >= 0 && (ItemManager.getToolEnchantLevel(item) < DataManager.levels - 1))
                && ItemManager.getItemEnchantLevel(item) == 0;
    }
}
