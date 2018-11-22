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

package org.pixeltime.enchantmentsenhance.event

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.chat.Announcement
import org.pixeltime.enchantmentsenhance.chat.Notification
import org.pixeltime.enchantmentsenhance.enums.AnnounceType
import org.pixeltime.enchantmentsenhance.enums.ItemType
import org.pixeltime.enchantmentsenhance.gui.Clickable
import org.pixeltime.enchantmentsenhance.gui.menu.MainMenu
import org.pixeltime.enchantmentsenhance.manager.CompatibilityManager
import org.pixeltime.enchantmentsenhance.manager.DataManager
import org.pixeltime.enchantmentsenhance.manager.ItemManager
import org.pixeltime.enchantmentsenhance.manager.MaterialManager
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util
import java.util.*

object Enhance {

    /**
     * Finds the corresponding enhancement stone ID.
     *
     * @param item
     * @param level
     * @return
     */
    fun getStoneId(item: ItemStack, level: Int, clicked: Clickable): Int {
        if (clicked == MainMenu.gear) {
            if (ItemManager.isValid(item, MaterialManager.weapon) || ItemManager.isValid(item, MaterialManager.bow)) {
                return if (DataManager.requireConcentratedStones[level]) {
                    2
                } else {
                    0
                }
            }
            if (ItemManager.isValid(item, MaterialManager.armor)) {
                return if (DataManager.requireConcentratedStones[level]) {
                    3
                } else {
                    1
                }
            }
        } else if (clicked == MainMenu.tool) {
            if (ItemManager.isValid(item, MaterialManager.weapon)
                || ItemManager.isValid(item, MaterialManager.pickaxe)
                || ItemManager.isValid(item, MaterialManager.axe)
                || ItemManager.isValid(item, MaterialManager.hoe)) {
                return if (DataManager.requireConcentratedStones[level]) {
                    2
                } else {
                    0
                }
            }
        }
        return -1
    }

    /**
     * Determines the enhancement eligibility of the item.
     *
     * @param item
     * @return
     */
    fun getValidationOfItem(item: ItemStack): Boolean {
        // If item cannot be enhanced
        return if (!SettingsManager.config.getBoolean("enableStackedItem") && item.amount > 1) {
            false
        } else ItemManager.getItemEnchantmentType(item) !== ItemType.INVALID && (ItemManager.getItemEnchantLevel(item) == 0 || ItemManager.getItemEnchantLevel(item) < DataManager.levels - 1)
    }

    /**
     * Calls when enhancement is success.
     *
     * @param item
     * @param player
     * @param forceEnhanced
     * @param enchantLevel
     */
    fun enhanceSuccess(
            item: ItemStack,
            player: Player,
            forceEnhanced: Boolean,
            enchantLevel: Int,
            clicked: Clickable) {
        // Enchant level after a successful enhancement

        val forged = ItemManager.forgeItem(player, item, enchantLevel, true, clicked)

        // Play sound
        CompatibilityManager.playsound.playSound(player, "SUCCESS")
        // Launch fireworks
        CompatibilityManager.spawnFirework.launch(player, 1,
                DataManager.fireworkRounds[enchantLevel], SettingsManager.config
            .getInt("fireworkDelay"))
        // Do not clear failstack if force enhanced
        if (forceEnhanced) {
            Util.sendMessage(SettingsManager.lang.getString(
                    "enhance.forceEnhanceSuccess"), player)
        } else {
            // Clear used failstack
            Main.getApi().resetFailstack(player.name)
            Main.getNotifierManager()
                .call(Notification(player, SettingsManager.lang.getString("enhance.enhanceSuccess")))
        }
        if (DataManager.broadcastEnhance[enchantLevel]) {
            val msg = SettingsManager.lang.getString("annoucer.success")
                .replace("%player%", player.name)
                .replace("%item%", forged!!.itemMeta.displayName)
            Main.getAnnoucerManager().call(Announcement(msg, AnnounceType.SUCCESS))
        }
    }

    /**
     * Calls when enhancement is failed.
     *
     * @param item
     * @param player
     */
    fun enhanceFail(
            item: ItemStack,
            player: Player,
            level: Int, clicked: Clickable) {
        // Failed message.
        val msg = arrayOfNulls<String>(2)
        msg[0] = SettingsManager.lang.getString("enhance.enhanceFailed")
        // Play failed sound.
        CompatibilityManager.playsound.playSound(player, "FAILED")
        // Add failstack.
        Main.getApi().addFailstack(player.name,
                DataManager.failstackGainedPerFail[level])
        val random = Random()
        if (random.nextDouble() < DataManager.destroyChanceIfFail[level]) {
            // Broadcast
            if (DataManager.broadcastEnhance[level]) {
                val str = SettingsManager.lang.getString("annoucer.destroyed")
                    .replace("%player%", player.name)
                    .replace("%item%", item.itemMeta.displayName)
                Main.getAnnoucerManager().call(Announcement(str, AnnounceType.FAIL))
            }
            // Destroy failed item.
            player.inventory.removeItem(item)
            MainMenu.clearPlayer(player.name)
            // Adds destroyed message.
            msg[1] = SettingsManager.lang.getString("enhance.destroyed")
        } else if (random.nextDouble() < DataManager.downgradeChanceIfFail[level]) {
            // Downgrade failed item.
            msg[1] = SettingsManager.lang.getString("enhance.downgraded")
            // Play destroyed sound.
            CompatibilityManager.playsound.playSound(player, "DOWNGRADED")
            // Item level after failing.
            val enchantLevel = level - 2
            // Updates the item.
            ItemManager.forgeItem(player, item, enchantLevel, false, clicked)
            // Broadcast
            if (DataManager.broadcastEnhance[level]) {
                val str = SettingsManager.lang.getString("annoucer.failed")
                    .replace("%player%", player.name)
                    .replace("%item%", item.itemMeta.displayName)
                Main.getAnnoucerManager().call(Announcement(str, AnnounceType.FAIL))
            }
        }
        // Sends the failed message.
        Main.getNotifierManager().call(Notification(player, *msg.requireNoNulls()))
    }

    fun getValidationOfPlayer(item: ItemStack, player: Player, clicked: Clickable): Boolean {
        // Current enchant level before enhancing
        val enchantLevel = ItemManager.getItemEnchantLevel(item) + 1
        // Finds the stone used in the enhancement
        val stoneId = getStoneId(item, enchantLevel, clicked)
        // Checks if player has enough enchant stone
        return Main.getApi().getItem(player.name, stoneId) - 1 >= 0
    }

    fun getToolValidationOfPlayer(item: ItemStack, player: Player, clicked: Clickable): Boolean {
        // Current enchant level before enhancing
        val enchantLevel = ItemManager.getToolEnchantLevel(item) + 1
        // Finds the stone used in the enhancement
        val stoneId = getStoneId(item, enchantLevel, clicked)
        // Checks if player has enough enchant stone
        return Main.getApi().getItem(player.name, stoneId) - 1 >= 0
    }

    fun validate(item: ItemStack, player: Player, clicked: Clickable): Boolean {
        var valid = false
        if (Enhance.getValidationOfItem(item) && Enhance.getValidationOfPlayer(item, player, clicked)) {
            valid = true
        }

        if (Enhance.getValidationOfToolItem(item) && Enhance.getToolValidationOfPlayer(item, player, clicked)) {
            valid = true
        }
        return valid
    }

    fun validateForce(item: ItemStack, player: Player, clicked: Clickable): Boolean {
        var valid = false
        if (Enhance.getValidationOfForce(item, player, clicked)) {
            valid = true
        }
        return valid
    }

    /**
     * Randomly generates a result to the enhancement.
     *
     * @param item
     * @param player
     */
    fun diceToEnhancement(item: ItemStack, player: Player, clicked: Clickable) {
        val enchantLevel: Int
        if (clicked == MainMenu.gear) {
            enchantLevel = ItemManager.getItemEnchantLevel(item) + 1
        } else if (clicked == MainMenu.tool) {
            enchantLevel = ItemManager.getToolEnchantLevel(item) + 1
        } else {
            return
        }
        val stoneId = getStoneId(item, enchantLevel, clicked)

        if (!validate(item, player, clicked)) {
            return
        }

        Main.getApi().addItem(player.name, stoneId, -1)
        // Randomly generate a double between 0 to 1
        val random = Math.random()
        // Calculate the chance
        val chance = Main.getApi().getChance(player.name, enchantLevel)
        // Proceed to enhance
        if (random < chance) {
            enhanceSuccess(item, player, false, enchantLevel, clicked)
        } else {
            enhanceFail(item, player, enchantLevel, clicked)
        }

    }

    fun getForceEnhanceCost(item: ItemStack, player: Player): Int {
        // Current enchant level before enhancing
        val enchantLevel = ItemManager.getItemEnchantLevel(item) + 1
        // Gets the cost of force enhancing
        return DataManager.costToForceEnchant[enchantLevel]
    }

    fun getValidationOfForce(item: ItemStack, player: Player, clicked: Clickable): Boolean {
        // Current enchant level before enhancing
        val enchantLevel = ItemManager.getItemEnchantLevel(item) + 1
        // Finds the stone used in the enhancement
        val stoneId = getStoneId(item, enchantLevel, clicked)
        // Finds the cost of enhance.
        val costToEnhance = getForceEnhanceCost(item, player)
        return if (costToEnhance <= 0) {
            false
        } else Main.getApi().getItem(player.name, stoneId) - costToEnhance >= 0
    }

    /**
     * Guarantees a successful enhancement.
     *
     * @param item
     * @param player
     */
    fun forceToEnhancement(item: ItemStack, player: Player, clicked: Clickable) {
        if (!validateForce(item, player, clicked)) {
            return
        }
        // Current enchant level before enhancing
        val enchantLevel = ItemManager.getItemEnchantLevel(item) + 1
        // Finds the stone used in the enhancement
        val stoneId = getStoneId(item, enchantLevel, clicked)
        // Finds the cost of enhance.
        val costToEnhance = getForceEnhanceCost(item, player)
        Main.getApi().addItem(player.name, stoneId, -costToEnhance)
        enhanceSuccess(item, player, true, enchantLevel, clicked)
    }

    /**
     * Gets chance as a list.
     *
     * @param item
     * @param playerName
     * @return
     */
    fun getChance(item: ItemStack, playerName: String, clicked: Clickable): String {
        // Enchantment type
        val type = ItemManager.getItemEnchantmentType(item)
        if (type !== ItemType.INVALID) {
            // Display failstack
            var placeholder = ""
            if (clicked == MainMenu.gear) {
                placeholder = String.format("%.2f", Main.getApi().getChance(
                        playerName, ItemManager.getItemEnchantLevel(item) + 1) * 100)
            } else if (clicked == MainMenu.tool) {
                placeholder = String.format("%.2f", Main.getApi().getChance(
                        playerName, ItemManager.getToolEnchantLevel(item) + 1) * 100)
            }

            // Display chance after failstack is applied
            return SettingsManager.lang.getString(
                    "enhance.successRate").replace("%chance%".toRegex(), placeholder)
        } else {
            return SettingsManager.lang.getString("enhance.itemInvalid")
        } // Invalid item
    }


    fun getValidationOfToolItem(item: ItemStack): Boolean {
        // If item cannot be enhanced
        return if (!SettingsManager.config.getBoolean("enableStackedItem") && item.amount > 1) {
            false
        } else ItemManager.getToolItemEnchantmentType(item) !== ItemType.INVALID && ItemManager.getToolEnchantLevel(item) >= 0 && ItemManager.getToolEnchantLevel(item) < DataManager.levels - 1
    }
}
