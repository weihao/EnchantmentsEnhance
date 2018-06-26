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

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.event.blacksmith.SecretBook
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

class SelectCommand : SubCommand() {


    override val permission: String
        get() = "Enchantmentsenhance.enhance"

    override fun onCommand(player: Player, args: Array<String>) {
        var error: Exception? = null
        var num = 1
        try {
            num = Integer.parseInt(args[1])
        } catch (e: NumberFormatException) {
            Util.sendMessage(SettingsManager.lang.getString(
                    "Config.invalidNumber"), player)
        } catch (e: ArrayIndexOutOfBoundsException) {
            error = e
            SecretBook.select(player, num)
        }

        if (error == null) {
            SecretBook.select(player, num)
        }
    }


    override fun name(): String {
        return "select"
    }


    override fun usage(): String {
        return "/enhance select { n }"
    }


    override fun aliases(): Array<String> {
        return arrayOf("select", "sl", "xuanze", "xz")
    }

}
