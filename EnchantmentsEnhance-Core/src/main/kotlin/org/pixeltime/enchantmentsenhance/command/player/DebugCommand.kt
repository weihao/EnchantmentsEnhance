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

package org.pixeltime.enchantmentsenhance.command.player

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.util.Util


class DebugCommand : SubCommand() {
    override val permission: String
        get() = "Enchantmentsenhance.debug"

    override fun onCommand(player: Player, args: Array<String>) {
        when {
            args[0] == "format" -> {
                val subcommands = Main.getCommandManager().commands.iterator()
                while (subcommands.hasNext()) {
                    val sc = subcommands.next()
                    val curr = "Command: " + sc.usage() + " Permission: " + sc.permission
                    Util.sendMessage(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', curr)), player, false)
                }
            }
            args[0] == "info" -> {
                println(player.inventory.itemInMainHand.toString())
                println(player.inventory.itemInMainHand.serialize())
                val item = ItemStack.deserialize(player.inventory.itemInMainHand.serialize())
                player.inventory.addItem(item)
            }
            args[0] == "bar" -> {
            }
        }
    }


    override fun name(): String {
        return "debug"
    }


    override fun usage(): String {
        return "/enhance debug"
    }


    override fun aliases(): Array<String> {
        return arrayOf("debug", "tiaoshi", "ts")
    }
}
