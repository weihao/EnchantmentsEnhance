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

package org.pixeltime.enchantmentsenhance.command.console

import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.pixeltime.enchantmentsenhance.command.SubConsoleCommand
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util



class ReloadConsoleCommand : SubConsoleCommand() {
    override fun onCommand(sender: CommandSender, args: Array<String>) {
        Util.sendMessage(SettingsManager.lang.getString("Config.reloading"),
                sender)
        val console = Bukkit.getServer().consoleSender
        val command = "reload"
        Bukkit.dispatchCommand(console, command)
        Util.sendMessage(SettingsManager.lang.getString("Config.reload"),
                sender)
    }


    override fun name(): String {
        return "reload"
    }


    override fun usage(): String {
        return "/enhance reload"
    }


    override fun aliases(): Array<String> {
        return arrayOf("reload", "rel", "chongzai", "cz")
    }

}
