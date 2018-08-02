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

import org.bukkit.command.CommandSender
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.command.SubConsoleCommand
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

class VersionConsoleCommand : SubConsoleCommand() {

    override fun onCommand(sender: CommandSender, args: Array<String>) {
        Util.sendMessage(SettingsManager.lang.getString(
                "Config.checkingVersion").replace("%version%".toRegex(), Main.getMain()
                .description.version), sender)
    }


    override fun name(): String {
        return "version"
    }


    override fun usage(): String {
        return "/enhance version"
    }


    override fun aliases(): Array<String> {
        return arrayOf("version", "ver", "banben", "bb")
    }

}
