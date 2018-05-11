package org.pixeltime.enchantmentsenhance.events.blackspirit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.pixeltime.enchantmentsenhance.events.inventory.Inventory;
import org.pixeltime.enchantmentsenhance.listeners.MenuHandler;
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager;
import org.pixeltime.enchantmentsenhance.manager.DataManager;
import org.pixeltime.enchantmentsenhance.manager.ItemManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;
import org.pixeltime.enchantmentsenhance.util.Broadcast;
import org.pixeltime.enchantmentsenhance.util.ItemTypes;
import org.pixeltime.enchantmentsenhance.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Enhance {

    /**
     * Finds the corresponding enhancement stone ID.
     *
     * @param player
     * @param item
     * @param level
     * @return
     */
    public static int getStoneId(Player player, ItemStack item, int level) {
        if (ItemManager.isValid(item, Util.weapon)) {
            if (isPhaseTwo(level)) {
                return 2;
            } else {
                return 0;
            }
        }
        if (ItemManager.isValid(item, Util.armor)) {
            if ((isPhaseTwo(level))) {
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
     * @param player
     * @param item
     * @return
     */
    public static boolean getValidationOfItem(Player player, ItemStack item) {
        // If item cannot be enhanced
        // If item level exceeds the maximum levels allowed
        if ((ItemManager.getItemEnchantmentType(item) == ItemTypes.INVALID)
                || (ItemManager.getItemEnchantLevel(item) >= DataManager.levels)) {
            return false;
        }
        return true;
    }


    /**
     * Calls when enhancement is success.
     *
     * @param item
     * @param player
     * @param forceEnhanced
     * @param enchantLevelBeforeAttemptEnhancing
     */
    public static void enhanceSuccess(
            ItemStack item,
            Player player,
            boolean forceEnhanced,
            int enchantLevelBeforeAttemptEnhancing) {
        // Enchant level after a successful enhancement

        int enchantLevel = enchantLevelBeforeAttemptEnhancing + 1;

        MenuHandler.updateItem(player, ItemManager.forgeItem(item,
                enchantLevel));

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
            Failstack.resetLevel(player);
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
            int enchantLevelBeforeAttemptEnhancing) {
        String str = SettingsManager.lang.getString("Enhance.enhanceFailed");
        CompatibilityManager.playsound.playSound(player, "FAILED");
        Failstack.addLevel(player,
                DataManager.failstackGainedPerFail[enchantLevelBeforeAttemptEnhancing]);
        if (isPhaseDowngrade(enchantLevelBeforeAttemptEnhancing)) {
            str += ("\n" + SettingsManager.lang.getString(
                    "Enhance.downgraded"));
            CompatibilityManager.playsound.playSound(player, "DOWNGRADED");
            int enchantLevel = enchantLevelBeforeAttemptEnhancing - 1;
            MenuHandler.updateItem(player, ItemManager.forgeItem(item,
                    enchantLevel));
        }
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
        if (getValidationOfItem(player, item)) {
            // Current enchant level before enhancing
            int enchantLevel = ItemManager.getItemEnchantLevel(item);
            // Finds the stone used in the enhancement
            int stoneId = getStoneId(player, item, enchantLevel);
            // Checks if player has enough enchant stone
            if (Inventory.getLevel(stoneId, player) - 1 >= 0) {
                Inventory.addLevel(player, stoneId, -1);
                Util.sendMessage(SettingsManager.lang.getString("Item.use")
                        .replaceAll("%ITEM%", SettingsManager.lang.getString("Item."
                                + stoneId)), player);
                // Randomly generate a double between 0 to 1
                double random = Math.random();
                // Calculate the chance
                double chance = Failstack.getChance(player, enchantLevel);
                // Enhancement result
                boolean success = random < chance;
                // Broadcast if attempting enhancement meet enchant level
                if (isPhaseDowngrade(enchantLevel)) {
                    Broadcast.broadcast(player, item, enchantLevel, success);
                }
                // Proceed to enhance
                if (success) {
                    enhanceSuccess(item, player, false, enchantLevel);
                } else {
                    enhanceFail(item, player, enchantLevel);
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
        if (getValidationOfItem(player, item)) {
            // Current enchant level before enhancing
            int enchantLevel = ItemManager.getItemEnchantLevel(item);
            // Finds the stone used in the enhancement
            int stoneId = getStoneId(player, item, enchantLevel);
            // Gets the cost of force enhancing
            int costToEnhance = DataManager.costToForceEnchant[enchantLevel];
            // Checks if player has enough enchant stone
            if (Inventory.getLevel(stoneId, player) - costToEnhance >= 0) {
                Inventory.addLevel(player, stoneId, -costToEnhance);
                enhanceSuccess(item, player, true, enchantLevel);
                // Broadcast if attempting enhancement meet enchant level
                if (isPhaseTwo(enchantLevel)) {
                    Broadcast.broadcast(player, item, enchantLevel, true);
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
     * @param player
     * @return
     */
    public static List<String> getChanceAsList(ItemStack item, Player player) {
        // Enchantment type
        ItemTypes type = ItemManager.getItemEnchantmentType(item);
        ArrayList<String> result = new ArrayList<String>();
        if (type != ItemTypes.INVALID) {
            // Display failstack
            String fs = (SettingsManager.lang.getString(
                    "Enhance.currentFailstack") + Failstack.getLevel(player));
            String placeholder = String.format("%.2f", Failstack.getChance(
                    player, ItemManager.getItemEnchantLevel(item)) * 100);
            // Display chance after failstack is applied
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
        // Invalid item
        else {
            result.add(SettingsManager.lang.getString("Enhance.itemInvalid"));
            return result;
        }
    }


    public static boolean isPhaseTwo(int lvl) {
        return (lvl >= DataManager.secondPhase);
    }


    public static boolean isPhaseDowngrade(int lvl) {
        return (lvl > DataManager.downgradePhase);
    }


    public static boolean isPhaseOne(int lvl) {
        return (lvl >= DataManager.firstPhase);
    }
}
