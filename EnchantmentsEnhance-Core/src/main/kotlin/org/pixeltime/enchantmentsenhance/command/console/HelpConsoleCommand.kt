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

package org.pixeltime.enchantmentsenhance.command.console

import org.bukkit.command.CommandSender
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.command.SubConsoleCommand

class HelpConsoleCommand : SubConsoleCommand() {

    override fun onCommand(sender: CommandSender, args: Array<String>) {
        Main.getCommandManager().printHelp(sender)
    }


    override fun name(): String {
        return "help"
    }


    override fun usage(): String {
        return "/enhance help"
    }


    override fun aliases(): Array<String> {
        return arrayOf("help", "hl", "bangzhu", "bz")
    }

}
