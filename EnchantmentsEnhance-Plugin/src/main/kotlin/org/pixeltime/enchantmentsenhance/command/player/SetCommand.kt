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

import org.bukkit.entity.Player
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.mysql.PlayerStat
import org.pixeltime.enchantmentsenhance.util.Util
import java.lang.NumberFormatException
import java.text.ParseException

class SetCommand : SubCommand() {
    override val permission: String
        get() = "Enchantmentsenhance.set"

    override fun onCommand(player: Player, args: Array<String>) {
        if (args.size == 1) {
            try {
                val leverage: Int = Integer.parseInt(args[0])
                if (leverage > 1) {
                    PlayerStat.getPlayerStats(player.name).grind = leverage
                    Util.sendMessage(SettingsManager.lang.getString("set.success")
                            .replace("%leverage%",
                                    Integer.toString(leverage)), player)
                } else {
                    Util.sendMessage(SettingsManager.lang.getString("set.failed"), player)
                }
            } catch (ex: ParseException) {
                Util.sendMessage(SettingsManager.lang.getString("config.invalidNumber"), player
                )
            } catch (ex: NumberFormatException) {
                Util.sendMessage(SettingsManager.lang.getString("config" +
                        ".invalidNumber"), player)
            }
        } else {
            Util.sendMessage(SettingsManager.lang.getString(
                    "config.invalidNumber"), player)
        }
    }

    override fun name(): String {
        return "set"
    }

    override fun usage(): String {
        return "/enhance set { multiplier }"
    }

    override fun aliases(): Array<String> {
        return arrayOf("")
    }


}