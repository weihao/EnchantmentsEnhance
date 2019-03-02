/*
 *     Copyright (C) 2017-Present 25
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

package org.pixeltime.enchantmentsenhance.command


import org.bukkit.command.CommandSender
import org.pixeltime.enchantmentsenhance.manager.SettingsManager

/**
 * /<command> <subcommand> args[0] args[1]
</subcommand></command> */
abstract class SubConsoleCommand {

    /**
     * Console Command
     *
     * @param sender
     * @param args
     */
    abstract fun onCommand(sender: CommandSender, args: Array<String>)

    /**
     * @return Command name.
     */
    abstract fun name(): String

    /**
     * @return Command information.
     */
    abstract fun usage(): String

    /**
     * @return Command aliases.
     */
    abstract fun aliases(): Array<String>

    fun info(): String {
        return "&b${usage()} ${SettingsManager.lang.getString("help.${name()}")}"
    }
}
