/*
 *     Coplayeryright (C) 2017-Present HealPot
 *
 *     This playerrogram is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as playerublished by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your oplayertion) any later version.
 *
 *     This playerrogram is distributed in the hoplayere that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implayerlied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a coplayery of the GNU General Public License
 *     along with this playerrogram.  If not, see <httplayers://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.event

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util
import java.util.*

object Lore {

    /**
     * Adds lore to the enhanced item.
     *
     * @param stack
     * @param player
     * @param str
     * @param tradeable
     */
    fun addLore(
            stack: ItemStack,
            player: Player,
            str: String,
            tradeable: Boolean,
            sendMessage: Boolean
    ) {
        val im = stack.getItemMeta()
        val lore = Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', str)
        val x: String
        val y: String
        if (tradeable) {
            x = "tradeable"
            y = "untradeable"
        } else {
            x = "untradeable"
            y = "tradeable"
        }

        if (stack.hasItemMeta() && stack.getItemMeta().hasLore()) {
            val loreList = stack.getItemMeta().getLore()
            if (loreList.contains(lore)) {
                if (SettingsManager.config.getBoolean(
                                "lore.sendBoundingMessage") && sendMessage) {
                    Util.sendMessage(SettingsManager.lang.getString(
                            "messages.already$x"), player)
                }
                return
            }
            loreList.remove(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                    SettingsManager.lang.getString("lore." + y + "Lore")))
            loreList.add(lore)
            im.setLore(loreList)
            stack.setItemMeta(im)
            if (SettingsManager.config.getBoolean(
                            "lore.sendBoundingMessage") && sendMessage) {
                Util.sendMessage(SettingsManager.lang.getString("messages.made$x"), player)
            }
            return
        }

        im.lore = Arrays.asList(lore)
        stack.itemMeta = im
        player.updateInventory()
        if (SettingsManager.config.getBoolean(
                        "lore.sendBoundingMessage") && sendMessage) {
            Util.sendMessage(SettingsManager.lang.getString("messages.made$x"), player)
        }
    }

    fun addLore(
            stack: ItemStack,
            str: String,
            tradeable: Boolean
    ) {
        val lore = Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&', str)
        val im = stack.itemMeta

        val y: String = if (tradeable) {
            "untradeable"
        } else {
            "tradeable"
        }

        if (stack.hasItemMeta() && stack.itemMeta.hasLore()) {
            val loreList = stack.itemMeta.lore
            if (loreList.contains(lore)) {
                return
            }
            loreList.remove(Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                    SettingsManager.lang.getString("lore." + y + "Lore")))
            loreList.add(lore)
            im.lore = loreList
            stack.itemMeta = im
            return
        }
        im.lore = Arrays.asList(lore)
        stack.itemMeta = im
    }

    /**
     * removes the lore of an enhanced item.
     *
     * @param stack
     * @param player
     */
    fun removeLore(stack: ItemStack, player: Player) {
        val im = stack.itemMeta

        val x = Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("lore.tradeableLore"))
        val y = Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("lore.untradeableLore"))
        if (stack.hasItemMeta() && stack.itemMeta.hasLore()) {
            val loreList = stack.itemMeta.lore
            if (loreList.contains(x) || loreList.contains(y)) {
                loreList.remove(x)
                loreList.remove(y)
            }
            im.lore = loreList
            stack.itemMeta = im
            Util.sendMessage(SettingsManager.lang.getString(
                    "messages.madeunbound"), player)
            return
        }
        Util.sendMessage(SettingsManager.lang.getString(
                "messages.alreadyunbound"), player)
    }

    /**
     * removes the lore of an enhanced item.
     *
     * @param stack
     */
    fun removeLore(stack: ItemStack) {
        val im = stack.itemMeta
        val x = Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("lore.tradeableLore"))
        val y = Util.UNIQUEID + ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("lore.untradeableLore"))
        if (stack.hasItemMeta() && stack.itemMeta.hasLore()) {
            val loreList = stack.itemMeta.lore
            if (loreList.contains(x) || loreList.contains(y)) {
                loreList.remove(x)
                loreList.remove(y)
            }
            im.lore = loreList
            stack.itemMeta = im
        }
    }
}
