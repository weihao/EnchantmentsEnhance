/*
 *     Copyright (C) 2017-Present HealPotion
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
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.api.API
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.event.blackspirit.Enhance
import org.pixeltime.enchantmentsenhance.manager.IM
import org.pixeltime.enchantmentsenhance.util.Util


class DebugCommand : SubCommand() {
    override val permission: String
        get() = "Enchantmentsenhance.debug"

    override fun onCommand(p: Player, args: Array<String>) {
        if (args[0] == "upgrade") {
            Enhance.enhanceSuccess(p.itemInHand, p, false, 19)
        } else if (args[0] == "slots") {
            IM.getArmorSlots(p)
        } else if (args[0] == "mysql") {
        } else if (args[0] == "format") {
            val subcommands = Main.getMain().commandManager.commands.iterator()
            while (subcommands.hasNext()) {
                val sc = subcommands.next()
                val curr = "Command: " + sc.usage() + " Permission: " + sc.permission
                Util.sendMessage(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', curr)), p, false)
            }
        } else if (args[0].equals("failstack", ignoreCase = true)) {
            API.addFailstack(p.name, 200)
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
