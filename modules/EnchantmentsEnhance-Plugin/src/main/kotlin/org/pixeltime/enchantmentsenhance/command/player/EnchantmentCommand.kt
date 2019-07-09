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
import org.pixeltime.enchantmentsenhance.Main
import org.pixeltime.enchantmentsenhance.command.SubCommand
import org.pixeltime.enchantmentsenhance.manager.SettingsManager
import org.pixeltime.enchantmentsenhance.util.Util

class EnchantmentCommand : SubCommand() {

    override val permission: String
        get() = "Enchantmentsenhance.ench"

    override fun onCommand(player: Player, args: Array<String>) {
        if (args.size >= 2) {
            if (args[0].equals("add", ignoreCase = true)) {
                val item = Util.getMainHand(player)
                if (item == null) {
                    Util.sendMessage(SettingsManager.lang.getString("config.invalidItem"), player)
                    return
                }
                var level = 1
                try {
                    level = Integer.parseInt(args[2])
                } catch (ex: NumberFormatException) {
                    // Expected
                } catch (ex: ArrayIndexOutOfBoundsException) {
                    // Expected
                }
                if (Main.getApi().addCustomEnchant(item, args[1], level)) {
                    Util.sendMessage(SettingsManager.lang.getString("config.success"), player)
                } else {
                    Util.sendMessage(SettingsManager.lang.getString("config.invalidEnchant"), player)
                }
            } else {
                Util.sendMessage(SettingsManager.lang.getString("config.invalidCommand"), player)
            }
        } else {
            Util.sendMessage(SettingsManager.lang.getString("config.invalidCommand"), player)
        }
    }

    override fun name(): String {
        return "enchantment"
    }

    override fun usage(): String {
        return "/enhance enchantment add {enchantment} {level}"
    }

    override fun aliases(): Array<String> {
        return arrayOf("ench")
    }
}
