/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.command.console.ReloadConsoleCommand

class ReloadCommand : SubCommand() {


    override val permission: String
        get() = "Enchantmentsenhance.reload"

    override fun onCommand(player: Player, args: Array<String>) {
        ReloadConsoleCommand().onCommand(player, args)
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
