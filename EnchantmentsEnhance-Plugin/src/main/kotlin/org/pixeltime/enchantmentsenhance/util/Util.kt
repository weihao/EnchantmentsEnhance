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

package org.pixeltime.enchantmentsenhance.util

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.DyeColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.material.Wool
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import java.util.*

object Util {

    val UNIQUEID = ChatColor.translateAlternateColorCodes('&', "&r&c&r&a&r&4&r&e&r&a&r&8&r&2&r&a&r&5&r&8&r&c&r&4&r&7&r&e&r&b&r&e")

    /**
     * Lists all the enhancement stone possible.
     */
    fun pluginTag(): String {
        return ChatColor.translateAlternateColorCodes('&',
                SettingsManager.lang.getString("config.pluginTag"))
    }

    /**
     * Calculate the inventory slot.
     *
     * @param x in-game x-cord, starting from 1 to 9.
     * @param y in-game y-cord, starting from 1 to 9.
     * @return returns the inventory slot.
     */
    fun getSlot(x: Int, y: Int): Int {
        return y * 9 - (9 - x) - 1
    }

    /**
     * Checks if an item is a valid plugin generated item.
     *
     * @param item
     * @param displayName
     * @return
     */
    fun isPluginItem(item: ItemStack?, displayName: String): Boolean {
        if (item != null && item.hasItemMeta() && item.itemMeta
                    .hasDisplayName()) {
            val itemName = item.itemMeta.displayName
            val stripped = ChatColor.stripColor(itemName)
            if (itemName != stripped && stripped == ChatColor
                        .stripColor(displayName)) {
                return true
            }
        }
        return false
    }

    /**
     * Sends a colored message to a player.
     *
     * @param msg
     * @param player
     */
    fun sendMessage(msg: String, player: CommandSender) {
        val message = ChatColor.translateAlternateColorCodes('&', msg)
        player.sendMessage(pluginTag() + message)
    }

    /**
     * Sends a colored message to a player.
     *
     * @param msg
     * @param player
     */
    fun sendMessage(msg: String, player: String) {
        val p = Bukkit.getPlayer(player)
        val message = ChatColor.translateAlternateColorCodes('&', msg)
        p?.sendMessage(pluginTag() + message)
    }

    /**
     * Sends a colored message to a player.
     *
     * @param msg
     * @param player
     */
    fun sendMessage(
            msg: String,
            player: CommandSender,
            addPlugintag: Boolean) {
        val message = ChatColor.translateAlternateColorCodes('&', msg)
        player.sendMessage(message)
    }

    /**
     * Translates color codes.
     *
     * @param str
     * @return
     */
    fun toColor(str: String): String {
        return ChatColor.translateAlternateColorCodes('&', str)
    }

    /**
     * Translates color codes.
     *
     * @param str
     * @return
     */
    fun toColor(vararg str: String): Array<String> {
        val result = arrayOfNulls<String>(str.size)
        for (i in str.indices) {
            result[i] = toColor(str[i])
        }
        return result.requireNoNulls()
    }

    /**
     * Translates color codes.
     *
     * @param str
     * @return
     */
    fun toColor(str: List<String>): List<String> {
        val result = ArrayList<String>()
        for (s in str) {
            result.add(toColor(s))
        }
        return result
    }

    /**
     * Gets a player as a Player entity.
     *
     * @param str
     * @return
     */
    fun getPlayer(str: String): Player {
        return Bukkit.getServer().getPlayer(str)
    }

    /**
     * Gets a player's username as a string.
     *
     * @param player
     * @return
     */
    fun getPlayerUsername(player: Player): String {
        return player.name
    }

    /**
     * Removes all the alphabet in a string.
     *
     * @param str
     * @return
     */
    fun extractNumber(str: String): Int {
        return Integer.parseInt(str.replace("[^0-9]".toRegex(), ""))
    }

    /**
     * Make a string colorful.
     *
     * @param string
     * @return
     */
    fun rainbowlize(string: String): String {
        val lastColor = 0
        var currColor: Int
        var newString = ""
        val colors = "123456789abcde"
        for (i in 0 until string.length) {
            do {
                currColor = Random().nextInt(colors.length - 1) + 1
            } while (currColor == lastColor)
            newString += ChatColor.RESET.toString() + ChatColor.getByChar(colors[currColor]) + "" + string[i]
        }
        return newString
    }

    /**
     * Make a string colorful.
     *
     * @param string
     * @return
     */
    fun rainbowlizeCode(string: String): String {
        val lastColor = 0
        var currColor: Int
        var newString = ""
        val colors = "123456789abcde"
        for (i in 0 until string.length) {
            do {
                currColor = Random().nextInt(colors.length - 1) + 1
            } while (currColor == lastColor)
            newString += "&r" + "&" + colors[currColor] + string[i]
        }
        return newString
    }

    /**
     * Creates a GUI button made of a wool.
     *
     * @param dc
     * @param name
     * @return
     */
    fun createButton(dc: DyeColor, name: String): ItemStack {
        val i = Wool(dc).toItemStack(1)
        val im = i.itemMeta
        im.displayName = name
        i.itemMeta = im
        return i
    }

    fun romanToInt(num: String): Int {
        if (num.isEmpty() || num == " ") {
            return 0
        }
        val ht = Hashtable<Char, Int>()
        ht['I'] = 1
        ht['X'] = 10
        ht['V'] = 5
        var intNum = 0
        var prev = 0
        for (i in num.length - 1 downTo 0) {
            try {
                val temp = ht[num[i]]!!
                if (temp < prev)
                    intNum -= temp
                else
                    intNum += temp
                prev = temp
            } catch (ex: NullPointerException) {
                return Integer.parseInt(num)
            }

        }
        return intNum
    }

    fun intToRoman(input: Int): String {
        var input = input
        if (input > 10) {
            return Integer.toString(input)
        }
        var s = ""
        while (input >= 10) {
            s += "X"
            input -= 10
        }
        while (input >= 9) {
            s += "IX"
            input -= 9
        }
        while (input >= 5) {
            s += "V"
            input -= 5
        }
        while (input >= 4) {
            s += "IV"
            input -= 4
        }
        while (input >= 1) {
            s += "I"
            input -= 1
        }
        return s
    }

    /**
     * Removes any underscores and capitalizes first letter of each word in the
     * string.
     *
     * @param s
     * @return
     */
    fun format(s: String): String {
        if (!s.contains("_")) {
            return capitalize(s)
        }
        val j = s.split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        var c = ""

        for (f in j) {
            var str = capitalize(f)
            c += if (c.equals("", ignoreCase = true)) str else " $str"
        }
        return c
    }

    /**
     * Capitalizes first letter of each word in the string.
     *
     * @param text
     * @return
     */
    fun capitalize(text: String): String {
        val firstLetter = text.substring(0, 1).toUpperCase()
        val next = text.substring(1).toLowerCase()
        return firstLetter + next
    }

    fun invFull(p: Player): Boolean {
        return p.inventory.firstEmpty() == -1
    }
}
